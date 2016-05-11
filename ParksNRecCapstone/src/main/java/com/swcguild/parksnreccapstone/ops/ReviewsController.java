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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Winona Wixson
 */
@Controller
public class ReviewsController {

    ParksNRecDao dao;

    @Inject
    public ReviewsController(ParksNRecDao dao) {
        this.dao = dao;
    }

    private List<BlogPost> deleteUnapprovedReviewsFromList(List<BlogPost> posts) {
        List<BlogPost> approvedPosts = new ArrayList<>();
        for (BlogPost post : posts) {
            if (post.isApproved()) { //if the review isn't approved, don't display the review
                approvedPosts.add(post);
            }
        }
        return approvedPosts;
    }

    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    public String displayReviews(Model model) {
        List<Tag> tags = dao.getAllTags();
        List<Category> categories = dao.getAllCategories();
        model.addAttribute("tags", tags);
        model.addAttribute("categories", categories);

        List<BlogPost> allReviews = dao.getAllPosts();

        allReviews = this.deleteUnapprovedReviewsFromList(allReviews);

        model.addAttribute("reviews", allReviews);

        return "reviews";
    }

    @RequestMapping(value = "/reviewsByTag", method = RequestMethod.GET)
    public String displayReviewsByTag(HttpServletRequest req, Model model) {
        List<Tag> tags = dao.getAllTags();
        List<Category> categories = dao.getAllCategories();
        model.addAttribute("tags", tags);
        model.addAttribute("categories", categories);

        int id = Integer.parseInt(req.getParameter("tagId"));
        List<BlogPost> postsByTagId = dao.getAllPostsByTagId(id);

        postsByTagId = this.deleteUnapprovedReviewsFromList(postsByTagId);

        model.addAttribute("reviews", postsByTagId);

        return "reviews";
    }

    @RequestMapping(value = "/reviewsByCategory", method = RequestMethod.GET)
    public String displayReviewsByCategory(HttpServletRequest req, Model model) {
        List<Tag> tags = dao.getAllTags();
        List<Category> categories = dao.getAllCategories();
        model.addAttribute("tags", tags);
        model.addAttribute("categories", categories);

        int id = Integer.parseInt(req.getParameter("categoryId"));

        List<BlogPost> postsByCategory = dao.getAllPostsByCategoryId(id);
        postsByCategory = this.deleteUnapprovedReviewsFromList(postsByCategory);

        model.addAttribute("reviews", postsByCategory);

        return "reviews";
    }

//    @RequestMapping(value = "/recentReviews", method = RequestMethod.GET)
//    public String displayRecentReviews(HttpServletRequest req, Model model) {
//        List<Tag> tags = dao.getAllTags();
//        List<Category> categories = dao.getAllCategories();
//        model.addAttribute("tags", tags);
//        model.addAttribute("categories", categories);
//
////        int numberOfReviews = Integer.parseInt(req.getParameter("numberOfReviews"));
//        List<BlogPost> recentReviews = dao.getRecentPosts(4);
//        
//        for (int i = 0; i < recentReviews.size(); i++) {
//            String review = "reviews" + i;
//            model.addAttribute(review, recentReviews.get(i));
//        }
//        return "home";
//    }

    @RequestMapping(value = "/singleReview", method = RequestMethod.GET)
    public String displaySingleReview(HttpServletRequest req, Model model) {
        List<Tag> tags = dao.getAllTags();
        List<Category> categories = dao.getAllCategories();
        model.addAttribute("tags", tags);
        model.addAttribute("categories", categories);

        //get the review from the database through the dao
        //check to see if it's approved and can be displayed
        //if approved, put it in a list, so the reviews.jsp can display it properly
        //if not, nothing will be added to the model and nothing will be displayed
        //maybe add an else so the jsp can check if there's nothing to display
        BlogPost post = dao.getPostById(Integer.parseInt(req.getParameter("postId")));
        if (post.isApproved()) {
            List<BlogPost> reviews = new ArrayList<>();
            reviews.add(post);
            model.addAttribute("reviews", reviews);
        }

        return "reviews";
    }
//    @RequestMapping(value = "/singleReview/{id}", method = RequestMethod.GET)
//    public String displaySingleReview(HttpServletRequest req, Model model, @PathVariable("id") int id) {
//        List<Tag> tags = dao.getAllTags();
//        List<Category> categories = dao.getAllCategories();
//        model.addAttribute("tags", tags);
//        model.addAttribute("categories", categories);
//        
//        List<BlogPost> reviews = new ArrayList<>();
//        //reviews.add(dao.getPostById(Integer.parseInt(req.getParameter("reviewId"))));
//        reviews.add(dao.getPostById(id));
//        model.addAttribute("reviews", reviews);
//
//        return "reviews";
//    }

}
