package com.BlogApp.repositories;

import com.BlogApp.entities.Category;
import com.BlogApp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import com.BlogApp.entities.User;
import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);
    List<Post> findByTitleContainingIgnoreCase(String title);
    List<Post> findByContentContainingIgnoreCase(String content);
}
