/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.web;

import edu.mum.courseswitch.dao.CourseDao;
import edu.mum.courseswitch.dao.UserDao;
import edu.mum.courseswitch.domain.Course;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author henok
 */
@Controller
public class InstructorController {
    
    @Autowired
    private CourseDao courseDao;
    
    @RequestMapping(path = "/instructor", method = RequestMethod.GET)
    public String instructor(Model m) { 
       Course c1= new Course(1,"CS400","MPP","Modern Programming",new ArrayList<Course>(){{}},"Orlando");
       List<Course> prerequisites=new ArrayList<Course>(){{add(c1);}};
        courseDao.save(c1);        
        courseDao.save(new Course(2,"CS54","EA","enterprise", prerequisites,"Mike"));
        m.addAttribute("courses", courseDao.findAll());       
        return "instructorView";
    }
    
    @RequestMapping(path = "/instructor/edit/{id}", method = RequestMethod.GET)
    public String instructorEdit(Model m,@PathVariable int id) { 
        m.addAttribute("course", courseDao.getOne(id));       
        return "instructor/instructorEdit";
    }
    
    
}
