package com.indatacore.test.repositories;

import com.indatacore.test.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String userName);
}
