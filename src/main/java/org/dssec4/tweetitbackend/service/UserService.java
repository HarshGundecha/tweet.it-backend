package org.dssec4.tweetitbackend.service;

import org.dssec4.tweetitbackend.config.JwtTokenUtil;
import org.dssec4.tweetitbackend.entity.User;
import org.dssec4.tweetitbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private FollowerService followerService;

    public User save(User user) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserFromRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        String email = jwtTokenUtil.getUsernameFromToken(token);
//        return setFollowersFollowingCount(userRepository.findByEmail(email)); // will create a DETACHED instance
        return userRepository.findByEmail(email);
    }
    public User getUserFromEmail(String username) {
        return userRepository.findByEmail(username);
    }

    public User getUserFromUsername(String username) {
        return setFollowersFollowingCount(userRepository.findByUsername(username));
    }

    public User getUser(Long id) {
        return setFollowersFollowingCount(userRepository.findById(id).get());
    }

    public User setFollowersFollowingCount(User user){
        return new User(
            user,
            new Long[]{
                Long.valueOf(followerService.getFollowingIdsByUserId(user.getId()).size()),
                Long.valueOf(followerService.getFollowersIdsByUserId(user.getId()).size())
            }
        );
    }

    public List<User> getUserBySimilarName(String name){
        return userRepository.findUserBySimilarName(name);
    }

    public List<User> getUsersByUsersId(List<Long> usersId){
        return userRepository.findAllById(usersId);
    }

}
