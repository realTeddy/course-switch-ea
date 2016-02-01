/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.service;

import edu.mum.courseswitch.dao.RegistrationDao;
import edu.mum.courseswitch.domain.Course;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

public class PreferenceService {

    @Autowired
    private RegistrationDao registrationDao;

    public List<Course> getPreferences(int courseId) {
        return registrationDao.findByCourse_Id(courseId).stream()
                .map(r -> r.getCourse()).collect(Collectors.toList());
    }
}
