package com.cbr.university.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = IdExistsInDbValidator.class)
public @interface IdExistsInDb {

    String typeObject();

    String message() default "Id not found in database";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
