package _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.util;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface ValidationUtil {
    <E> boolean isValid(E entity);

    <E> Set<ConstraintViolation<E>> getViolations(E entity);
}
