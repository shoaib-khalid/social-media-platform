package com.econception.social_media_platform.controller;

import com.econception.social_media_platform.entity.User;
import com.econception.social_media_platform.entity.Follow;
import com.econception.social_media_platform.repository.UserRepository;
import com.econception.social_media_platform.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        // Implement user authentication logic
        return "User logged in";
    }

    @GetMapping("/{id}")
    public Optional<User> getUserProfile(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping("/{id}/follow")
    public String followUser(@PathVariable Long id, @RequestBody User follower) {
        User followed = userRepository.findById(id).orElseThrow();
        // Follow follow = new Follow();
        // follow.setFollower(follower);
        // follow.setFollowed(followed);
        // followRepository.save(follow);
        return "User followed";
    }

    @GetMapping("/{id}/followers")
    public List<Follow> getUserFollowers(@PathVariable Long id) {
        // User user = userRepository.findById(id).orElseThrow();
        // return followRepository.findByFollowed(user);
        return null;
    }

    @GetMapping("/{id}/following")
    public List<Follow> getUserFollowing(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return followRepository.findAll();
    }

    @PostMapping("/search")
    public List<User> searchUsers(@RequestParam String keyword) {
        return userRepository.findAll();
    }
}