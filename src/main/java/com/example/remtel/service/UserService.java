package com.example.remtel.service;


import com.example.remtel.beans.Role;
import com.example.remtel.beans.User;
import com.example.remtel.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean addUser(User user){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if(userFromDb != null){
            return false;
        }
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        sendMessage(user, "Welcome to Remtel. Please visit next link: http://localhost:8081/activate/%s");
        return true;
    }

    private void sendMessage(User user, String s) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Hello, %s! \n" +
                            s,
                    user.getName(),
                    user.getActivationCode());
            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }

    public void sendQuestion(User user, String s){
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Hello, %s! \n" +
                            s,
                    user.getName());
            mailSender.send(user.getEmail(), "Remtel", message);
        }
    }

    public void sendReport(User user, String task, String cost){
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Hello, %s! \n" +
                            task + ". К оплате: %s$. Было приятно иметь с вами дело",
                    user.getName(),
                    cost);
            mailSender.send(user.getEmail(), "Remtel", message);
        }
    }

    public void sendMessageIfTaskAdmittedOrCanceled(User user, String task, String model, String answer){
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Hello, %s! \n" +
                            ". Ваш заказ %s, модель: %s был %s",
                    user.getName(), task, model, answer);
            mailSender.send(user.getEmail(), "Remtel", message);
        }
    }

    public String editUser(User user, String email, String username, String password, String name, String phonenumber){
        String userEmail = user.getEmail();
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));
        if(isEmailChanged){
            if(!StringUtils.isEmpty(email)){
                user.setEmail(email);
                user.setActive(false);
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if(!StringUtils.isEmpty(username)){
            user.setUsername(username);
        }
        if(!StringUtils.isEmpty(password)){
            user.setPassword(passwordEncoder.encode(password));
        }
        if(!StringUtils.isEmpty(name)){
            user.setName(name);
        }
        if(!StringUtils.isEmpty(phonenumber)){
            user.setPhonenumber(phonenumber);
        }
        userRepo.save(user);
        if(isEmailChanged){
            sendMessage(user, "If you want that your changes will be saved, please visit next link: http://localhost:8081/activate/%s");
            return "/login";
        }
        return "/user/profile";

    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if(user == null){
            return false;
        }
        user.setActive(true);
        user.setActivationCode(null);

        userRepo.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, Map<String, String> rolesMap) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key: rolesMap.keySet()) {
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }
}
