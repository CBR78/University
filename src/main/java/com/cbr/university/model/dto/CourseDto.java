package com.cbr.university.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

public class CourseDto {
    
    @NotNull
    private int id;
    @JsonIgnore
    private String name;
    
    

}
