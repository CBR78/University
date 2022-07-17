package com.cbr.university.validation;

import com.cbr.university.service.BaseService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdExistsInDbValidator implements ConstraintValidator<IdExistsInDb, Integer> {

    private final ServiceContext serviceContext;
    private BaseService<?> currentService;

    IdExistsInDbValidator(ServiceContext serviceContext) {
        this.serviceContext = serviceContext;
    }

    @Override
    public void initialize(IdExistsInDb exist) {
        this.currentService = serviceContext
                .getInstance(exist.typeObject())
                .orElseThrow(() -> new IllegalArgumentException(
                        "typeObject parameter accepts only 1 of 6 values - Course, Group, Room, ScheduleLine, Student, Teacher"));
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        if (id == null)
            return true;
        else
            return currentService.existsById(id);
    }
}
