package ra.springsecurityjwt.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = ProductConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProductNameUnique {
    String message() default "product name is exists !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
