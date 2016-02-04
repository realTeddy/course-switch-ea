/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.service;

import edu.mum.courseswitch.dao.PendingSwitchDao;
import edu.mum.courseswitch.dao.RegistrationDao;
import edu.mum.courseswitch.dao.Registration_PreferedCoursesDao;
import edu.mum.courseswitch.domain.PendingSwitch;
import edu.mum.courseswitch.domain.Registration;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PendingSwitchService {

    @Autowired
    private PendingSwitchDao pendingSwitchDao;
    @Autowired
    private RegistrationDao registrationDao;
    @Autowired
    private Registration_PreferedCoursesDao registration_PreferedCoursesDao;

    public List<PendingSwitch> getAllPendingSwitches() {
        return pendingSwitchDao.findAll();
    }

    public void approve(int pendingSwitchId) {
        PendingSwitch pendingSwitch = pendingSwitchDao.findOne(pendingSwitchId);
        Registration fromRegistration = pendingSwitch.getFromRegistration();
        registrationDao.save(new Registration(pendingSwitch.getUser(), pendingSwitch.getFromRegistration().getBlock(), pendingSwitch.getToCourse()));
        pendingSwitchDao.delete(pendingSwitch);
        registration_PreferedCoursesDao.deleteInBatch(fromRegistration.getPreferedCourses());
        registrationDao.delete(fromRegistration);
    }

    public void reject(int pendingSwitchId) {
        pendingSwitchDao.delete(pendingSwitchId);
    }
}
