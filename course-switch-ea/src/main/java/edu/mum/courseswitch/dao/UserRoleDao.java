/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.dao;

import edu.mum.courseswitch.domain.UserRoles;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author henok
 */
public interface UserRoleDao extends JpaRepository<UserRoles, Integer> {
    List<UserRoles> findByUsername(String username);
}
