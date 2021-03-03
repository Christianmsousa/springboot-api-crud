package com.study.crudjava.exception;

public class BaseMessage {
  private String message;

  public BaseMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
