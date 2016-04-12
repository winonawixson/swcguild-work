/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookmvc.controller;

import static org.springframework.http.RequestEntity.method;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Winona Wixson
 */
@Controller
public class HomeController {
    
    @RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
    public String displayHomePage(){
        return "home";
    }
}
