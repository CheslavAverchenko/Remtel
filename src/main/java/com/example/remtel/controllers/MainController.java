package com.example.remtel.controllers;

import com.example.remtel.beans.Form;
import com.example.remtel.beans.User;
import com.example.remtel.beans.dto.FormDto;
import com.example.remtel.repos.FormRepo;
import com.example.remtel.service.FormService;
import com.example.remtel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


@Controller
public class MainController {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private FormService formService;

    @Autowired
    private FormRepo formRepo;

    @Autowired
    private UserService userService;


    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model){
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        if(isConfirmEmpty){
            model.addAttribute("password2Error", "Password confirmation cannot be empty");
        }
        if(user.getPassword() != null && !user.getPassword().equals(passwordConfirm)){
            bindingResult.addError(new FieldError("user","password2","Passwords are different!"));
        }

        if(isConfirmEmpty || bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        if(!userService.addUser(user)){
            model.addAttribute("message","User exists");
            return "registration";
        }

        return "redirect:/login";
    }


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }


    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       @AuthenticationPrincipal User user){
        Page<FormDto> activePageForms = formService.formList(pageable, filter, user);
        model.addAttribute("pageActive",activePageForms);
        model.addAttribute("url","/main");
        model.addAttribute("filter",filter);
        return "main";
    }

    @GetMapping("/admitted")
    public String admitted(@RequestParam(required = false, defaultValue = "") String filter,
                           Model model,
                           @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                           @AuthenticationPrincipal User user){
        Page<FormDto> admittedPageForms = formService.getAdmittedForms(pageable, filter, user);
        model.addAttribute("pageAdmitted",admittedPageForms);
        model.addAttribute("url","/admitted");
        model.addAttribute("filter",filter);
        return "admitted";
    }


    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam(required = false, defaultValue = "") String filter,
                      @Valid Form form,
                      BindingResult bindingResult,
                      Model model,
                      @RequestParam("file") MultipartFile file,
                      @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) throws IOException {
        form.setUser(user);
        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("form",form);
        } else {
            saveFile(form,file);
            form.setDate(LocalDate.now());
            model.addAttribute("form",null);
            formRepo.save(form);
        }
        Page<FormDto> forms = formRepo.findAll(pageable,user);
        model.addAttribute("url","/main");
        model.addAttribute("pageActive",forms);
        model.addAttribute("filter",filter);
        return "main";
    }

    private void saveFile(@Valid Form form, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            form.setFilename(resultFilename);
        }
    }

    @GetMapping("/user-forms-admitted/{user}")
    public String getAdmittedUserForms(@AuthenticationPrincipal User currentUser,
                               @PathVariable User user,
                               Model model,
                               @RequestParam(required = false) Form form,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        Page<FormDto> forms = formService.formListForAdmittedUser(pageable, currentUser, user);

        model.addAttribute("pageAdmitted", forms);
        model.addAttribute("form", form);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        model.addAttribute("url", "/user-forms-admitted/" + user.getId());
        return "admitted";
    }

    @PostMapping("/admitted/{user}")
    public String sendQuestion(@PathVariable User user,
                               @RequestParam("question") String question,
                               Model model){
        userService.sendQuestion(user,question);
        return "redirect:/admitted";
    }

    @PostMapping("/admitted-cost/{user}")
    public String sendReport(@PathVariable User user,
                             @RequestParam("task") String task,
                             @RequestParam("cost") String cost,
                             Model model){
        userService.sendReport(user,task,cost);
        return "redirect:/admitted";
    }

    @GetMapping("/user-forms-not-admitted/{user}")
    public String getUserForms(@AuthenticationPrincipal User currentUser,
                               @PathVariable User user,
                               Model model,
                               @RequestParam(required = false) Form form,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        Page<FormDto> forms = formService.formListForNotAdmittedUser(pageable, currentUser, user);

        model.addAttribute("pageActive", forms);
        model.addAttribute("form", form);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        model.addAttribute("url", "/user-forms-not-admitted/" + user.getId());
        return "userForms";
    }

    @PostMapping("/user-forms-not-admitted/{user}")
    public String updateForm(@AuthenticationPrincipal User currentUser,
                             @PathVariable Long user,
                             @RequestParam("id") Form form,
                             @RequestParam("message") String message,
                             @RequestParam("subject") String subject,
                             @RequestParam("file") MultipartFile file) throws IOException {
        if(form.getUser().equals(currentUser)){
            if(!StringUtils.isEmpty(message)){
                form.setMessage(message);
            }
            if(!StringUtils.isEmpty(subject)){
                form.setSubject(subject);
            }
            saveFile(form, file);

            formRepo.save(form);
        }
        return "redirect:/user-forms-not-admitted/" + user;
    }

    @GetMapping("/forms/{form}/admit/{user}")
    public String admit(@AuthenticationPrincipal User currentUser,
                        @PathVariable Form form,
                        @PathVariable User user,
                        RedirectAttributes redirectAttributes,
                        @RequestHeader(required = false) String referer){
        Set<User> admits = form.getAdmits();

        if(admits.contains(currentUser)){
            admits.remove(currentUser);
            form.setAdmitted(false);
        } else {
            admits.add(currentUser);
            form.setAdmitted(true);
        }
        userService.sendMessageIfTaskAdmittedOrCanceled(user,form.getMessage(),form.getSubject(),"принят");
        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();
        components.getQueryParams()
                .forEach(redirectAttributes::addAttribute);
        formRepo.save(form);
        return "redirect:" + components.getPath();
    }

    @GetMapping("/forms/{form}/unMark/{user}")
    public String unMark(@AuthenticationPrincipal User currentUser,
                        @PathVariable Form form,
                        @PathVariable User user,
                        RedirectAttributes redirectAttributes,
                        @RequestHeader(required = false) String referer){
        Set<User> admits = form.getAdmits();

        if(admits.contains(currentUser)){
            admits.remove(currentUser);
            form.setAdmitted(false);
        } else {
            admits.add(currentUser);
            form.setAdmitted(true);
        }
        userService.sendMessageIfTaskAdmittedOrCanceled(user,form.getMessage(),form.getSubject(),"временно отклонен");
        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();
        components.getQueryParams()
                .forEach(redirectAttributes::addAttribute);
        formRepo.save(form);
        return "redirect:" + components.getPath();
    }

    @GetMapping("/forms/{form}/delete/{user}")
    public String deleteForm(@PathVariable Form form,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                             @PathVariable User user,
                             Model model,
                             RedirectAttributes redirectAttributes,
                             @RequestHeader(required = false) String referer){
        formRepo.delete(form);
        userService.sendMessageIfTaskAdmittedOrCanceled(user,form.getMessage(),form.getSubject(),"отклонен");
        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();
        components.getQueryParams()
                .forEach(redirectAttributes::addAttribute);
        return "redirect:" + components.getPath();
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        if(isActivated){
            model.addAttribute("messageType","success");
            model.addAttribute("message","User successfully activated");
        } else {
            model.addAttribute("messageType","danger");
            model.addAttribute("message","Activation code is not found");
        }

        return "login";
    }
}
