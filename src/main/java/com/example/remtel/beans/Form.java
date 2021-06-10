package com.example.remtel.beans;

import com.example.remtel.beans.util.FormHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "message too long (more than 2kB)")
    private String message;
    @NotBlank(message = "Please fill the subject")
    @Length(max = 255, message = "message too long (more than 255)")
    private String subject;

    private LocalDate date;

    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @JoinColumn(name = "admitted")
    private boolean admitted;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "forms_admitted",
            joinColumns = { @JoinColumn(name = "form_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> admits = new HashSet<>();

    public Form() {
    }

//    public Form(String message, String subject, User user) {
//        this.message = message;
//        this.subject = subject;
//        this.user = user;
//        this.date = LocalDate.now();
//        System.out.println("--------------------------------------------");
//    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUserName(){
        return FormHelper.getUserName(user);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Set<User> getAdmits() {
        return admits;
    }

    public void setAdmits(Set<User> admits) {
        this.admits = admits;
    }

    public boolean isAdmitted() {
        return admitted;
    }

    public void setAdmitted(boolean admitted) {
        this.admitted = admitted;
    }
}
