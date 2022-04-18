package com.cbr.university.validation;

import com.cbr.university.service.BaseService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdExistsInDbValidator implements ConstraintValidator<IdExistsInDb, Integer> {

    private final ServiceSimpleFactory serviceFactory;
    private BaseService<?> currentService;

    IdExistsInDbValidator(ServiceSimpleFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @Override
    public void initialize(IdExistsInDb exist) {
        String typeObject = exist.typeObject();
        this.currentService = serviceFactory.load(typeObject);
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        if (id == null) {
            return true;
        }
        return currentService.existsById(id);
    }
}
