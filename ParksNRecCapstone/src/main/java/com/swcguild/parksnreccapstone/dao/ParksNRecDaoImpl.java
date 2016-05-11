/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.parksnreccapstone.dao;

import com.swcguild.parksnreccapstone.model.BlogPost;
import com.swcguild.parksnreccapstone.model.Category;
import com.swcguild.parksnreccapstone.model.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Winona Wixson
 */
public class ParksNRecDaoImpl implements ParksNRecDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //SQL statements used in several methods
    private static final String SQL_GET_ALL_TAGS_BY_POST_ID = "SELECT Tags.id, Tags.tagName \n"
            + "FROM Tags\n"
            + "INNER JOIN BlogPostTags\n"
            + "ON Tags.id = BlogPostTags.tagId\n"
            + "WHERE BlogPostTags.blogPostId = ?";
    private static final String SQL_GET_CATEGORY_BY_POST_ID = "SELECT Categories.id, Categories.categoryName\n"
            + "FROM Categories\n"
            + "INNER JOIN BlogPostCategories\n"
            + "ON Categories.id = BlogPostCategories.categoryId\n"
            + "WHERE BlogPostCategories.blogPostId= ?";

    private void setBlogPostTagsAndCategories(List<BlogPost> allPosts) {

        for (BlogPost post : allPosts) {
            try {
                List<Tag> tagsByPostId = jdbcTemplate.query(SQL_GET_ALL_TAGS_BY_POST_ID, new TagMapper(), post.getId());
               
                post.setTags(tagsByPostId);
            } catch (EmptyResultDataAccessException ex) {
                Logger.getLogger(ParksNRecDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                Category category = jdbcTemplate.queryForObject(SQL_GET_CATEGORY_BY_POST_ID, new CategoryMapper(), post.getId());

                post.setCategory(category);
               
            } catch (EmptyResultDataAccessException ex) {
                Logger.getLogger(ParksNRecDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    //sql query for next method
    private static final String SQL_CREATE_POST = "INSERT INTO BlogPosts "
            + "(title, author, postContent, approved)\n"
            + "VALUES (?, ?, ?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public BlogPost createPost(BlogPost post) {
        jdbcTemplate.update(SQL_CREATE_POST, post.getTitle(), post.getAuthor(), post.getPostContent(), post.isApproved());

        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        post.setId(id);
        return post;
    }

    private static final String SQL_CREATE_TAG = "INSERT INTO Tags (tagName) VALUES (?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Tag createTag(Tag tag) {
        jdbcTemplate.update(SQL_CREATE_TAG, tag.getTag());

        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        tag.setiD(id);
        return tag;
    }

    private static final String SQL_CREATE_CATEGORY = "INSERT INTO Categories (categoryName) VALUES (?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Category createCategory(Category category) {
        jdbcTemplate.update(SQL_CREATE_CATEGORY, category.getCategory());

        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        category.setiD(id);
        return category;
    }

    private static final String SQL_ADD_POST_TAGS = "INSERT INTO BlogPostTags (blogPostId, tagId) VALUES (?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addBlogPostTags(List<Tag> tags, int postId) {
        for (Tag t : tags) {
            jdbcTemplate.update(SQL_ADD_POST_TAGS, postId, t.getiD());
        }
    }

    private static final String SQL_ADD_POST_CATEGORY = "INSERT INTO BlogPostCategories (blogPostId, categoryId) VALUES (?, ?)";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addBlogPostCategory(Category category, int postId) {
        jdbcTemplate.update(SQL_ADD_POST_CATEGORY, postId, category.getiD());
    }

    private static final String SQL_GET_ALL_POSTS = "SELECT * FROM BlogPosts "
            + "WHERE 1=1 ORDER BY currentDate DESC";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<BlogPost> getAllPosts() {
        List<BlogPost> allPosts = jdbcTemplate.query(SQL_GET_ALL_POSTS, new BlogPostMapper());
        this.setBlogPostTagsAndCategories(allPosts);
        return allPosts;
    }

    private static final String SQL_GET_RECENT_POSTS = "SELECT * FROM BlogPosts ORDER BY currentDate DESC LIMIT ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<BlogPost> getRecentPosts(int numberOfPosts) {
        List<BlogPost> recentPosts = jdbcTemplate.query(SQL_GET_RECENT_POSTS, new BlogPostMapper(), numberOfPosts);

        this.setBlogPostTagsAndCategories(recentPosts);

        return recentPosts;
    }

    private static final String SQL_GET_POST_BY_ID = "SELECT * FROM BlogPosts WHERE id = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public BlogPost getPostById(int id) {
        BlogPost post = jdbcTemplate.queryForObject(SQL_GET_POST_BY_ID, new BlogPostMapper(), id);
        try {
            List<Tag> tagsByPostId = jdbcTemplate.query(SQL_GET_ALL_TAGS_BY_POST_ID, new TagMapper(), id);

            post.setTags(tagsByPostId);
        } catch (EmptyResultDataAccessException ex) {
            Logger.getLogger(ParksNRecDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Category category = jdbcTemplate.queryForObject(SQL_GET_CATEGORY_BY_POST_ID, new CategoryMapper(), id);

            post.setCategory(category);
        } catch (EmptyResultDataAccessException ex) {
            Logger.getLogger(ParksNRecDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return post;
    }

    private static final String SQL_GET_ALL_POSTS_BY_TAG_ID = "SELECT * FROM BlogPostTags\n"
            + "LEFT JOIN BlogPosts\n"
            + "ON BlogPostTags.blogPostId = BlogPosts.id\n"
            + "WHERE BlogPostTags.tagId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<BlogPost> getAllPostsByTagId(int id) {
        List<BlogPost> postsByTagId = jdbcTemplate.query(SQL_GET_ALL_POSTS_BY_TAG_ID, new BlogPostMapper(), id);

        this.setBlogPostTagsAndCategories(postsByTagId);

        return postsByTagId;
    }

    private static final String SQL_GET_ALL_TAGS = "SELECT * FROM Tags WHERE 1=1";

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(SQL_GET_ALL_TAGS, new TagMapper());
    }

    private static final String SQL_GET_ALL_CATEGORIES = "SELECT * FROM Categories WHERE 1=1";

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(SQL_GET_ALL_CATEGORIES, new CategoryMapper());
    }

    private static final String SQL_GET_ALL_POSTS_BY_CATEGORY_ID = "SELECT * FROM BlogPostCategories\n"
            + "LEFT JOIN BlogPosts\n"
            + "ON BlogPostCategories.blogPostId = BlogPosts.id\n"
            + "WHERE BlogPostCategories.categoryId =  ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<BlogPost> getAllPostsByCategoryId(int id) {
        List<BlogPost> postsByCategoryId = jdbcTemplate.query(SQL_GET_ALL_POSTS_BY_CATEGORY_ID, new BlogPostMapper(), id);

        this.setBlogPostTagsAndCategories(postsByCategoryId);

        return postsByCategoryId;
    }

    private static final String SQL_EDIT_POST = "UPDATE BlogPosts SET title = ?, "
            + "author = ?, postContent = ?, approved = ? WHERE id = ?";

    @Override
    public void editPost(BlogPost post) {
        jdbcTemplate.update(SQL_EDIT_POST, post.getTitle(), post.getAuthor(), post.getPostContent(), post.isApproved(), post.getId());
    }
    
    
    
    private static final String SQL_UPDATE_BLOG_POST_CATEGORIES = "UPDATE BlogPostCategories SET categoryId = ? WHERE blogPostId = ?";
    
    @Override
    public void updateBlogPostCategory(int categoryId, int postId){
        jdbcTemplate.update(SQL_UPDATE_BLOG_POST_CATEGORIES, categoryId, postId);
    }
    
    private static final String SQL_DELETE_POST_BY_ID = "DELETE FROM BlogPosts WHERE id = ?";
    private static final String SQL_GET_ALL_TAG_IDS_FROM_BLOGPOSTTAGS = "SELECT BlogPostTags.tagId\n"
            + "FROM BlogPostTags\n"
            + "WHERE 1=1";
    private static final String SQL_GET_ALL_CATEGORY_IDS_FROM_BLOGPOSTCATEGORIES = "SELECT BlogPostCategories.categoryId\n"
            + "FROM BlogPostCategories\n"
            + "WHERE 1=1";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deletePostById(int id) {
        //getting all the tags that are associated with the post to delete, so we can possibly delete them

        List<Tag> allTagsByPostId = null;
        Category category = null;

        try {
            category = jdbcTemplate.queryForObject(SQL_GET_CATEGORY_BY_POST_ID, new CategoryMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            Logger.getLogger(ParksNRecDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IncorrectResultSizeDataAccessException ex) {
            Logger.getLogger(ParksNRecDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            allTagsByPostId = jdbcTemplate.query(SQL_GET_ALL_TAGS_BY_POST_ID, new TagMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            Logger.getLogger(ParksNRecDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IncorrectResultSizeDataAccessException ex) {
            Logger.getLogger(ParksNRecDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        //delete the post, which should delete the references in BlogPostTags to that post
        jdbcTemplate.update(SQL_DELETE_POST_BY_ID, id);

        //check each tag from allTagsByPostId and see if it's still in BlogPostTags
        //if not in BlogPostTags, delete the tag
        //1.get all tag ids from blogPostTags
        //2. for loop, to check each
        //3. possibly delete tag
        List<Integer> tagIdsThatStillExist = null;

        try {
            tagIdsThatStillExist = jdbcTemplate.queryForList(SQL_GET_ALL_TAG_IDS_FROM_BLOGPOSTTAGS, Integer.class);
        } catch (EmptyResultDataAccessException ex) {
            Logger.getLogger(ParksNRecDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IncorrectResultSizeDataAccessException ex) {
            Logger.getLogger(ParksNRecDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean doNotDelete = false;

        if (tagIdsThatStillExist != null && tagIdsThatStillExist.size() > 0) {
            for (Tag t : allTagsByPostId) {
                for (Integer i : tagIdsThatStillExist) {
                    if (t.getiD() == i) {
                        doNotDelete = true;
                    }
                }
                if (!doNotDelete) {
                    this.deleteTagById(t.getiD());
                }
                doNotDelete = false;
            }
        } else if (allTagsByPostId != null && allTagsByPostId.size() > 0) {
            for (Tag t : allTagsByPostId) {
                this.deleteTagById(t.getiD());
            }
        }

        List<Integer> categoryIdsThatStillExist = null;
        try {
            jdbcTemplate.queryForList(SQL_GET_ALL_CATEGORY_IDS_FROM_BLOGPOSTCATEGORIES, Integer.class);
        } catch (EmptyResultDataAccessException ex) {
            Logger.getLogger(ParksNRecDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IncorrectResultSizeDataAccessException ex) {
            Logger.getLogger(ParksNRecDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (categoryIdsThatStillExist != null && categoryIdsThatStillExist.size() > 0) {
            for (Integer i : categoryIdsThatStillExist) {
                if (i == category.getiD()) {
                    doNotDelete = true;
                }
            }
            if (!doNotDelete) {
                this.deleteCategoryById(category.getiD());
            }
        } else if (category != null) {
            this.deleteCategoryById(category.getiD());
        }
    }

    private static final String SQL_DELETE_TAG_BY_ID = "DELETE FROM Tags WHERE id = ?";

    @Override
    public void deleteTagById(int id) {
        jdbcTemplate.update(SQL_DELETE_TAG_BY_ID, id);
    }

    private static final String SQL_DELETE_CATEGORY_BY_ID = "DELETE FROM Categories WHERE id = ?";

    @Override
    public void deleteCategoryById(int id) {
        jdbcTemplate.update(SQL_DELETE_CATEGORY_BY_ID, id);
    }

    private static final class BlogPostMapper implements RowMapper<BlogPost> {

        @Override
        public BlogPost mapRow(ResultSet rs, int i) throws SQLException {
            BlogPost post = new BlogPost();
            post.setId(rs.getInt("id"));
            post.setTitle(rs.getString("title"));
            post.setAuthor(rs.getString("author"));
            post.setPostContent(rs.getString("postContent"));
            post.setDateTime(rs.getTimestamp("currentDate").toLocalDateTime().toLocalDate());
            post.setApproved(rs.getBoolean("approved"));

            return post;
        }

    }

    private static final class TagMapper implements RowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet rs, int i) throws SQLException {
            Tag tag = new Tag();
            tag.setiD(rs.getInt("id"));
            tag.setTag(rs.getString("tagName"));

            return tag;
        }

    }

    private static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {
            Category category = new Category();
            category.setiD(rs.getInt("id"));
            category.setCategory(rs.getString("categoryName"));

            return category;
        }

    }

}
