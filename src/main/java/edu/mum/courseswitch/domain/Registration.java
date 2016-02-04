package edu.mum.courseswitch.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Registration implements Serializable {

    public Registration() {
    }

    public Registration(User user, Block block, Course course, List<Course> preferedCourses) {
        this.user = user;
        this.course = course;
        this.block = block;
        this.preferedCourses = preferedCourses != null ? preferedCourses : new ArrayList<Course>();
    }

    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Course course;
    
    @ManyToOne
    private Block block;
    
    @OneToMany
    private List<Course> preferedCourses;
    
    public List<Course> getPreferedCourses() {
        return preferedCourses;
    }
    
    public void setPreferedCourses(List<Course> preferedCourses) {
        this.preferedCourses = preferedCourses;
    }
    
    public void addPreferedCourse(Course preferedCourse) {
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
