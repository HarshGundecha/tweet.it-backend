package org.dssec4.tweetitbackend.controller;

import org.dssec4.tweetitbackend.entity.Tweet;
import org.dssec4.tweetitbackend.service.TweetService;
import org.dssec4.tweetitbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tweet")
public class TweetController {
    @Autowired
    private TweetService tweetService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Tweet> addTweet(@RequestBody Tweet tweet){
        return ResponseEntity.ok(tweetService.addTweet(tweet));
    }

    @GetMapping
    public ResponseEntity<?> getTweets(){
        return ResponseEntity.ok(tweetService.getTweets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTweet(@PathVariable Long id){
        return ResponseEntity.ok(tweetService.getTweet(id));
    }

}
