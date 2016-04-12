/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.sitelab.controller;

import com.swcguild.sitelab.dao.TipCalculator;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Winona Wixson
 */
@Controller
public class TipCalculatorController {
 
    @RequestMapping(value="/tipCalc", method=RequestMethod.POST)
    public String playTipCalc(HttpServletRequest request){
         String subtotalString = request.getParameter("subtotal");
        String tipPercentString = request.getParameter("tipPercent");

        if (subtotalString == null || tipPercentString == null) {
            request.setAttribute("badInput", true);
        } else {
            try {
                double subtotal = Double.parseDouble(subtotalString);
                double tipPercent = Double.parseDouble(tipPercentString);
                
                TipCalculator tipCalc = new TipCalculator(subtotal, tipPercent);
                
                request.setAttribute("tipData", tipCalc);

            } catch (NumberFormatException ex) {
                request.setAttribute("badInput", true);
            }

        }
        return "tcresponse";
    }
    
}
