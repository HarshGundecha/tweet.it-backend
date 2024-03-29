package org.dssec4.tweetitbackend.service;

import org.dssec4.tweetitbackend.entity.Comment;
import org.dssec4.tweetitbackend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getComment(Long id) {
        return commentRepository.findById(id);
    }

    public boolean deleteComment(Long id) {
        boolean flag =  commentRepository.findById(id).get().getUser()==userService.getUserFromRequest();
        if(flag)
            commentRepository.deleteById(id);
        return flag;
    }
}
