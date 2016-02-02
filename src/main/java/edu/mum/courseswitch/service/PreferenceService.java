/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.service;

import edu.mum.courseswitch.dao.CourseDao;
import edu.mum.courseswitch.dao.PendingSwitchDao;
import edu.mum.courseswitch.dao.RegistrationDao;
import edu.mum.courseswitch.domain.Course;
import edu.mum.courseswitch.domain.PendingSwitch;
import edu.mum.courseswitch.domain.Registration;
import edu.mum.courseswitch.domain.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

public class PreferenceService {

    @Autowired
    private RegistrationDao registrationDao;
    
    @Autowired
    private CourseDao courseDao;
    
    @Autowired
    private PendingSwitchDao pendingSwitchDao;

    public List<Course> getPreferences(int courseId) {
        return registrationDao.findByCourse_Id(courseId).stream()
                .map(r -> r.getCourse()).collect(Collectors.toList());
    }
    
    public boolean addPreferedCourse(User user, int registrationId, int courseId) {
        Registration registration = registrationDao.findOne(registrationId);
        Course course = courseDao.findOne(courseId);
        registration.addPreferedCourse(course);

        if (course.getClassCapacity() > registrationDao.countByCourse_Id(courseId)) {
            pendingSwitchDao.save(new PendingSwitch(user, registration, course, false));
            return true;
        } else if (registrationDao.countByBlock_IdAndPreferedCourse_Id(registration.getBlock().getId(), course.getId()) > 0) {
            Registration otherRegistration = registrationDao.findByBlockIdAndCourse_IdPreferedCourse_Id(registration.getBlock().getId(), course.getId(), registration.getCourse().getId());
            if (otherRegistration != null) {
                pendingSwitchDao.save(new PendingSwitch(user, registration, course, true));
                pendingSwitchDao.save(new PendingSwitch(otherRegistration.getUser(), otherRegistration, registration.getCourse(), true));
                return true;
            }
        }
        return false;
    }
}
