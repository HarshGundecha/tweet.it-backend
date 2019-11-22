package org.dssec4.tweetitbackend.controller;

import org.dssec4.tweetitbackend.entity.Tweet;
import org.dssec4.tweetitbackend.entity.User;
import org.dssec4.tweetitbackend.service.FollowerService;
import org.dssec4.tweetitbackend.service.TweetService;
import org.dssec4.tweetitbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/tweets")
@CrossOrigin
public class TweetController {
    @Autowired
    private TweetService tweetService;

    @Autowired
    private UserService userService;

    @Autowired
    private FollowerService followerService;

    @PostMapping
    public ResponseEntity<?> addTweet(@RequestBody Tweet tweet){
        tweet.setUser(userService.getUserFromRequest());
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

    @PutMapping("/{id}/togglelike")
    public ResponseEntity<?> toggleTweetLike(@PathVariable Long id) {
        Tweet tweet = tweetService.getTweet(id).get();
        User user = userService.getUserFromRequest();

        if (tweet.getLikes().contains(user))
            tweet.getLikes().remove(user);
        else
            tweet.getLikes().add(user);

        return ResponseEntity.ok(tweetService.addTweet(tweet));
    }

    @DeleteMapping("/{id}")
    public boolean deleteTweet(@PathVariable Long id){
        return tweetService.deleteTweet(id);
    }

    @GetMapping("/feeds")
    public ResponseEntity<?> getFeeds(){
        User user = userService.getUserFromRequest();
        List<Long> followingIds = followerService.getFollowingIdsByUserId(user.getId());
        List<User> users = userService.getUsersByUsersId(followingIds);
        List<Tweet> tist = tweetService.getTweetsFromUserIds(users);
        Map<String, Object> mymap = new HashMap();
        mymap.put("user", user);
        mymap.put("tweet", tist);
        return ResponseEntity.ok(mymap);
    }
}
