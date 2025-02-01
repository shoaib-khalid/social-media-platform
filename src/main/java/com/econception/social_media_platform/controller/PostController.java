package com.econception.social_media_platform.controller;

import com.econception.social_media_platform.entity.Post;
import com.econception.social_media_platform.entity.User;
import com.econception.social_media_platform.entity.Comment;
import com.econception.social_media_platform.entity.PostLike;
import com.econception.social_media_platform.repository.PostRepository;
import com.econception.social_media_platform.repository.CommentRepository;
import com.econception.social_media_platform.repository.PostLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostLikeRepository postLikeRepository;

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam int page, @RequestParam int size, @RequestParam String sort) {
        // Implement logic to retrieve all posts with pagination and sorting
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Post> getPostById(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setContent(postDetails.getContent());
        return postRepository.save(post);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "Post deleted";
    }

    @PostMapping("/{id}/comments")
    public Comment addComment(@PathVariable Long id, @RequestBody Comment comment) {
        Post post = postRepository.findById(id).orElseThrow();
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @PostMapping("/{id}/like")
    public String likePost(@PathVariable Long id, @RequestBody User user) {
        Post post = postRepository.findById(id).orElseThrow();
        PostLike postLike = new PostLike();
        postLike.setPost(post);
        postLike.setUser(user);
        postLikeRepository.save(postLike);
        return "Post liked";
    }

    @PostMapping("/search")
    public List<Post> searchPosts(@RequestParam String keyword) {
        //return postRepository.findByContentContaining(keyword, keyword);
        return null;
    }
}
