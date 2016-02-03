/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.web;

import edu.mum.courseswitch.dto.CourseDto;
import edu.mum.courseswitch.dto.RegistrationDto;
import edu.mum.courseswitch.service.CourseService;
import edu.mum.courseswitch.service.PreferenceService;
import edu.mum.courseswitch.service.RegistrationService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/courseSwitch")
public class CourseSwitchController {
    
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private PreferenceService preferenceService;
    @Autowired
    private CourseService courseService;
    
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String courseSwitch() {
        return "courseSwitch";
    }
    
    @RequestMapping(path = "/getRegistrations", method = RequestMethod.GET)
    public @ResponseBody List<RegistrationDto> courseSwitch(Principal principal) {
        return registrationService.getRegistrations(principal.getName());
    }
    
    @RequestMapping(path = "/getBlockCourses", method = RequestMethod.GET)
    public @ResponseBody List<CourseDto> getBlockCourses(int blockId, int courseId, Principal principal) {
        return courseService.getCourses(principal.getName(), courseId, blockId);
    }
    
    @RequestMapping(path = "/addPreferedCourse", method = RequestMethod.GET)
    public @ResponseBody boolean addPreferedCourse(int registrationId, int courseId, Principal principal) {
        return preferenceService.addPreferedCourse(principal.getName(), registrationId, courseId);
    }
}