package com.study.crudjava.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Email
  @Column(unique = true)
  private String email;

  @Column(name = "age", nullable = false)
  @Range(min = 18, max = 60, message = "O valor deve ser entre {min} e {max}")
  private Integer age;

  @Override
  public String toString() {
    return "User [age=" + age + ", email=" + email + ", id=" + id + ", name=" + name + "]";
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name.toLowerCase();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email.toLowerCase();
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public User(String name, @Email String email, Integer age) {
    this.name = name.toLowerCase();
    this.email = email.toLowerCase();
    this.age = age;
  }

  public User() {
  }

}
