package com.aikoni6.project.Services.posting;

import com.aikoni6.project.Repositories.posting.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aikoni6.project.Entities.posting.Post;

import java.util.List;

//TODO: implement recommendations
@Service
public class PostService {

    private final PostRepo postRepo;
    @Autowired
    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public Post getPostByID(Long ID){
        return postRepo.findById(ID).orElse(null);
    }
    public Post getPostsByRecommendations(String token){
        return null;
    }

    public List<Post> getPostsBySubcategory(Long subcategoryID, String token) {
        return null;
    }

    public List<Post> getPostsByCategory(Long subcategoryID, String token) {
        return null;
    }

    public Object getPostByRecommendations() {
        return null;
    }

    public Post createPost(Post post){
        return postRepo.save(post);
    }
}
