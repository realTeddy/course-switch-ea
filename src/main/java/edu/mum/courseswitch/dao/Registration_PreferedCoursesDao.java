/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.dao;

import edu.mum.courseswitch.domain.Registration_PreferedCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tewodros Ayele Assefa
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface Registration_PreferedCoursesDao extends JpaRepository<Registration_PreferedCourses, Integer>{
    
}
