package com.study.crudjava.controller;

import java.util.List;
import javax.validation.Valid;

import com.study.crudjava.exception.InvalidEmailException;
import com.study.crudjava.exception.InvalidUpdateException;
import com.study.crudjava.exception.UserNotFoundException;
import com.study.crudjava.model.User;
import com.study.crudjava.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  // Retorna todo os usuarios
  @GetMapping("/user")
  public List<User> findAllUser() {
    return userRepository.findAll();
  }

  // Criar usuario
  @PostMapping("/user")
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

    String[] splitEmail = user.getEmail().split("@");
    String emailVerify = splitEmail[1];
    emailVerify.toLowerCase();

    if (emailVerify.equals("gmail.com") || emailVerify.equals("yahoo.com")) {
      userRepository.save(user);
      return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    throw new InvalidEmailException();

  }

  @GetMapping("/user/{id}")
  public User findOne(@PathVariable(value = "id") Long Userid) {
    return userRepository.findById(Userid).orElseThrow(() -> new UserNotFoundException());
  }

  @DeleteMapping("/user/{id}")
  public ResponseEntity<User> removeUser(@PathVariable(value = "id") Long userId) {

    User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
    userRepository.delete(user);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @PatchMapping("/user/{id}")
  public ResponseEntity<User> updateNameOrAge(@PathVariable(value = "id") Long userId, @Valid @RequestBody User user) {

    if (user.getEmail() != null) {
      throw new InvalidUpdateException();
    }
    User userFind = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());

    if (user.getName() != null && user.getAge() != null) {
      userFind.setName(user.getName());
      userFind.setAge(user.getAge());
      userRepository.save(userFind);

      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    if (user.getAge() != null) {

      userFind.setAge(user.getAge());
      userRepository.save(userFind);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    if (user.getName() != null) {
      userFind.setName(user.getName());
      userRepository.save(userFind);

      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // throw new InvalidUpdateException();
    throw new HttpMessageNotReadableException(null);

  }

  @PutMapping("/user/{id}")
  public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User user) {
    String[] userEmailSplit = user.getEmail().split("@");
    String userEmail = userEmailSplit[1];
    userEmail.toLowerCase();

    User userFind = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());

    if (user.getAge() == null || user.getName() == null) {
      throw new HttpMessageNotReadableException(null);
    }
    if (userEmail.equals("gmail.com") || userEmail.equals("yahoo.com")) {

      userFind.setName(user.getName());
      userFind.setAge(user.getAge());
      userFind.setEmail(user.getEmail());

      userRepository.save(userFind);

      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    throw new HttpMessageNotReadableException(null);

  }
}
