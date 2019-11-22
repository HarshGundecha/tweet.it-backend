package org.dssec4.tweetitbackend.service;

import org.dssec4.tweetitbackend.entity.Follower;
import org.dssec4.tweetitbackend.entity.User;
//import org.dssec4.tweetitbackend.model.Friends;
import org.dssec4.tweetitbackend.repository.FollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowerService {

    @Autowired
    private FollowerRepository followerRepository;

    public Follower toggleFollowUser(User user, Long friendId){
        if(followerRepository.findByUserIdAndFriendId(user.getId(),friendId)!=null)
            return followerRepository.deleteByUserIdAndFriendId(user.getId(),friendId);
        else {
            Follower follower = new Follower();
            follower.setUserId(user.getId());
            follower.setFriendId(friendId);
            return followerRepository.save(follower);
        }
    }

    public List<Long> getFollowingIdsByUserId(Long userId){
        return followerRepository.findFriendIdsByUsersId(userId);
    }

    public List<Long> getFollowersIdsByUserId(Long userId){
        return followerRepository.findUserIdsByFriendsId(userId);
    }

}
