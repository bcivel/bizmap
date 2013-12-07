/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redoute.bizmap.entity;

import com.redoute.bizmap.dao.IProductDAO;

/**
 *
 * @author bcivel
 */
public class Product implements IProductDAO {
    
    Integer id;
    String product;
    String famille;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }
    
    
            
          
}
