package org.dssec4.tweetitbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.Date;

@Data
@Entity
@Table(name = "myuser")
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
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
	//@JsonIgnore
	@CreationTimestamp
	private Date createdAt;
	@UpdateTimestamp
	private Date updatedAt;
	@Column(nullable=false)
	private String password;
}