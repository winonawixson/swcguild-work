/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.sitelab.controller;

import com.swcguild.sitelab.dao.FlooringCalculator;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Winona Wixson
 */
@Controller
public class FlooringCalcController {

    @RequestMapping(value = "/flooringCalculation", method = RequestMethod.POST)
    public String playFlooringCalc(HttpServletRequest request) {
        String widthString = request.getParameter("width");
        String lengthString = request.getParameter("length");
        String costString = request.getParameter("costSqFt");

        if (widthString == null || lengthString == null || costString == null) {
            request.setAttribute("badInput", true);
        } else {
            try {
                double width = Double.parseDouble(widthString);
                double length = Double.parseDouble(lengthString);
                double costSqFt = Double.parseDouble(costString);

                if (width <= 0 || length <= 0 || costSqFt <= 0) {
                    request.setAttribute("badInput", true);
                } else {
                    FlooringCalculator fc = new FlooringCalculator(width, length, costSqFt);
                    request.setAttribute("calculations", fc);

                    request.setAttribute("width", width);
                    request.setAttribute("length", length);
                    request.setAttribute("costSqFt", costSqFt);

                    request.setAttribute("flooringArea", fc.getArea());
                    request.setAttribute("laborTime", fc.getLaborTime());
                    request.setAttribute("laborCost", fc.getLaborCost());
                    request.setAttribute("flooringCost", fc.getFlooringCost());
                }

            } catch (NumberFormatException ex) {
                request.setAttribute("badInput", true);
            }
        }

        return "fcresponse";
    }

}
