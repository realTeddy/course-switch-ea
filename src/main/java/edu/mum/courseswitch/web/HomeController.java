/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.web;

import edu.mum.courseswitch.dao.BlockDao;
import edu.mum.courseswitch.dao.CourseDao;
import edu.mum.courseswitch.dao.RegistrationDao;
import edu.mum.courseswitch.dao.UserDao;
import edu.mum.courseswitch.domain.Block;
import edu.mum.courseswitch.domain.Course;
import edu.mum.courseswitch.domain.Registration;
import edu.mum.courseswitch.domain.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    
    @Autowired
    UserDao userDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    BlockDao blockDao;
    @Autowired
    RegistrationDao registrationDao;
    
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
    
    @RequestMapping(path = "/initDb", method = RequestMethod.GET)
    public String initDb() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user1 = new User("984511", "john", encoder.encode("123"), "John", "Doe", true);
        userDao.save(user1);
        User user2 = new User("984512", "jane", encoder.encode("123"), "Jane", "Doe", true);
        userDao.save(user2);
        User admin = new User("984513", "admin", encoder.encode("123"), "Admin", "", true);
        userDao.save(admin);

        Course course1 = new Course(1, "CS 390", "Fundamental Programming Practices", "This course provides a focused program for enhancing programming and analytical skills in five areas", null, "Professor ...", 0);
        Course course2 = new Course(2, "CS 401", "Modern Programming Practices", "Current Concepts and Best Practices in Software Development — Knowledge is the Basis of Action", null, "Professor ...", 0);
        Course course3 = new Course(3, "CS 422", "Database Systems", "Capturing the Organizing Power of Information", null, "Professor ...", 0);
        Course course4 = new Course(4, "CS 423", "Systems Analysis and Design", "The systems approach is an organized way of dealing with a problem", null, "Professor ...", 0);
        Course course5 = new Course(5, "CS 425", "Software Engineering", "Knowledge Is the Basis of Action — Principles and Processes for Developing Large-Scale Software Systems", null, "Professor ...", 0);
        Course course6 = new Course(6, "CS 428", "Software Development with Fund. Design Patterns", "This course is an introduction to 23 GoF (Gang of Four) design patterns.", null, "Professor ...", 0);
        courseDao.save(course1);
        courseDao.save(course2);
        courseDao.save(course3);
        courseDao.save(course4);
        courseDao.save(course5);
        courseDao.save(course6);

        List<Course> firstBlockCourses = new ArrayList<>();
        firstBlockCourses.add(course1);
        List<Course> secondBlockCourses = new ArrayList<>();
        firstBlockCourses.add(course2);
        List<Course> thirdBlockCourses = new ArrayList<>();
        thirdBlockCourses.add(course3);
        thirdBlockCourses.add(course4);
        thirdBlockCourses.add(course5);
        thirdBlockCourses.add(course6);

        Block block1 = new Block(firstBlockCourses, new Date(2016, 1, 1), 1);
        Block block2 = new Block(secondBlockCourses, new Date(2016, 2, 1), 2);
        Block block3 = new Block(thirdBlockCourses, new Date(2016, 3, 1), 3);
        blockDao.save(block1);
        blockDao.save(block2);
        blockDao.save(block3);
        
        registrationDao.save(new Registration(user1, block1, course1, null));
        registrationDao.save(new Registration(user1, block2, course2, null));
        registrationDao.save(new Registration(user1, block3, course3, null));

        registrationDao.save(new Registration(user2, block1, course1, null));
        registrationDao.save(new Registration(user2, block2, course2, null));
        registrationDao.save(new Registration(user2, block3, course4, null));
        return "index";
    }
    
    
}
