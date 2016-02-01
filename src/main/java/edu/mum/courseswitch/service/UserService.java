/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.service;

import edu.mum.courseswitch.dao.UserDao;
import edu.mum.courseswitch.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUser(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }

    public User addUser(String id, String firstName, String lastName, String email, String password) {
        return userDao.save(new User(firstName, lastName, email, password, false));
    }
}
