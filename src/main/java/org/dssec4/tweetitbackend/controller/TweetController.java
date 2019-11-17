package org.dssec4.tweetitbackend.controller;

import org.dssec4.tweetitbackend.entity.Tweet;
import org.dssec4.tweetitbackend.service.TweetService;
import org.dssec4.tweetitbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tweets")
@CrossOrigin
public class TweetController {
    @Autowired
    private TweetService tweetService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addTweet(@RequestBody Tweet tweet){
        tweetService.addTweet(tweet);
        List<Tweet> tist = tweetService.getTweetsFromUser(userService.getUserFromRequest());
        Map<String, Object> mymap = new HashMap();
        mymap.put("tweet",tist);
        return ResponseEntity.ok(mymap);
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
