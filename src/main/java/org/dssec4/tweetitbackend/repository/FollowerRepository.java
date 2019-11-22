package org.dssec4.tweetitbackend.repository;

import org.dssec4.tweetitbackend.entity.Follower;
//import org.dssec4.tweetitbackend.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
    Follower findByUserIdAndFriendId(Long id1, Long id2);

    Follower deleteByUserIdAndFriendId(Long id1, Long id2);

    Follower save(Follower follower);

    @Query("select f.friendId from Follower f where f.userId = ?1 ")
    List<Long> findFriendIdsByUsersId(Long userId);

    @Query("select f.userId from Follower f where f.friendId = ?1 ")
    List<Long> findUserIdsByFriendsId(Long userId);

}
