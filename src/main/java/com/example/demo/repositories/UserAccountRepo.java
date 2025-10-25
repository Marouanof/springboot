package com.example.demo.repositories;

import com.example.demo.entities.Employee;
import com.example.demo.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface
UserAccountRepo extends JpaRepository<UserAccount, UUID> {
    Optional<UserAccount> findOneByUsername(String username);
}
