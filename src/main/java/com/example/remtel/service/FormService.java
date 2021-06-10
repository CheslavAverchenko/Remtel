package com.example.remtel.service;

import com.example.remtel.beans.User;
import com.example.remtel.beans.dto.FormDto;
import com.example.remtel.repos.FormRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FormService {
    @Autowired
    private FormRepo formRepo;

    public Page<FormDto> formList(Pageable pageable , String filter, User user){
        if(filter != null && !filter.isEmpty()){
            return formRepo.findByNotAdmittedSubject(filter.trim(), pageable, user);
        } else {
            return formRepo.findAll(pageable, user);
        }
    }

    public Page<FormDto> formListForAdmittedUser(Pageable pageable, User currentUser, User user) {
        return formRepo.findByAdmittedUser(pageable, currentUser, user);
    }

    public Page<FormDto> formListForNotAdmittedUser(Pageable pageable, User currentUser, User user){
        return formRepo.findByNotAdmittedUser(pageable, currentUser, user);
    }

    public Page<FormDto> getAdmittedForms(Pageable pageable , String filter, User user){
        if(filter != null && !filter.isEmpty()){
            return formRepo.findByAdmittedSubject(filter.trim(), pageable, user);
        } else {
            return formRepo.findFormsByMeAdmitted(pageable,user);
        }
    }
}
