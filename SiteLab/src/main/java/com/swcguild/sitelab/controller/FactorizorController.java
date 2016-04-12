/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.sitelab.controller;

import com.swcguild.sitelab.dao.Factorizor;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Winona Wixson
 */
@Controller
public class FactorizorController {

    @RequestMapping(value = "factorize", method = RequestMethod.POST)
    public String factorize(HttpServletRequest request) {
        String numberString = request.getParameter("numberToFactor");
        try {
            Integer number = Integer.parseInt(numberString);
            Factorizor f = new Factorizor(number);
            request.setAttribute("factorizations", f);
        } catch (NumberFormatException ex) {
            request.setAttribute("badInput", true);
        }

        return "factresponse";
    }

}
