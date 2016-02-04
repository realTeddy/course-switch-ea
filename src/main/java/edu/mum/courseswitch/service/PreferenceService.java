/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.service;

import edu.mum.courseswitch.dao.CourseDao;
import edu.mum.courseswitch.dao.PendingSwitchDao;
import edu.mum.courseswitch.dao.RegistrationDao;
import edu.mum.courseswitch.dao.Registration_PreferedCoursesDao;
import edu.mum.courseswitch.dao.UserDao;
import edu.mum.courseswitch.domain.Course;
import edu.mum.courseswitch.domain.PendingSwitch;
import edu.mum.courseswitch.domain.Registration;
import edu.mum.courseswitch.domain.Registration_PreferedCourses;
import edu.mum.courseswitch.domain.User;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PreferenceService {

    @Autowired
    private RegistrationDao registrationDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private UserDao userDao;

    @Autowired
    private PendingSwitchDao pendingSwitchDao;
    @Autowired
    private Registration_PreferedCoursesDao registration_PreferedCoursesDao;

    public List<Course> getPreferences(int courseId) {
        return registrationDao.findByCourse_Id(courseId).stream()
                .map(r -> r.getCourse()).collect(Collectors.toList());
    }

//    public boolean addPreferedCourse(String userName, int registrationId, int courseId) {
//        User user = userDao.findByUsername(userName);
//        Registration registration = registrationDao.findOne(registrationId);
//        Course course = courseDao.findOne(courseId);
//        registration.addPreferedCourse(course);
//
//        if (course.getClassCapacity() > registrationDao.countByCourse_Id(courseId)) {
//            pendingSwitchDao.save(new PendingSwitch(user, registration, course, false));
//            return true;
//        } else if (registrationDao.countByBlock_IdAndPreferedCourse_Id(registration.getBlock().getId(), course.getId()) > 0) {
//            Registration otherRegistration = registrationDao.findByBlockIdAndCourse_IdPreferedCourse_Id(registration.getBlock().getId(), course.getId(), registration.getCourse().getId());
//            if (otherRegistration != null) {
//                pendingSwitchDao.save(new PendingSwitch(user, registration, course, true));
//                pendingSwitchDao.save(new PendingSwitch(otherRegistration.getUser(), otherRegistration, registration.getCourse(), true));
//                return true;
//            }
//            return false;
//        } else {
//            pendingSwitchDao.save(new PendingSwitch(user, registration, course, false));
//            return false;
//        }
//    }
    
    public boolean addPreferedCourse(String username, int registrationId, int courseId) {
        List<Registration> allRegistrations = registrationDao.findAll();
        List<Course> allCourses = courseDao.findAll();
        User user = userDao.findByUsername(username);
        Registration registration = allRegistrations.stream().filter(r -> r.getId() == registrationId).findFirst().get();
        Course course = allCourses.stream().filter(r -> r.getId() == courseId).findFirst().get();
        registration_PreferedCoursesDao.save(new Registration_PreferedCourses(registration, course));
        registrationDao.save(registration);

        if (course.getClassCapacity() > allRegistrations.stream().filter(r -> r.getCourse().equals(course)).count()) {
            pendingSwitchDao.save(new PendingSwitch(user, registration, course, false));

            return true;
        } else if (allRegistrations.stream().anyMatch(r -> r.getBlock().equals(registration.getBlock()) && r.getPreferedCourses().stream().map(pc -> pc.getCourse()).anyMatch(c -> c.equals(course)))) {
            Optional<Registration> otherRegistration = allRegistrations.stream().filter(r -> r.getBlock().equals(registration.getBlock()) && r.getCourse().equals(course) && r.getPreferedCourses().stream().map(pc -> pc.getCourse()).anyMatch(c -> c.equals(registration.getCourse()))).findFirst();
            if (otherRegistration.isPresent()) {
                pendingSwitchDao.save(new PendingSwitch(user, registration, course, true));
                pendingSwitchDao.save(new PendingSwitch(otherRegistration.get().getUser(), otherRegistration.get(), registration.getCourse(), true));
                return true;
            }
        }
        return false;
    }
}
