package com.feibai.spring.study.annotation.constraint;

import com.feibai.spring.study.annotation.Status;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class StatusValidator implements ConstraintValidator<Status, String> {
  private final String[] ALL_STATUS = {"created", "paid", "shipped", "closed"};

  public void initialize(Status status) {
  }

  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (Arrays.asList(ALL_STATUS).contains(value))
      return true;
    return false;
  }
}