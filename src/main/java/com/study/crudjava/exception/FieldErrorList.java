package com.study.crudjava.exception;

import org.springframework.validation.FieldError;

public class FieldErrorList {

  private String field;
  private String message;

  public FieldErrorList(FieldError error) {
    this.field = error.getField();
    this.message = error.getDefaultMessage();

  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
