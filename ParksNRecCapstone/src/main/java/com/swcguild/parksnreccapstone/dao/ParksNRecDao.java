/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.parksnreccapstone.dao;

import com.swcguild.parksnreccapstone.model.BlogPost;
import com.swcguild.parksnreccapstone.model.Category;
import com.swcguild.parksnreccapstone.model.Tag;
import java.util.List;

/**
 *
 * @author Winona Wixson & Patrick Culp
 */
public interface ParksNRecDao {

    public BlogPost createPost(BlogPost post);
    
    public Tag createTag(Tag tag);
    
    public Category createCategory(Category category);   
    
    public void addBlogPostTags(List<Tag> tags, int postId);
    
    public void addBlogPostCategory(Category category, int postId);

    public List<BlogPost> getAllPosts();

    public BlogPost getPostById(int id);

    public List<BlogPost> getAllPostsByTagId(int id);

    public List<BlogPost> getAllPostsByCategoryId(int id);
    
    public List<BlogPost> getRecentPosts(int numberOfPosts);

    public void editPost(BlogPost post);
    
    public void updateBlogPostCategory(int categoryId, int postId);

    public void deletePostById(int id);

    public void deleteTagById(int id);

    public void deleteCategoryById(int id);

    public List<Tag> getAllTags();

    public List<Category> getAllCategories();
}
