/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.service;

import edu.mum.courseswitch.dao.CourseDao;
import edu.mum.courseswitch.dao.PendingSwitchDao;
import edu.mum.courseswitch.dao.RegistrationDao;
import edu.mum.courseswitch.domain.Block;
import edu.mum.courseswitch.domain.Course;
import edu.mum.courseswitch.domain.PendingSwitch;
import edu.mum.courseswitch.domain.Registration;
import edu.mum.courseswitch.domain.User;
import edu.mum.courseswitch.dto.CourseDto;
import edu.mum.courseswitch.dto.RegistrationDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class RegistrationService {

    @Autowired
    private RegistrationDao registrationDao;

    @Autowired
    private PendingSwitchDao pendingSwitchDao;

    @Autowired
    private CourseDao courseDao;

    public List<RegistrationDto> getRegistrations(User user) {
        List<Registration> registrations = registrationDao.findByUser_Id(user.getId());

        List<RegistrationDto> registrationViewModels = new ArrayList<>();
        for (Registration registration : registrations) {
            RegistrationDto registrationViewModel = new RegistrationDto(registration);
            for (CourseDto courseViewModel : registrationViewModel.getPreferedCourses()) {
                if (courseViewModel.getCourse().getClassCapacity() > registrationDao.countByCourse_Id(courseViewModel.getCourse().getId())) {
                    courseViewModel.setIsAvailable(true);
                }
                if (registrationDao.countByBlock_IdAndPreferedCourse_Id(registration.getBlock().getId(), courseViewModel.getCourse().getId()) > 0) {
                    courseViewModel.setIsAvailable(true);
                }
                if (pendingSwitchDao.countByUserAndToCourse(user.getId(), courseViewModel.getCourse().getId()) > 0) {
                    courseViewModel.setIsPendingApproval(true);
                }
            }
            registrationViewModels.add(registrationViewModel);
        }

        return registrationViewModels;
    }

    public void register(User user, Block block, Course course, List<Course> preferedCourses) {
        registrationDao.save(new Registration(user, block, course, preferedCourses));
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
