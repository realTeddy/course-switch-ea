/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.service;

import edu.mum.courseswitch.dao.UserDao;
import edu.mum.courseswitch.dao.UserRoleDao;
import edu.mum.courseswitch.domain.User;
import java.util.stream.Collectors;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserService {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserRoleDao userRoleDao;

    public User getUser(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    public User addUser(String id, String username, String firstName, String lastName, String password, boolean enabled) {
        return userDao.save(new User(id, username, firstName, lastName, password, enabled));
    }
    
    public List<String> getUserRoles(String username) {
        return userRoleDao.findByUsername(username).stream().map(ur -> ur.getRole()).collect(Collectors.toList());
    }
}
