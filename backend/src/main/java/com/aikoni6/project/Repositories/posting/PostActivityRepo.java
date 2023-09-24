package com.aikoni6.project.Repositories.posting;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aikoni6.project.Entities.posting.PostActivity;

import java.util.List;

public interface PostActivityRepo extends JpaRepository<PostActivity, Long> {
    public List<PostActivity> findByPost(Long post);
}
