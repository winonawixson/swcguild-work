/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.parksnreccapstone.ops;

import com.swcguild.parksnreccapstone.dao.ParksNRecDao;
import com.swcguild.parksnreccapstone.model.BlogPost;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Winona Wixson
 */
@Controller
public class HomeController {

    ParksNRecDao dao;

    @Inject
    public HomeController(ParksNRecDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage(Model model) {
        List<BlogPost> recentReviews = dao.getRecentPosts(4);
        
        for (int i = 0; i < recentReviews.size(); i++) {
            String review = "reviews" + i;
            model.addAttribute(review, recentReviews.get(i));
        }
        
        return "home";
    }

}
