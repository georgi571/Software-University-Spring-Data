package _8SpringData._1SpringData._8JSONProcessing._1Lab._1ProductsShop.org.softwareuniversityspringdata.util;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface ValidationUtil {
    <E> boolean isValid(E entity);

    <E> Set<ConstraintViolation<E>> getViolations(E entity);
}
