package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.util.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.util.ValidatorService;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidatorServiceImpl implements ValidatorService {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).isEmpty();
    }

    @Override
    public <E> Set<ConstraintViolation<E>> validate(E entity) {
        return this.validator.validate(entity);
    }
}
