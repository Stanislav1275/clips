package com.aikoni6.project.Controllers;

import com.aikoni6.project.Entities.posting.Post;
import com.aikoni6.project.Repositories.posting.PostRepo;
import com.aikoni6.project.Services.posting.PostService;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepo postRepo;

    @SneakyThrows
    @CrossOrigin
    @GetMapping("post/{post_id}")
    public ResponseEntity<String> getPostByID(@PathVariable("post_id") Long ID){
        if(ID == null) return ResponseEntity.badRequest().build();
        var res = postService.getPostByID(ID);
        if(res != null) return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(res));
        //TODO: error handling
        return ResponseEntity.badRequest().body("Internal error");
    }

    @SneakyThrows
    @CrossOrigin
    @GetMapping("post")
    public ResponseEntity<String> getPostsBySomething(@RequestParam(value = "TOKEN", required = false) String token,
                                                      @RequestParam("SUBCATEGORY") Long ID){
        List<Post> res;
        if(ID != null && ID >= 0) res = postService.getPostsBySubcategory(ID, token);
        else res = postService.getPostsByCategory(ID, token);

        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(res));
    }

    @CrossOrigin
    @PostMapping("post")
    @SneakyThrows
    public ResponseEntity<String> savePost(@RequestParam("TOKEN") String token, @RequestBody Post post){
        var res = postService.createPost(post);
        if(res == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(res));
    }

}
