/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redoute.bizmap.entity;

/**
 *
 * @author bcivel
 */
public class Customer {
    
    Integer id;
    String customer;
    String codpos;
    String segment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCodpos() {
        return codpos;
    }

    public void setCodpos(String codpos) {
        this.codpos = codpos;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    
}
