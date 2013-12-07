/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redoute.bizmap.service.impl;

import com.redoute.bizmap.dao.IMeteoDAO;
import com.redoute.bizmap.entity.MeteoInformation;
import com.redoute.bizmap.service.IMeteoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class MeteoService implements IMeteoService {
    
    @Autowired
    IMeteoDAO meteoDAO;
    
    @Override
    public List<MeteoInformation> getMeteoInformation(String date) {
        return meteoDAO.getMeteoInformation(date);
    }
    
}
