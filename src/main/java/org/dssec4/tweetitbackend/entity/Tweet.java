package org.dssec4.tweetitbackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comment = new ArrayList();

    @Column(nullable=false)
    private String tweetText;

    @ElementCollection
    private List<String> image = new ArrayList();

    @ElementCollection
    private List<String> video = new ArrayList();

    @ManyToMany
    private Set<User> likes/* = new HashSet()*/;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}