package org.dssec4.tweetitbackend.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Tweet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "tweet" ,cascade = CascadeType.REMOVE)
    @JoinColumn
    private Comment comment;

    @Column(nullable=false)
    private String tweetText;

    private List<String> image;

    private List<String> video;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}