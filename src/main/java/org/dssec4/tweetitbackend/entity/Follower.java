package org.dssec4.tweetitbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
//import org.dssec4.tweetitbackend.model.Friends;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
//@IdClass(Friends.class)
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Id
    private Long userId;
//    @Id
    private Long friendId;
}
