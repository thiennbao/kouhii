package com.thiennbao.kouhii.common.validator;

import com.nimbusds.jose.Payload;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumNameValid {
    Class<? extends Enum<?>> enumClass();

    String message() default "Invalid enum name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
