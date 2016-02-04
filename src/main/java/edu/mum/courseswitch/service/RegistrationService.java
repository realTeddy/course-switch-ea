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
import edu.mum.courseswitch.dao.UserDao;
import edu.mum.courseswitch.domain.Block;
import edu.mum.courseswitch.domain.Course;
import edu.mum.courseswitch.domain.PendingSwitch;
import edu.mum.courseswitch.domain.Registration;
import edu.mum.courseswitch.domain.User;
import edu.mum.courseswitch.dto.CourseDto;
import edu.mum.courseswitch.dto.RegistrationDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
    private BlockDao blockDao;
    
    @Autowired
    private CourseDao courseDao;
    
    @Autowired
    private UserDao userDao;

//    public List<RegistrationDto> getRegistrations(String username) {
//        List<Registration> registrations = registrationDao.findByUser_Username(username);
//
//        List<RegistrationDto> registrationViewModels = new ArrayList<>();
//        for (Registration registration : registrations) {
//            RegistrationDto registrationViewModel = new RegistrationDto(registration);
//            for (CourseDto courseViewModel : registrationViewModel.getPreferedCourses()) {
//                if (courseViewModel.getCourse().getClassCapacity() > registrationDao.countByCourse_Id(courseViewModel.getCourse().getId())) {
//                    courseViewModel.setIsAvailable(true);
//                }
//                if (registrationDao.countByBlock_IdAndPreferedCourse_Id(registration.getBlock().getId(), courseViewModel.getCourse().getId()) > 0) {
//                    courseViewModel.setIsAvailable(true);
//                }
//                if (pendingSwitchDao.countByUser_UsernameAndToCourse_Id(username, courseViewModel.getCourse().getId()) > 0) {
//                    courseViewModel.setIsPendingApproval(true);
//                }
//            }
//            registrationViewModels.add(registrationViewModel);
//        }
//
//        return registrationViewModels;
//    }
    
    public List<RegistrationDto> getRegistrations(String username) {
        List<Registration> allRegistrations = registrationDao.findAll();
        List<PendingSwitch> pendingSwitches = pendingSwitchDao.findAll();
        List<Registration> registrations = allRegistrations.stream().filter(b -> b.getUser().getUsername().equals(username)).collect(Collectors.toList());

        List<RegistrationDto> registrationViewModels = new ArrayList<>();
        for (Registration registration : registrations) {
            RegistrationDto registrationViewModel = new RegistrationDto(registration);
            for (CourseDto courseViewModel : registrationViewModel.getPreferedCourses()) {
                if (courseViewModel.getCourse().getClassCapacity() > allRegistrations.stream().filter(r -> r.getCourse().equals(courseViewModel.getCourse())).count()) {
                    courseViewModel.setIsAvailable(true);
                }
                if (allRegistrations.stream().anyMatch(r -> r.getBlock().equals(registration.getBlock()) && r.getPreferedCourses().contains(courseViewModel.getCourse()))) {
                    courseViewModel.setIsAvailable(true);
                }
                if (pendingSwitches.stream().anyMatch(p -> p.getUser().getUsername().equals(username) && p.getToCourse().equals(courseViewModel.getCourse()))) {
                    courseViewModel.setIsPendingApproval(true);
                }
            }
            registrationViewModels.add(registrationViewModel);
        }

        return registrationViewModels;
    }

    public void register(User user, Block block, Course course, List<Course> preferedCourses) {
        registrationDao.save(new Registration(user, block, course));
    }

    public RegistrationDto addRegistration(String name, int blockId, int courseId) {
        Block block = blockDao.findOne(blockId);
        Course course = courseDao.findOne(courseId);
        User user = userDao.findByUsername(name);
        Registration registration = new Registration(user, block, course);
        registrationDao.save(registration);
        RegistrationDto registrationDto = new RegistrationDto(registration);
        return registrationDto;
    }
}
