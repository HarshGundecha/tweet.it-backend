package org.dssec4.tweetitbackend.service;

import org.dssec4.tweetitbackend.entity.Tweet;
import org.dssec4.tweetitbackend.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
