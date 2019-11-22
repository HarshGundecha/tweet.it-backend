package org.dssec4.tweetitbackend.controller;

import org.dssec4.tweetitbackend.entity.Comment;
import org.dssec4.tweetitbackend.entity.Tweet;
import org.dssec4.tweetitbackend.entity.User;
import org.dssec4.tweetitbackend.service.CommentService;
import org.dssec4.tweetitbackend.service.TweetService;
import org.dssec4.tweetitbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        comment.setUser(userService.getUserFromRequest());
        comment = commentService.addComment(comment);
        Tweet tweet = tweetService.getTweet(comment.getTweet().getId()).get();
        return ResponseEntity.ok(tweet);
    }

    @GetMapping
    public ResponseEntity<?> getComments() {
        return ResponseEntity.ok(commentService.getComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getComment(id));
    }

    @PutMapping("/{id}/togglelike")
    public ResponseEntity<?> toggleCommentLike(@PathVariable Long id) {
        Comment comment = commentService.getComment(id).get();
        User user = userService.getUserFromRequest();

        if (comment.getLikes().contains(user))
            comment.getLikes().remove(user);
        else
            comment.getLikes().add(user);
        Tweet tweet = tweetService.getTweet(comment.getTweet().getId()).get();

        return ResponseEntity.ok(tweet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        Tweet tweet = commentService.getComment(id).get().getTweet();
        commentService.deleteComment(id);
        return ResponseEntity.ok(tweet);
    }

}
