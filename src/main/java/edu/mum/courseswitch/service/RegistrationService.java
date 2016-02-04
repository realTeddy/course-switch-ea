/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.service;

import edu.mum.courseswitch.dao.PendingSwitchDao;
import edu.mum.courseswitch.dao.RegistrationDao;
import edu.mum.courseswitch.domain.Block;
import edu.mum.courseswitch.domain.Course;
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

    public List<RegistrationDto> getRegistrations(String username) {
        List<Registration> registrations = registrationDao.findByUser_Username(username);

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
                if (pendingSwitchDao.countByUser_UsernameAndToCourse_Id(username, courseViewModel.getCourse().getId()) > 0) {
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
}
