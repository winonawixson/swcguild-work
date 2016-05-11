/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.parksnreccapstone.ops;

import com.swcguild.parksnreccapstone.dao.ParksNRecDao;
import com.swcguild.parksnreccapstone.model.BlogPost;
import com.swcguild.parksnreccapstone.model.Category;
import com.swcguild.parksnreccapstone.model.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Winona Wixson
 */
@Controller
public class AuthorizationController {

    ParksNRecDao dao;

    @Inject
    public AuthorizationController(ParksNRecDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String displayPostPage() {
        return "post";
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public String addPost(HttpServletRequest req) {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String postContent = req.getParameter("postContent");
        String approvedString = req.getParameter("approved");
        boolean approved = Boolean.parseBoolean(approvedString);

        BlogPost post = new BlogPost();
        post.setTitle(title);
        post.setAuthor(author);
        post.setPostContent(postContent);
        post.setApproved(approved);

        dao.createPost(post);

        //category
        String categoryString = req.getParameter("category");
        this.addBlogPostCategories(post.getId(), categoryString);

        //tag
        String tagString = req.getParameter("tag");
        this.addBlogPostTags(post.getId(), tagString);

        return "redirect:reviews";
    }

    private void addBlogPostCategories(int postId, String categoryString) {
        Category categoryForPost = new Category();

        List<Category> allCategories = dao.getAllCategories();
        boolean categoryFound = false;

        for (Category category : allCategories) {
            if (category.getCategory().equalsIgnoreCase(categoryString)) {
                categoryForPost = category;
                categoryFound = true;
            }
        }
        if (!categoryFound) {
            categoryForPost.setCategory(categoryString);
            dao.createCategory(categoryForPost);
        }

        dao.addBlogPostCategory(categoryForPost, postId);

    }

    private void addBlogPostTags(int postId, String tagString) {
        if (tagString != null || !(tagString.isEmpty())) {
            List<Tag> allTagsFromDb = dao.getAllTags();
            String[] allTagsFromUser = tagString.split("\\s+");

            List<Tag> tagsToAddToDb = new ArrayList<>();
            boolean duplicateTag = false;

            for (int i = 0; i < allTagsFromUser.length; i++) {
                for (Tag tagFromDb : allTagsFromDb) {

                    if (allTagsFromUser[i].equals(tagFromDb.getTag())) {
                        duplicateTag = true;

                        tagsToAddToDb.add(tagFromDb);

                    }
                }
                if (!duplicateTag) {
                    Tag tag = new Tag();
                    tag.setTag(allTagsFromUser[i]);
                    dao.createTag(tag);

                    tagsToAddToDb.add(tag);
                }

                duplicateTag = false;

            }
            dao.addBlogPostTags(tagsToAddToDb, postId);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String displayLogInPage() {

        return "login";
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.POST)
    public String displayHomePage(Model model) {
        model.addAttribute("threeReviews", dao.getRecentPosts(3));

        return "home";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getPostToEditById(HttpServletRequest req, Model model) {
        BlogPost post = dao.getPostById(Integer.parseInt(req.getParameter("postId")));
        model.addAttribute("postToEdit", post);

        String tagString = "";
        if (!post.getTags().isEmpty() && post.getTags() != null) {
            for (Tag t : post.getTags()) {
                tagString = tagString + t.getTag() + " ";
            }
        }

        model.addAttribute("tagString", tagString);

        return "editPost";
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.POST)
    public String editPostWithId(HttpServletRequest req) {
        BlogPost post = new BlogPost();
        post.setId(Integer.parseInt(req.getParameter("id")));
        post.setTitle(req.getParameter("title"));
        post.setAuthor(req.getParameter("author"));
        post.setPostContent(req.getParameter("postContent"));
        post.setApproved(Boolean.parseBoolean(req.getParameter("approved")));

        dao.editPost(post);

        //gives us the old tags/categories
        post = dao.getPostById(post.getId());

        //posts olds category and tags
        Category oldCategory = post.getCategory();
        List<Tag> tagsWithPost = post.getTags();

        //list of all tags and all categories
        List<Category> allCategories = dao.getAllCategories();
        List<Tag> allTags = dao.getAllTags();

        //category
        String categoryString = req.getParameter("category.category");
        boolean categoryAlreadyCreated = false;

        //checks to see if old category is equal to new category
        if (!(categoryString.equalsIgnoreCase(oldCategory.getCategory()))) {
            for (Category cat : allCategories) {
                if (cat.getCategory().equalsIgnoreCase(categoryString)) {
                    dao.updateBlogPostCategory(cat.getiD(), post.getId());
                    categoryAlreadyCreated = true;
                }

            }

            if (!categoryAlreadyCreated) {
                Category category = new Category();

                //if the category wasn't found to be  already created, then we need to create it here
                //so we'll create the category and add the category id with the post id
                //to blogpostcategories
                category.setCategory(categoryString);
                dao.createCategory(category);
                dao.updateBlogPostCategory(category.getiD(), post.getId());
            }

            //if the category was found above, then we need to check to see if it changed
            //if it changed, then we need to check if it exists as a property of another blogpost
            //if it exists, then we don't need to do anything
            //if doesn't exist, then we need to delete the category
            List<BlogPost> allPosts = dao.getAllPosts();
            boolean categoryExists = false;

            for (BlogPost p : allPosts) {
                if (p.getCategory().getCategory().equals(oldCategory.getCategory())) {
                    categoryExists = true;
                }
            }
            if (!categoryExists) {
                dao.deleteCategoryById(oldCategory.getiD());
            }

        }
//        
//        //tag
//        String tagString = req.getParameter("tag");
//        if (tagString != null || !(tagString.isEmpty())) {
//            this.addBlogPostTags(post.getId(), tagString);
//        }
//        
        return "redirect:reviews";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePostById(HttpServletRequest req) {
        int postId = Integer.parseInt(req.getParameter("postId"));

        dao.deletePostById(postId);
        return "redirect:reviews";
    }

    @RequestMapping(value = "/reviewsToApprove", method = RequestMethod.GET)
    public String displayApprovalPage(Model model) {
        List<BlogPost> allPosts = dao.getAllPosts();
        List<BlogPost> postsToApprove = new ArrayList<>();

        for (BlogPost post : allPosts) {
            if (!post.isApproved()) {
                postsToApprove.add(post);
            }
        }

        model.addAttribute("reviews", postsToApprove);

        return "reviewsToApprove";
    }

}
