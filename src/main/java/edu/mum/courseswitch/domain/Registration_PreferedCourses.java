/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Tewodros Ayele Assefa
 */
@Entity
public class Registration_PreferedCourses {

    public Registration_PreferedCourses() {
    }

    public Registration_PreferedCourses(Registration registration, Course course) {
        this.registration = registration;
        this.course = course;
    }

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Registration registration;
    @ManyToOne
    private Course course;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
