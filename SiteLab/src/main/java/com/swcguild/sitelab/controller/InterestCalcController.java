/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.sitelab.controller;

import com.swcguild.sitelab.dao.InterestCalculator;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Winona Wixson
 */
@Controller
public class InterestCalcController {

    @RequestMapping(value = "/interestCalc", method = RequestMethod.POST)
    public String playInterestCalc(HttpServletRequest request) {

        String rateString = request.getParameter("annualInterestRate");
        String initialPrincipalString = request.getParameter("initialPrincipal");
        String yearsString = request.getParameter("years");

        if (rateString == null || initialPrincipalString == null || yearsString == null) {
            request.setAttribute("badInput", true);
        } else {
            try {
                Integer annualInterestRate = Integer.parseInt(rateString);
                Integer initialPrincipal = Integer.parseInt(initialPrincipalString);
                Integer years = Integer.parseInt(yearsString);

                InterestCalculator intCalc = new InterestCalculator(annualInterestRate, initialPrincipal, years);

                request.setAttribute("annualInterestData", intCalc.getAnnualInterestData());

            } catch (NumberFormatException ex) {
                request.setAttribute("badInput", true);
            }
        }

        return "icresponse";
    }

}
