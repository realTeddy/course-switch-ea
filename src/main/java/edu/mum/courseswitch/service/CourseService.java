/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.service;

import edu.mum.courseswitch.dao.BlockDao;
import edu.mum.courseswitch.dao.CourseDao;
import edu.mum.courseswitch.dao.PendingSwitchDao;
import edu.mum.courseswitch.dao.RegistrationDao;
import edu.mum.courseswitch.domain.Block;
import edu.mum.courseswitch.domain.Course;
import edu.mum.courseswitch.domain.PendingSwitch;
import edu.mum.courseswitch.domain.Registration;
import edu.mum.courseswitch.dto.CourseDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CourseService {

    @Autowired
    private RegistrationDao registrationDao;
    @Autowired
    private BlockDao blockDao;
    @Autowired
    private PendingSwitchDao pendingSwitchDao;
    @Autowired
    private CourseDao courseDao;

//    public List<CourseDto> getCourses(String username, int courseId, int blockId) {
//        List<Course> previousCourses = registrationDao.findByUser_UsernameAndBlockStartDateGreaterThan(username, new Date()).stream()
//                .map(r -> r.getCourse()).collect(Collectors.toList());
//
//        List<Course> alreadyPreferenced = registrationDao.findByUser_UsernameAndBlock_Id(username, blockId);
//
//        List<Course> blockCourses = blockDao.findOne(blockId).getCourses();
//        List<CourseDto> courseViewModels = new ArrayList<>();
//        List<Registration> registrations = registrationDao.findByBlock_Id(blockId);
//        List<Course> pendingSwitches = pendingSwitchDao.findByUser_Username(username).stream()
//                .map(p -> p.getToCourse()).collect(Collectors.toList());
//        
//        for (Course course : blockCourses) {            
//            if (previousCourses.contains(course) || (!course.getPrerequisites().isEmpty()
//                    && previousCourses.stream().anyMatch(pc -> course.getPrerequisites().contains(pc)))) {
//                continue;
//            }
//            
//            boolean isAvailable;
//            long registeredCount = registrations.stream().filter(r -> r.getCourse().equals(course)).count();
//            if (registeredCount < course.getClassCapacity()) {
//                isAvailable = true;
//            } else {
//                isAvailable = registrations.stream().anyMatch(r -> r.getCourse().equals(course) && r.getPreferedCourses().stream().anyMatch(p -> p.getId() == courseId));
//            }
//            boolean isPendingApproval = pendingSwitches.contains(course);
//            courseViewModels.add(new CourseDto(course, isAvailable, isPendingApproval));
//        }
//
//        return courseViewModels;
//    }
    
    public List<CourseDto> getCourses(String username, int courseId, int blockId) {
        List<Registration> registrations = registrationDao.findAll();
        List<Block> blocks = blockDao.findAll();
        List<PendingSwitch> pendingSwitches = pendingSwitchDao.findAll();
        List<Course> previousCourses = registrations.stream()
                .filter(r -> r.getUser().getUsername().equals(username) && r.getBlock().getStartDate().compareTo(new Date()) > 0)
                .map(r -> r.getCourse()).collect(Collectors.toList());

        List<Course> alreadyPreferenced = registrations.stream()
                .filter(r -> r.getBlock().getId() == blockId && r.getUser().getUsername().equals(username))
                .map(r -> r.getPreferedCourses().stream().map(pc -> pc.getCourse()).collect(Collectors.toList()))
                .flatMap(Collection::stream).collect(Collectors.toList());

        List<Course> blockCourses = blocks.stream().filter(b -> b.getId() == blockId)
                .map(b -> b.getCourses()).flatMap(Collection::stream).collect(Collectors.toList());

        List<CourseDto> courseViewModels = new ArrayList<>();
        
        List<Course> courses = blockCourses.stream().filter(c -> !previousCourses.contains(c) && (c.getPrerequisites().isEmpty()
                || previousCourses.stream().anyMatch(pc -> c.getPrerequisites().contains(pc))
                && !alreadyPreferenced.contains(c)))
                .collect(Collectors.toList());
        
        List<Registration> blockRegistrations = registrations.stream()
                .filter(r -> r.getBlock().getId() == blockId).collect(Collectors.toList());
        List<Course> pendingSwitchCourses = pendingSwitches.stream().filter(p -> p.getUser().getUsername().equals(username)).map(p -> p.getToCourse()).collect(Collectors.toList());
        for (Course course : courses) {
            boolean isAvailable;
            long registeredCount = blockRegistrations.stream().filter(r -> r.getCourse().equals(course)).count();
            if (registeredCount < course.getClassCapacity()) {
                isAvailable = true;
            } else {
                isAvailable = blockRegistrations.stream().anyMatch(r -> r.getCourse().equals(course) && r.getPreferedCourses().stream().anyMatch(p -> p.getCourse().getId() == courseId));
            }
            boolean isPendingApproval = pendingSwitchCourses.contains(course);
            courseViewModels.add(new CourseDto(course, isAvailable, isPendingApproval));
        }

        return courseViewModels;
    }

    public void createCourse(Course course) {
        courseDao.save(course);
    }

    public List<Course> getAllCourses() {
        return courseDao.findAll();
    }

    public Course getCourse(int courseId) {
        return courseDao.findOne(courseId);
    }

    public Course updateCourse(Course course) {
        return courseDao.save(course);
    }

    public void deleteCourse(int courseId) {
        courseDao.delete(courseId);
    }

    public void addPrerequisite(int courseId, int prerequisiteId) {
        Course course = courseDao.findOne(courseId);
        Course prerequisite = courseDao.findOne(prerequisiteId);
        course.addPrerequisite(prerequisite);
        courseDao.save(course);
    }

    public void deletePrerequisite(int courseId, int prerequisiteId) {
        Course course = courseDao.findOne(courseId);
        Course prerequisite = courseDao.findOne(prerequisiteId);
        course.removePrerequisite(prerequisite);
        courseDao.save(course);
    }

    public void addBlockCourse(String name, int blockId, int courseId) {
        Block block = blockDao.findOne(blockId);
        Course course = courseDao.findOne(courseId);
        block.addCourse(course);
        blockDao.save(block);
    }
}
