package com.swcguild.sitelab.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping({"/hello"})
public class HomeController {
        
    public HomeController() {
    }
    
//    @RequestMapping(value="/sayhi", method=RequestMethod.GET)
//    public String sayHi(Map<String, Object> model) {
//        model.put("message", "Hello from the controller" );
//        return "hello";
//    }
     @RequestMapping(value="/", method=RequestMethod.GET)
    public String displayHomePage(){
        return "home";
    }
    
    @RequestMapping(value="/luckySevens", method=RequestMethod.GET)
    public String playLuckySevens(){
        return "luckySevens";
    }
    
    @RequestMapping(value="/factorizor", method=RequestMethod.GET)
    public String playFactorizor(){
        return "factorizor";
    }
    
    @RequestMapping(value="/interestCalc", method=RequestMethod.GET)
    public String playInterestCalc(){
        return "interestCalculator";
    }
    
    @RequestMapping(value="/flooringCalc", method=RequestMethod.GET)
    public String playFlooringCalc(){
        return "flooringCalculator";
    }
    
    @RequestMapping(value="/tipCalc", method=RequestMethod.GET)
    public String playTipCalc(){
        return "tipCalculator";
    }
    
    @RequestMapping(value="/unitConverter", method=RequestMethod.GET)
    public String playUnitConverter(){
        return "unitConverter";
    }
    
}
