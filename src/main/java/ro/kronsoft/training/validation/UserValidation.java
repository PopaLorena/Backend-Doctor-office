package ro.kronsoft.training.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidator.class)
public @interface UserValidation {

	String message() default "the user is invalid";

	Class<? extends Payload>[] payload() default{};

	Class<?>[] groups() default{}; 
}
