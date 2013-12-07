/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redoute.bizmap.service;

import com.redoute.bizmap.entity.MeteoInformation;
import com.redoute.bizmap.entity.ProductUsed;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IProductUsedService {
    
     List<ProductUsed> getProduct(String start, String end); 
}
