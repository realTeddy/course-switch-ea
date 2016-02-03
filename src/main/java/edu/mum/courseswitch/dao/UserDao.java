/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.mum.courseswitch.domain.User;

public interface UserDao extends JpaRepository<User, Integer> {
    //filter(s -> s.getEmail().equals(email) && s.getPassword().equals(password)).findAny()
    public User findByUsernameAndPassword(String username, String password);
    
    public User findByUsername(String username);
}
