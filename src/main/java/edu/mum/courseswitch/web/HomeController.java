/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.web;

import edu.mum.courseswitch.dao.UserDao;
import edu.mum.courseswitch.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    
    @Autowired
    private UserDao userDao;
    
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() { 
        userDao.save(new User("John", "Doe", "email@example.com", "passw0rd", false));
        return "index";
    }
}
