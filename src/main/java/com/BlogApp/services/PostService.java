package com.BlogApp.services;

import com.BlogApp.entities.Post;
import com.BlogApp.payloads.PostDto;
import com.BlogApp.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);
    PostDto getPostById(Integer postId);
    List<PostDto> getPostByCategory(Integer categoryID);
    List<PostDto> getPostByUser(Integer userId);


    //SearchPost
    List<PostDto>searchPosts(String search);

}
