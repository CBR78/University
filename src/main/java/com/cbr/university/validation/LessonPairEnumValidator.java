package com.cbr.university.validation;

import com.cbr.university.model.LessonPair;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LessonPairEnumValidator implements ConstraintValidator<LessonPairEnum, LessonPair> {

    @Override
    public boolean isValid(LessonPair lessonPair, ConstraintValidatorContext context) {
        if (lessonPair == null) {
            return true;
        }

        boolean result = false;
        LessonPair[] allValues = LessonPair.values();

        for (LessonPair value : allValues) {
            if (value.equals(lessonPair)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
