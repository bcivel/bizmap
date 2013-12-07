/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redoute.bizmap.service.impl;

import com.redoute.bizmap.dao.IProductUsedDAO;
import com.redoute.bizmap.entity.ProductUsed;
import com.redoute.bizmap.service.IProductUsedService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class ProductUsedService implements IProductUsedService {

    @Autowired
    IProductUsedDAO productUsedDAO;
    
    @Override
    public List<ProductUsed> getProduct(String start, String end) {
        return productUsedDAO.findAllProduct(start,end);
    }
    
}
