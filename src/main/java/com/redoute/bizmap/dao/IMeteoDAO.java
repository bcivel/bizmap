/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redoute.bizmap.dao;

import com.redoute.bizmap.entity.MeteoInformation;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IMeteoDAO {
    
    List<MeteoInformation> getMeteoInformation(String date);
}
