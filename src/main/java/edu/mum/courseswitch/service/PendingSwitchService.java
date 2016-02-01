/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.service;

import edu.mum.courseswitch.dao.PendingSwitchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PendingSwitchService {
    @Autowired
    private PendingSwitchDao pendingSwitchDao;
    
    @RequestMapping("/")
    
}
