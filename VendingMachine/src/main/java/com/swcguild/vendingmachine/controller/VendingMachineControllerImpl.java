/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachine.controller;

import com.swcguild.vendingmachine.dao.VendingMachineDAOImpl;
import com.swcguild.vendingmachine.model.Item;
import com.swcguild.vendingmachine.model.PurchaseResult;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
public class VendingMachineControllerImpl {

    @Autowired
    private VendingMachineDAOImpl dao;

//    @Inject
//    public VendingMachineControllerImpl(VendingMachineDAOImpl dao) {
//        this.dao = dao;
//    }
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "index";
    }

//    @RequestMapping(value="/", method=RequestMethod.GET)
//    public String displayItems(Model model){
//        model.addAttribute("displayItems", dao.getDisplayItems());
//        return "index";
//    }
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> displayItems() {
        return dao.getDisplayItems();
    }

//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public String buyItem(HttpServletRequest req, Model model) {
//        String userMoneyString = req.getParameter("userMoney");
//        double userMoney = Double.parseDouble(userMoneyString);
//        int itemId = Integer.parseInt(req.getParameter("itemNumber"));
//
//        Item item = dao.getItem(itemId);
//        if (item.getItemQuantity() > 0 && item.getItemPrice() <= userMoney) {
//            dao.buyItem(itemId);
//            userMoney = userMoney - item.getItemPrice();
//            model.addAttribute("goodInput", true);
//            model.addAttribute("item", item);
//            model.addAttribute("userMoney", userMoney);
//        } else if (item.getItemQuantity() <= 0) {
//            model.addAttribute("badInput", true);
//            model.addAttribute("message", "This item is no longer available.");
//        } else if (item.getItemPrice() > userMoney) {
//            model.addAttribute("badInput", true);
//            model.addAttribute("message", "Insufficient funds.");
//        }
//        model.addAttribute("displayItems", dao.getDisplayItems());
//        return "index";
//    }
    @RequestMapping(value = "/itemId/{itemId}/userMoney/{userMoney}", method = RequestMethod.POST)
    @ResponseBody
    public PurchaseResult buyItem(@PathVariable("itemId") int itemId, @PathVariable("userMoney") double userMoney) {
        Item item = dao.getItem(itemId);
        PurchaseResult result = new PurchaseResult(userMoney, item);
        
        if (result.isSufficientFunds() && result.isSufficientQuant()) {
            dao.buyItem(itemId); //maybe need to reset the item in result
        } 
        return result;
    }

}
