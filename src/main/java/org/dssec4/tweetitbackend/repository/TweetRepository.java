package org.dssec4.tweetitbackend.repository;

import org.dssec4.tweetitbackend.entity.Tweet;
import org.dssec4.tweetitbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findByUser(User tweet);
}
