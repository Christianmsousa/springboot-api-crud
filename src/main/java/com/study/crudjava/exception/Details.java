package com.study.crudjava.exception;

import java.time.LocalDateTime;

public class Details<T> {

  private LocalDateTime timestap;
  private Integer status;
  private String error;
  private T details;

  public Details(LocalDateTime timestap, Integer status, String error, T details) {
    this.timestap = timestap;
    this.status = status;
    this.error = error;
    this.details = details;
  }

  public LocalDateTime getTimestap() {
    return timestap;
  }

  public void setTimestap(LocalDateTime timestap) {
    this.timestap = timestap;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public T getDetails() {
    return details;
  }

  public void setDetails(T details) {
    this.details = details;
  }

}
