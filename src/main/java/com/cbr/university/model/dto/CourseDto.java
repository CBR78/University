package com.cbr.university.model.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;


public class CourseDto {
    
    @NotNull
    @JsonView
    private int id;
    @JsonIgnore
    private String name;
    
    

}
