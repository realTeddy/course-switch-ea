/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.courseswitch.service;

import edu.mum.courseswitch.dao.RegistrationDao;
import edu.mum.courseswitch.domain.Block;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

public class BlockService {
    
    @Autowired
    private RegistrationDao registrationDao;

    public List<Block> getBlocks(int userId) {
        return registrationDao.findByUser_Id(userId).stream().map(r -> r.getBlock()).collect(Collectors.toList());
    }
}
