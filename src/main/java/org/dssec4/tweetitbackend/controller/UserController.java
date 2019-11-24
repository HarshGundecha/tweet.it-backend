package org.dssec4.tweetitbackend.controller;

import org.dssec4.tweetitbackend.config.JwtTokenUtil;
import org.dssec4.tweetitbackend.entity.Follower;
import org.dssec4.tweetitbackend.entity.Tweet;
import org.dssec4.tweetitbackend.entity.User;
import org.dssec4.tweetitbackend.model.JwtResponse;
import org.dssec4.tweetitbackend.service.FollowerService;
import org.dssec4.tweetitbackend.service.JwtUserDetailsService;
import org.dssec4.tweetitbackend.service.TweetService;
//import org.dssec4.tweetitbackend.service.UserFollowService;
import org.dssec4.tweetitbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private UserFollowService userFollowService;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private FollowerService followerService;

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

//    @GetMapping("/profile")
//    public ResponseEntity<?> getProfile() {
//        User user = userService.getUserFromRequest();
//        List<Tweet> tist = tweetService.getTweetsFromUser(user);
//        Map<String, Object> mymap = new HashMap();
//        mymap.put("tweet",tist);
//        mymap.put("user",user);
//        return ResponseEntity.ok(mymap);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        Map<String, Object> mymap = new HashMap();
        mymap.put("users",userService.getUsers());
        return ResponseEntity.ok(mymap);
    }

    @PutMapping
    public User putUser(@RequestBody User updatedUser){
        User user = userService.getUserFromRequest();
        if(user.getId()==updatedUser.getId())
        {
            user.setName(updatedUser.getName());
            user.setBio(updatedUser.getBio());
            return userService.save(user);
        }
        else
            return updatedUser;

    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchUser(@PathVariable String name) {
        Map<String, Object> mymap = new HashMap<>();
        List<User> users = userService.getUserBySimilarName(name);
        mymap.put("users",users.size()>0?users:false);
        return ResponseEntity.ok(mymap);
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<?> getUserFromUsername(@PathVariable String username) {
        System.out.println(username);
        User user = userService.getUserFromUsername(username);
        System.out.println(user);
        List<Tweet> tist = tweetService.getTweetsFromUser(user);
        Map<String, Object> mymap = new HashMap();
        mymap.put("tweet",tist);
        mymap.put("user",user);
        return ResponseEntity.ok(mymap);
    }

    @PutMapping("togglefollow/{id}")
    public User followUser(@PathVariable long id){
        followerService.toggleFollowUser(userService.getUserFromRequest(), id);
        return userService.getUser(id);
    }

    @GetMapping("/following")
    public List<User> getFollowing(){
        return userService.getUsersByUsersId(followerService.getFollowingIdsByUserId(userService.getUserFromRequest().getId()));
    }

    @GetMapping("/followers")
    public List<User> getFollowers(){
        return userService.getUsersByUsersId(followerService.getFollowersIdsByUserId(userService.getUserFromRequest().getId()));
    }
}