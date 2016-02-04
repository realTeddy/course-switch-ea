/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PendingSwitch implements Serializable {

    public PendingSwitch() {
    }

    public PendingSwitch(User user, Registration fromRegistration, Course toCourse, boolean isSwap) {
        this.user = user;
        this.fromRegistration = fromRegistration;
        this.toCourse = toCourse;
        this.isSwap = isSwap;
    }
    
    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Registration fromRegistration;
    
    @ManyToOne
    private Course toCourse;
    private boolean isSwap;

    public boolean isIsSwap() {
        return isSwap;
    }

    public void setIsSwap(boolean isSwap) {
        this.isSwap = isSwap;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Registration getFromRegistration() {
        return fromRegistration;
    }

    public void setFromRegistration(Registration fromRegistration) {
        this.fromRegistration = fromRegistration;
    }

    public Course getToCourse() {
        return toCourse;
    }

    public void setToCourse(Course toCourse) {
        this.toCourse = toCourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
