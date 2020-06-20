package com.cbr.university.model.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class CourseDto {
    
    @NotNull
    private int id;
    @JsonIgnore
    private String name;
    
    

}
