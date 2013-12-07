/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redoute.bizmap.dao;

import com.redoute.bizmap.entity.ProductUsed;
import java.util.List;

/**
 *
 * @author bcivel
 */
public interface IProductUsedDAO {
    
    List<ProductUsed> findAllProduct(String start, String end);
}
