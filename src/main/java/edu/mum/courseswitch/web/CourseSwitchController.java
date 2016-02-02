/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.web;

import edu.mum.courseswitch.domain.User;
import edu.mum.courseswitch.service.RegistrationService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseSwitchController {
    
    @Autowired
    private RegistrationService registrationService;
    
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public @ResponseBody User index(Principal principal) {
        //registrationService.getRegistrations(principal.getName());
        return new User("John", "Doe", "email@example.com", "passw0rd", false);
    }
}