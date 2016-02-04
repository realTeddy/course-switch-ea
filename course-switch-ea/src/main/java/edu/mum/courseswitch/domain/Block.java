/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Block implements Serializable {

    public Block() {
    }

    public Block(List<Course> courses, Date startDate, int id) {
        this.courses = courses;
        this.startDate = startDate;
        this.id = id;
    }

    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    @JoinColumn(name = "blockId")
    private List<Course> courses;
    
    @Temporal(TemporalType.DATE)
    private Date startDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return startDate.toString();
    }
}
