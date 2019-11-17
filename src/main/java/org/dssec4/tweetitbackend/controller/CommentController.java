package org.dssec4.tweetitbackend.controller;

import org.dssec4.tweetitbackend.entity.Comment;
import org.dssec4.tweetitbackend.entity.Tweet;
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

@Repository
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
        commentService.addComment(comment);
        List<Tweet> tist = tweetService.getTweetsFromUser(userService.getUserFromRequest());
        Map<String, Object> mymap = new HashMap();
        mymap.put("tweet",tist);
        return ResponseEntity.ok(mymap);
    }

    @GetMapping
    public ResponseEntity<?> getComments() {
        return ResponseEntity.ok(commentService.getComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getComment(id));
    }
}
