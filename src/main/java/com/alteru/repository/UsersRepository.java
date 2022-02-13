package com.alteru.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.alteru.models.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
}
