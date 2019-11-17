package org.dssec4.tweetitbackend.controller;

import org.dssec4.tweetitbackend.config.JwtTokenUtil;
import org.dssec4.tweetitbackend.entity.Tweet;
import org.dssec4.tweetitbackend.entity.User;
import org.dssec4.tweetitbackend.model.JwtResponse;
import org.dssec4.tweetitbackend.service.JwtUserDetailsService;
import org.dssec4.tweetitbackend.service.TweetService;
import org.dssec4.tweetitbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
//        return ResponseEntity.ok(userService.save(user));
        userService.save(user);
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
//		System.out.println(userDetails.getUsername()+" is my name");
        Map<String, Object> mymap = new HashMap();
        mymap.put("token",new JwtResponse(token).getToken());
        mymap.put("user",user);
        return ResponseEntity.ok(mymap);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        User user = userService.getUserFromRequest();
        List<Tweet> tist = tweetService.getTweetsFromUser(user);
        Map<String, Object> mymap = new HashMap();
        mymap.put("tweet",tist);
        mymap.put("user",user);
        return ResponseEntity.ok(mymap);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

}
