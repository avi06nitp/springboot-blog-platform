package com.BlogApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.BlogApp.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {
}
