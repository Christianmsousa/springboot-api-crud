package com.study.crudjava.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.study.crudjava.exception.BaseMessage;
import com.study.crudjava.exception.Details;
import com.study.crudjava.exception.FieldErrorList;
import com.study.crudjava.exception.InvalidEmailException;
import com.study.crudjava.exception.InvalidUpdateException;
import com.study.crudjava.exception.UserNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Details<List<FieldErrorList>> sintaxErro(MethodArgumentNotValidException exception) {

    return new Details<List<FieldErrorList>>(LocalDateTime.now(), 400, "Bad Request", exception.getBindingResult()
        .getAllErrors().stream().map((error) -> (new FieldErrorList((FieldError) error))).collect(Collectors.toList()));
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public Details<BaseMessage> syntaxError() {
    return new Details<BaseMessage>(LocalDateTime.now(), 400, "Bad Request", new BaseMessage("Syntax Error"));

  }

  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  @ExceptionHandler(UserNotFoundException.class)
  public Details<BaseMessage> userNotFound() {
    return new Details<BaseMessage>(LocalDateTime.now(), 404, "Not Found", new BaseMessage("User not found"));
  }

  @ResponseStatus(code = HttpStatus.CONFLICT)
  @ExceptionHandler(InvalidEmailException.class)
  public Details<BaseMessage> tEste() {
    return new Details<BaseMessage>(LocalDateTime.now(), 409, "Not Found", new BaseMessage("Invalid email"));
  }

  @ResponseStatus(code = HttpStatus.CONFLICT)
  @ExceptionHandler(DataIntegrityViolationException.class)
  public Details<BaseMessage> userAlreadyRegistered() {

    return new Details<BaseMessage>(LocalDateTime.now(), 409, "Conflict",
        new BaseMessage("User already registered with this email"));
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(InvalidUpdateException.class)
  public Details<BaseMessage> invalidUpdate() {

    return new Details<BaseMessage>(LocalDateTime.now(), 400, "Bad Request",
        new BaseMessage("You cannot change the email address"));
  }
}