package com.cosc625.carinventorysecure.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class CarInventorySecureController {

    @RequestMapping(value = "/newInventory", method = RequestMethod.POST)
    public String createInventory(Principal principal){
        return "Hello " + principal.getName() + "! You have created new inventory.";
    }

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.DELETE)
    public String deleteInventory(Principal principal, @PathVariable int id){
        return "You - " + principal.getName() + "have deleted the private Event " + "with id " + id + ".";
    }

    @RequestMapping(value = "/updateInventory", method = RequestMethod.PUT)
    public String updateInventory(){
        return "You ";
    }

    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    public String inventory(){
        return "You are registered for a public event";
    }

    @RequestMapping(value = "/allDone", method = RequestMethod.GET)
    private String calledByLogout(){
        return "You are logged out";
    }
}
