/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.parknreccapstone.dao;

import com.swcguild.parksnreccapstone.dao.ParksNRecDao;
import com.swcguild.parksnreccapstone.model.BlogPost;
import com.swcguild.parksnreccapstone.model.Category;
import com.swcguild.parksnreccapstone.model.Tag;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Winona Wixson
 */
public class ParksNRecDaoTest {

    private ParksNRecDao dao;

    public ParksNRecDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("testDao", ParksNRecDao.class);

        JdbcTemplate cleaner = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        cleaner.execute("DELETE FROM BlogPosts");
        cleaner.execute("DELETE FROM Tags");
        cleaner.execute("DELETE FROM Categories");
        cleaner.execute("DELETE FROM BlogPostTags");
        cleaner.execute("DELETE FROM BlogPostCategories");
    }

    @After
    public void tearDown() {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//
////        JdbcTemplate cleaner = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
////        cleaner.execute("DELETE FROM BlogPosts");
////        cleaner.execute("DELETE FROM Tags");
////        cleaner.execute("DELETE FROM Categories");
////        cleaner.execute("DELETE FROM BlogPostTags");
////        cleaner.execute("DELETE FROM BlogPostCategories");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void createPostGetById() {
        BlogPost post = new BlogPost();
        post.setTitle("National Park");
        post.setAuthor("Rene");
        post.setPostContent("parks are cool");
        post.setApproved(true);

        dao.createPost(post);

        Assert.assertEquals(post, dao.getPostById(post.getId()));

    }

    @Test
    public void createTagGetAll() {
        Tag tag = new Tag();
        tag.setTag("#money");

        dao.createTag(tag);

        List<Tag> listTags = dao.getAllTags();

        Assert.assertEquals(1, listTags.size());
    }

    @Test
    public void createCategoryGetAll() {
        Category category = new Category();
        category.setCategory("Camping");

        dao.createCategory(category);
        List<Category> listCategories = dao.getAllCategories();

        Assert.assertEquals(1, listCategories.size());
    }

    @Test
    public void addBlogPostTags() {
        BlogPost post = new BlogPost();
        post.setTitle("National Park");
        post.setAuthor("Rene");
        post.setPostContent("parks are cool");
        post.setApproved(true);

        dao.createPost(post);

        Tag tag = new Tag();
        tag.setTag("#money");

        dao.createTag(tag);

        Tag tag2 = new Tag();
        tag2.setTag("#moneyTime");

        dao.createTag(tag2);

        List<Tag> listTags = new ArrayList<>();
        listTags.add(tag);
        listTags.add(tag2);

        dao.addBlogPostTags(listTags, post.getId());

        BlogPost postToTest = dao.getPostById(post.getId());

        Assert.assertEquals(2, postToTest.getTags().size());

    }

    @Test
    public void addBlogPostCategories() {
        BlogPost post = new BlogPost();
        post.setTitle("National Park");
        post.setAuthor("Rene");
        post.setPostContent("parks are cool");
        post.setApproved(true);

        dao.createPost(post);

        Category category = new Category();
        category.setCategory("Camp");

        dao.createCategory(category);

        dao.addBlogPostCategory(category, post.getId());

        BlogPost postToTest = dao.getPostById(post.getId());

        Assert.assertEquals(category, postToTest.getCategory());
    }

    @Test
    public void getAllPostsWithTagsAndCategories() {
        //creating two posts that have different categories but the same tags
        //one post is approved, the other is not -- shouldn't affect anything with the dao
        BlogPost post = new BlogPost();
        post.setTitle("National Park");
        post.setAuthor("Rene");
        post.setPostContent("parks are cool");
        post.setApproved(true);
        dao.createPost(post);

        BlogPost post2 = new BlogPost();
        post2.setTitle("National Stuff");
        post2.setAuthor("Rene");
        post2.setPostContent("parks meh");
        post2.setApproved(false);
        dao.createPost(post2);

        Category category = new Category();
        category.setCategory("Camp");
        dao.createCategory(category);
        dao.addBlogPostCategory(category, post.getId());

        Category category2 = new Category();
        category2.setCategory("Triangle");
        dao.createCategory(category2);
        dao.addBlogPostCategory(category2, post2.getId());

        Tag tag = new Tag();
        tag.setTag("#money");
        dao.createTag(tag);

        Tag tag2 = new Tag();
        tag2.setTag("#moneyTime");
        dao.createTag(tag2);

        List<Tag> listTags = new ArrayList<>();
        listTags.add(tag);
        listTags.add(tag2);
        dao.addBlogPostTags(listTags, post.getId());
        dao.addBlogPostTags(listTags, post2.getId());

        List<BlogPost> allPosts = dao.getAllPosts();

        Assert.assertEquals(2, allPosts.size());

        Assert.assertEquals(post, allPosts.get(0));
        Assert.assertEquals(post2, allPosts.get(1));

        Assert.assertEquals(listTags, allPosts.get(0).getTags());
        Assert.assertEquals(category, allPosts.get(0).getCategory());
        Assert.assertEquals(category2, allPosts.get(1).getCategory());

    }

    @Test
    public void getRecent() {
        BlogPost post = new BlogPost();
        post.setTitle("National Park");
        post.setAuthor("Rene");
        post.setPostContent("parks are cool");
        post.setApproved(true);
        dao.createPost(post);

        BlogPost post2 = new BlogPost();
        post2.setTitle("National Stuff");
        post2.setAuthor("Rene");
        post2.setPostContent("parks meh");
        post2.setApproved(false);
        dao.createPost(post2);

        Category category = new Category();
        category.setCategory("Camp");
        dao.createCategory(category);
        dao.addBlogPostCategory(category, post.getId());

        Category category2 = new Category();
        category2.setCategory("Triangle");
        dao.createCategory(category2);
        dao.addBlogPostCategory(category2, post2.getId());

        Tag tag = new Tag();
        tag.setTag("#money");
        dao.createTag(tag);

        Tag tag2 = new Tag();
        tag2.setTag("#moneyTime");
        dao.createTag(tag2);

        List<Tag> listTags = new ArrayList<>();
        listTags.add(tag);
        listTags.add(tag2);
        dao.addBlogPostTags(listTags, post.getId());
        dao.addBlogPostTags(listTags, post2.getId());

        Assert.assertEquals(2, dao.getRecentPosts(2).size());

    }

    @Test
    public void getAllPostsByTagId() {
        BlogPost post = new BlogPost();
        post.setTitle("National Park");
        post.setAuthor("Rene");
        post.setPostContent("parks are cool");
        post.setApproved(true);
        dao.createPost(post);

        BlogPost post2 = new BlogPost();
        post2.setTitle("National Stuff");
        post2.setAuthor("Rene");
        post2.setPostContent("parks meh");
        post2.setApproved(false);
        dao.createPost(post2);

        Tag tag = new Tag();
        tag.setTag("#money");
        dao.createTag(tag);

        Tag tag2 = new Tag();
        tag2.setTag("#moneyTime");
        dao.createTag(tag2);

        List<Tag> listTags = new ArrayList<>();
        listTags.add(tag);
        listTags.add(tag2);
        dao.addBlogPostTags(listTags, post.getId());

        Assert.assertEquals(1, dao.getAllPostsByTagId(tag.getiD()).size());

    }

    @Test
    public void getPostByTagIdNotAssociatedWithPost() {
        Tag tag = new Tag();
        tag.setTag("#money");
        dao.createTag(tag);

        Assert.assertEquals(0, dao.getAllPostsByTagId(tag.getiD()).size());

    }

    @Test
    public void getAllByCategoryId() {
        BlogPost post = new BlogPost();
        post.setTitle("National Park");
        post.setAuthor("Rene");
        post.setPostContent("parks are cool");
        post.setApproved(true);
        dao.createPost(post);

        BlogPost post2 = new BlogPost();
        post2.setTitle("National Stuff");
        post2.setAuthor("Rene");
        post2.setPostContent("parks meh");
        post2.setApproved(false);
        dao.createPost(post2);

        Category category = new Category();
        category.setCategory("Camp");
        dao.createCategory(category);
        dao.addBlogPostCategory(category, post.getId());

        dao.addBlogPostCategory(category, post2.getId());

        Assert.assertEquals(2, dao.getAllPostsByCategoryId(category.getiD()).size());

    }

    @Test
    public void getAllByCategoryNotAssociatedWithPost() {
        Category category = new Category();
        category.setCategory("Camp");
        dao.createCategory(category);

        Assert.assertEquals(0, dao.getAllPostsByCategoryId(category.getiD()).size());
    }

    @Test
    public void deletePostById() {
        BlogPost post = new BlogPost();
        post.setTitle("National Park");
        post.setAuthor("Rene");
        post.setPostContent("parks are cool");
        post.setApproved(true);
        dao.createPost(post);

        BlogPost post2 = new BlogPost();
        post2.setTitle("National Stuff");
        post2.setAuthor("Rene");
        post2.setPostContent("parks meh");
        post2.setApproved(false);
        dao.createPost(post2);

        dao.deletePostById(post.getId());

        Assert.assertEquals(post2, dao.getAllPosts().get(0));
        Assert.assertEquals(1, dao.getAllPosts().size());

    }

    @Test
    public void deleteTagById() {
        Tag tag = new Tag();
        tag.setTag("#money");
        dao.createTag(tag);
        
        Tag tag2 = new Tag();
        tag2.setTag("#time");
        dao.createTag(tag2);
        
        dao.deleteTagById(tag.getiD());
        
        Assert.assertEquals(1, dao.getAllTags().size());
        Assert.assertEquals(tag2, dao.getAllTags().get(0));
        
    }
    
    @Test
    public void deleteCategoryById() {
        Category category = new Category();
        category.setCategory("Camp");
        dao.createCategory(category);
        
        Category category2 = new Category();
        category2.setCategory("Fields");
        dao.createCategory(category2);
        
        dao.deleteCategoryById(category.getiD());
        
        Assert.assertEquals(1, dao.getAllCategories().size());
        Assert.assertEquals(category2, dao.getAllCategories().get(0));
        
    }

    @Test
    public void editPost(){
        Assert.assertNull(null);
    
    }
    
    
    @Test
    public void createGetAllGetById() {
        BlogPost post = new BlogPost();
        post.setTitle("National Park");
        post.setAuthor("Rene");
        post.setPostContent("parks are cool");

        dao.createPost(post);
        Assert.assertNotNull(post);
        Assert.assertTrue(post.getId() > 0);

        Assert.assertNotNull(dao.getPostById(post.getId()));

        List<BlogPost> allPosts = dao.getAllPosts();
        Assert.assertEquals(1, allPosts.size());
        Assert.assertNotNull(allPosts.get(0));
        Assert.assertEquals(post.getTitle(), allPosts.get(0).getTitle());
        Assert.assertEquals(post, allPosts.get(0));

        Assert.assertEquals(post, dao.getPostById(post.getId()));

    }

    @Test
    public void getAddDeletePost() {
        List<BlogPost> allPosts = dao.getAllPosts();
        Assert.assertEquals(0, allPosts.size());

        BlogPost post = new BlogPost();
        post.setTitle("National Park");
        post.setAuthor("Rene");
        post.setPostContent("parks are cool");
        dao.createPost(post);

        allPosts = dao.getAllPosts();
        Assert.assertEquals(1, allPosts.size());

        Assert.assertEquals(post, dao.getPostById(post.getId()));

        dao.deletePostById(post.getId());
        allPosts = dao.getAllPosts();
        Assert.assertEquals(0, allPosts.size());

    }

    @Test
    public void addEditGetPost() {
        BlogPost post = new BlogPost();
        post.setTitle("National Park");
        post.setAuthor("Rene");
        post.setPostContent("parks are cool");
        dao.createPost(post);

        List<BlogPost> allPosts = dao.getAllPosts();
        Assert.assertEquals(1, allPosts.size());
        Assert.assertEquals(post, dao.getPostById(post.getId()));

        post.setTitle("NATIONAL CAMPGROUND!!!");
        dao.editPost(post);

        Assert.assertEquals(post, dao.getPostById(post.getId()));
    }

    @Test
    public void addPostDeleteTwice() {
        BlogPost post = new BlogPost();
        post.setTitle("National Park");
        post.setAuthor("Rene");
        post.setPostContent("parks are cool");
        dao.createPost(post);

        dao.deletePostById(post.getId());
        dao.deletePostById(post.getId());

        List<BlogPost> allPosts = dao.getAllPosts();
        Assert.assertEquals(0, allPosts.size());
    }

    @Test
    public void addMultiplePostsGetAllDeleteGetAll() {
        BlogPost post = new BlogPost();
        post.setTitle("National Park");
        post.setAuthor("Rene");
        post.setPostContent("parks are cool");
        dao.createPost(post);

        BlogPost post2 = new BlogPost();
        post2.setTitle("National Stuff");
        post2.setAuthor("Rene");
        post2.setPostContent("parks meh");
        dao.createPost(post2);

        List<BlogPost> allPosts = dao.getAllPosts();
        Assert.assertEquals(2, allPosts.size());

        Assert.assertEquals(post, dao.getPostById(post.getId()));
        Assert.assertEquals(post2, dao.getPostById(post2.getId()));

        dao.deletePostById(post.getId());

        allPosts = dao.getAllPosts();
        Assert.assertEquals(1, allPosts.size());

    }

}
