package org.dssec4.tweetitbackend.controller;

import org.dssec4.tweetitbackend.entity.Tweet;
import org.dssec4.tweetitbackend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TweetController {
    @Autowired
    private TweetService tweetService;

    @PostMapping("/tweet")
    public ResponseEntity<Tweet> addTweet(@RequestBody Tweet tweet){
        return ResponseEntity.ok(tweetService.addTweet(tweet));
    }

    @GetMapping("/tweet")
    public ResponseEntity<?> getTweets(){
        return ResponseEntity.ok(tweetService.getTweets());
    }

}
