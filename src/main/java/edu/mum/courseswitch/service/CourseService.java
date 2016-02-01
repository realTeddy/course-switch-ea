/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.service;

import edu.mum.courseswitch.dao.BlockDao;
import edu.mum.courseswitch.dao.PendingSwitchDao;
import edu.mum.courseswitch.dao.RegistrationDao;
import edu.mum.courseswitch.domain.Course;
import edu.mum.courseswitch.domain.Registration;
import edu.mum.courseswitch.domain.User;
import edu.mum.courseswitch.dto.CourseDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseService {

    @Autowired
    private RegistrationDao registrationDao;
    @Autowired
    private BlockDao blockDao;
    @Autowired
    private PendingSwitchDao pendingSwitchDao;

    public List<CourseDto> getCourses(User user, int courseId, int blockId) {
        List<Course> previousCourses = registrationDao.findByUserIdAndBlockStartDateGreaterThan(user.getId(), new Date()).stream()
                .map(r -> r.getCourse()).collect(Collectors.toList());

        List<Course> alreadyPreferenced = registrationDao.findByUserIdAndBlock_Id(user.getId(), blockId);

        List<Course> blockCourses = blockDao.findOne(blockId).getCourses();

        List<Course> courses = blockCourses.stream().filter(c -> !previousCourses.contains(c) && (c.getPrerequisites().isEmpty()
                || previousCourses.stream().anyMatch(pc -> c.getPrerequisites().contains(pc))
                && !alreadyPreferenced.contains(c)))
                .collect(Collectors.toList());
        List<CourseDto> courseViewModels = new ArrayList<>();
        List<Registration> registrations = registrationDao.findByBlock_Id(blockId);
        List<Course> pendingSwitches = pendingSwitchDao.findByUserId(user.getId()).stream()
                .map(p -> p.getToCourse()).collect(Collectors.toList());
        
        for (Course course : courses) {
            boolean isAvailable;
            long registeredCount = registrations.stream().filter(r -> r.getCourse().equals(course)).count();
            if (registeredCount < course.getClassCapacity()) {
                isAvailable = true;
            } else {
                isAvailable = registrations.stream().anyMatch(r -> r.getCourse().equals(course) && r.getPreferedCourses().stream().anyMatch(p -> p.getId() == courseId));
            }
            boolean isPendingApproval = pendingSwitches.contains(course);
            courseViewModels.add(new CourseDto(course, isAvailable, isPendingApproval));
        }

        return courseViewModels;
    }

}
