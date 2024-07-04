package com.BlogApp.services.impl;

import com.BlogApp.entities.Comment;
import com.BlogApp.entities.Post;
import com.BlogApp.exception.ResourceNotFoundException;
import com.BlogApp.payloads.CommentDto;
import com.BlogApp.repositories.CommentRepo;
import com.BlogApp.repositories.PostRepo;
import com.BlogApp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepo.save(comment);

        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        // Implementation code here
    }
}
