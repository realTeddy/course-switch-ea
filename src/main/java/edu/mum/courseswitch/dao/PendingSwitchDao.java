/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import edu.mum.courseswitch.domain.PendingSwitch;
import java.util.List;

public interface PendingSwitchDao extends JpaRepository<PendingSwitch, Integer> {

    public List<PendingSwitch> findByUserId(int userId);

    //anyMatch(p -> p.getUser().equals(user) && p.getToCourse().equals(courseViewModel.getCourse())
    @Query("select ps from PendingSwitch ps where ps.user.id = ?1 and ps.toCourse.id = ?2")
    public int countByUserAndToCourse(int userId, int courseId);
}
