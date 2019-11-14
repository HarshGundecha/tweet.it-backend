package org.dssec4.tweetitbackend.controller;

import org.dssec4.tweetitbackend.entity.Comment;
import org.dssec4.tweetitbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.addComment(comment));
    }

    @GetMapping("/")
    public ResponseEntity<?> getComments() {
        return ResponseEntity.ok(commentService.getComments());
    }
}
