/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.dao;

import edu.mum.courseswitch.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.mum.courseswitch.domain.Registration;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tewodros Ayele Assefa
 */
public interface RegistrationDao extends JpaRepository<Registration, Integer> {

    public List<Registration> findByUserIdAndBlockStartDateGreaterThan(int userId, Date blockStartDate);

    @Query("select distinct pc from Registration r join r.preferedCourses pc where r.user.id = ?1 and r.block.id = ?2")
    public List<Course> findByUserIdAndBlock_Id(int userId, int blockId);
    
    public List<Registration> findByBlock_Id(int blockId);
    
    public List<Registration> findByUser_Id(int userId);
    
    public List<Registration> findByCourse_Id(int courseId);
    
    //Database.getRegistrations().anyMatch(r -> r.getBlock().equals(registration.getBlock()) && r.getPreferedCourses().contains(courseViewModel.getCourse()))) {
    @Query("select count(distinct r) from Registration r join r.preferedCourses pc where r.block.id = ?1 and pc.id = ?2)")
    public int countByBlock_IdAndPreferedCourse_Id(int blockId, int preferedCourseId);
    
    //Database.getRegistrations().stream().filter(r -> r.getCourse().equals(course)).count()
    public int countByCourse_Id(int courseId);
    
    //Database.getRegistrations().stream().filter(r -> r.getBlock().equals(registration.getBlock()) && r.getCourse().equals(course) && r.getPreferedCourses().contains(registration.getCourse())).findFirst();
    @Query("select r from Registration r join r.preferedCourses pc where r.block.id = ?1 and r.course.id = ?2 and pc.id = ?3")
    public Registration findByBlockIdAndCourse_IdPreferedCourse_Id(int blockId, int courseId, int preferedCourseId);
}
