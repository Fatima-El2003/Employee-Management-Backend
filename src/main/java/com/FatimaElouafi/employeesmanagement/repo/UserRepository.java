package com.FatimaElouafi.employeesmanagement.repo;

import com.FatimaElouafi.employeesmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserById(int id);
    Optional<User> deleteUserById(int id);
}