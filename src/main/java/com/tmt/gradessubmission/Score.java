package com.tmt.gradessubmission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ScoreValidator.class)
public @interface Score {

    String message() default "Invalid Data";  //message for binding result
	Class<?>[] groups() default {};     //boilerplate code that needs to be added when defining the @Constraint above
    Class<? extends Payload>[] payload() default {};        //as above
}
