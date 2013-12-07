/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redoute.bizmap.entity;

import com.redoute.bizmap.dao.IProductUsedDAO;

/**
 *
 * @author bcivel
 */
public class ProductUsed {
    
    Integer id;
    String customer;
    String produitVu;
    String produitPanier;
    String produitAchat;
    String latitude;
    String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

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

    public String getProduitVu() {
        return produitVu;
    }

    public void setProduitVu(String produitVu) {
        this.produitVu = produitVu;
    }

    public String getProduitPanier() {
        return produitPanier;
    }

    public void setProduitPanier(String produitPanier) {
        this.produitPanier = produitPanier;
    }

    public String getProduitAchat() {
        return produitAchat;
    }

    public void setProduitAchat(String produitAchat) {
        this.produitAchat = produitAchat;
    }
    
    
}
