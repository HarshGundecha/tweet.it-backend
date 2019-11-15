package org.dssec4.tweetitbackend.controller;

import org.dssec4.tweetitbackend.entity.Tweet;
import org.dssec4.tweetitbackend.entity.User;
import org.dssec4.tweetitbackend.service.TweetService;
import org.dssec4.tweetitbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TweetService tweetService;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        User user = userService.getUserFromRequest();
        List<Tweet> tist = tweetService.getTweetsFromUser(user);
        Object[] obj = new Object[2];
        obj[0] = user;
        obj[1] = tist;
        Map<String, Object> mymap = new HashMap();
        mymap.put("user",user);
        mymap.put("tweet",tist);
        return ResponseEntity.ok(mymap);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

}
