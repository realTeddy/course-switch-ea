/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.mum.courseswitch.domain.Course;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface CourseDao extends JpaRepository<Course, Integer> {
    
}