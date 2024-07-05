package com.FatimaElouafi.employeesmanagement.repo;

import com.FatimaElouafi.employeesmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserById(int id);
    Optional<User> deleteUserById(int id);

}