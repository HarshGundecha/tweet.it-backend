package org.dssec4.tweetitbackend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "myuser")
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@UniqueElements
	@Column(nullable=false, unique = true)
	private String username;

	@Column(nullable=false)
	private String name;

	@Column(nullable=false, unique = true)
	private String email;

	private String profilePic;

	private String coverPic;

	private String bio;

	@Column(nullable=false)
	private String password;

	private Long followingCount;

	private Long followersCount;

	//@JsonIgnore
	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	public User(User user, Long followData[]) {
		this.setId(user.getId());
		this.setName(user.getName());
		this.setUsername(user.getUsername());
		this.setEmail(user.getEmail());
		this.setBio(user.getBio());
		this.setCoverPic(user.getCoverPic());
		this.setProfilePic(user.getProfilePic());
		this.setFollowingCount(followData[0]);
		this.setFollowersCount(followData[1]);
		this.setCreatedAt(user.getCreatedAt());
		this.setUpdatedAt(user.getCreatedAt());
		this.setPassword(user.getPassword());
	}
}