package com.example.remtel.beans.dto;

import com.example.remtel.beans.Form;
import com.example.remtel.beans.User;
import com.example.remtel.beans.util.FormHelper;

import java.time.LocalDate;

public class FormDto {
    private Long id;
    private String message;
    private String subject;
    private LocalDate date;
    private String filename;
    private User user;
    private Long admit;
    private boolean meAdmitted;

    public FormDto(Form form, Long admit, boolean meAdmitted){
        this.id = form.getId();
        this.message = form.getMessage();
        this.subject = form.getSubject();
        this.filename = form.getFilename();
        this.user = form.getUser();
        this.date = form.getDate();

        this.meAdmitted = meAdmitted;
        this.admit = admit;
    }

    public FormDto(Form form){
        this.id = form.getId();
        this.message = form.getMessage();
        this.subject = form.getSubject();
        this.filename = form.getFilename();
        this.user = form.getUser();
        this.date = form.getDate();
    }

    public String getUserName(){
        return FormHelper.getUserName(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getAdmit() {
        return admit;
    }

    public void setAdmit(Long admit) {
        this.admit = admit;
    }

    public boolean isMeAdmitted() {
        return meAdmitted;
    }

    public void setMeAdmitted(boolean meAdmitted) {
        this.meAdmitted = meAdmitted;
    }

    @Override
    public String toString() {
        return "FormDto{" +
                "id=" + id +
                ", user=" + user +
                ", admit=" + admit +
                ", meAdmitted=" + meAdmitted +
                '}';
    }
}
