package com.aikoni6.project.Repositories.posting;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aikoni6.project.Entities.posting.Post;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    public List<Post> findBySubcategory(Long subcategory);
}
