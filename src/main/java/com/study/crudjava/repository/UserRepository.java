package com.study.crudjava.repository;

import java.util.List;
// import java.util.Optional;

import com.study.crudjava.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByEmail(String email);

}
