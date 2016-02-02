/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.web;

import edu.mum.courseswitch.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseSwitchController {
    
    @Autowired
    private RegistrationService registrationService;
    
    @RequestMapping(path = "/courseSwitch", method = RequestMethod.GET)
    public String courseSwitch() {
        return "courseSwitch";
    }
}