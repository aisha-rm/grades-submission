package com.tmt.gradessubmission;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

public class Grade {
    @NotBlank(message="Name cannot be blank") //overwriting default message
    private String name;

    @NotBlank(message="Subject cannot be blank")
    private String subject;

    @Score (message="Score must be a letter grade")
    private String score;
    
    private String id;

    public Grade() {
        //below sets a universal unique identifier as the id for each grade object
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
