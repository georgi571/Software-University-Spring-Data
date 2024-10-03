package _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.repository;

import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.model.entity.AgeRestriction;
import _8SpringData._1SpringData._6SpringDataAdvancedQuerying._2Exercise.com.example.springintro.model.entity.EditionType;

import java.math.BigDecimal;

public interface BookInfo {
    String getTitle();
    EditionType getEditionType();
    AgeRestriction getAgeRestriction();
    BigDecimal getPrice();
}
