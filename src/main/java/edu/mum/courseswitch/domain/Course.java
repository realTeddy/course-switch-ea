package edu.mum.courseswitch.domain;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Course implements Serializable {

    public Course() {
    }

    public Course(int id, String code, String title, String description, List<Course> prerequisites, String instructor, int classCapacity) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.description = description;
        this.prerequisites = prerequisites != null ? prerequisites : new ArrayList<Course>();
        this.instructor = instructor;
        this.classCapacity = classCapacity;
    }

    @Id
    @GeneratedValue
    private int id;
    
    private String code;
    private String title;
    private String description;
    
    @OneToMany
    private List<Course> prerequisites;
    private String instructor;
    private int classCapacity;

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public String getTitle() {
        return title;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassCapacity() {
        return classCapacity;
    }

    public void setClassCapacity(int classCapacity) {
        this.classCapacity = classCapacity;
    }

    @Override
    public String toString() {
        return title + " (" + code + ")";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        return this.id == other.id;
    }

}
