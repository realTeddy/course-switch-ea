/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.dto;

import edu.mum.courseswitch.domain.Course;

/**
 *
 * @author Tewodros Ayele Assefa
 */
public class CourseDto {

    public CourseDto(Course course, boolean isAvailable, boolean isPendingApproval) {
        this.course = course;
        this.isAvailable = isAvailable;
        this.isPendingApproval = isPendingApproval;
    }
    
    private Course course;
    private boolean isAvailable;
    private boolean isPendingApproval;

    public boolean isIsPendingApproval() {
        return isPendingApproval;
    }

    public void setIsPendingApproval(boolean isPendingApproval) {
        this.isPendingApproval = isPendingApproval;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
