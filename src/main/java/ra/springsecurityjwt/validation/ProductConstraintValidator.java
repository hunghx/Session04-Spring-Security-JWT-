package ra.springsecurityjwt.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ra.springsecurityjwt.repository.ProductRepository;

@Component
@RequiredArgsConstructor
public class ProductConstraintValidator implements ConstraintValidator<ProductNameUnique,String> {
   private final ProductRepository productRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !productRepository.existsByProductName(value);
    }
}
