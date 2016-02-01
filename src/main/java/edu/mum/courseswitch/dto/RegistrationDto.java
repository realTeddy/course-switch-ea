/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.dto;

import edu.mum.courseswitch.domain.Block;
import edu.mum.courseswitch.domain.Course;
import edu.mum.courseswitch.domain.Registration;
import edu.mum.courseswitch.domain.User;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDto {

    public RegistrationDto(int id, User user, Block block, Course course, List<CourseDto> preferedCourses) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.block = block;
        this.preferedCourses = preferedCourses != null ? preferedCourses : new ArrayList<CourseDto>();
    }

    public RegistrationDto(Registration registration) {
        this.id = registration.getId();
        this.user = registration.getUser();
        this.course = registration.getCourse();
        this.block = registration.getBlock();
        this.preferedCourses = new ArrayList<>();
        for (Course regCourse : registration.getPreferedCourses()) {
            this.preferedCourses.add(new CourseDto(regCourse, false, false));
        }
    }

    private int id;
    private User user;
    private Course course;
    private Block block;
    private List<CourseDto> preferedCourses;

    public List<CourseDto> getPreferedCourses() {
        return preferedCourses;
    }

    public void setPreferedCourses(List<CourseDto> preferedCourses) {
        this.preferedCourses = preferedCourses;
    }

    public void addPreferedCourse(CourseDto preferedCourse) {
        this.preferedCourses.add(preferedCourse);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
