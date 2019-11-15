package org.dssec4.tweetitbackend.service;

import org.dssec4.tweetitbackend.entity.Tweet;
import org.dssec4.tweetitbackend.entity.User;
import org.dssec4.tweetitbackend.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetService {
    @Autowired
    private TweetRepository tweetRepository;

    public Tweet addTweet(Tweet tweet){
        return tweetRepository.save(tweet);
    }

    public List<Tweet> getTweets(){
        return tweetRepository.findAll();
    }

    public Optional<Tweet> getTweet(Long id){
        return tweetRepository.findById(id);
    }

    public List<Tweet> getTweetsFromUser(User user){
        return tweetRepository.findByUser(user);
    }

}
