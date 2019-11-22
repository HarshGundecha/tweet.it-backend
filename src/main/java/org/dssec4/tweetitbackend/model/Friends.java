//package org.dssec4.tweetitbackend.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//@Data
//@RequiredArgsConstructor
//@AllArgsConstructor
//public class Friends implements Serializable{
//    private Long userId;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Friends friends = (Friends) o;
//        return userId.equals(friends.userId) &&
//                friendId.equals(friends.friendId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, friendId);
//    }
//
//    private Long friendId;
//}
