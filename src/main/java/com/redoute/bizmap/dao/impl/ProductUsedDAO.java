/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redoute.bizmap.dao.impl;

import com.redoute.bizmap.dao.IProductUsedDAO;
import com.redoute.bizmap.database.DatabaseSpring;
import com.redoute.bizmap.entity.Customer;
import com.redoute.bizmap.entity.ProductUsed;
import com.redoute.bizmap.log.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bcivel
 */
@Repository
public class ProductUsedDAO implements IProductUsedDAO {
    
    
 
    @Autowired
    DatabaseSpring databaseSpring;
    
    @Override
    public List<ProductUsed> findAllProduct(String start, String end) {
        List<ProductUsed> result = new ArrayList<ProductUsed>();
        
        StringBuilder query = new StringBuilder();
        


        query.append("SELECT latitude, longitude, count(achat) as toto, count(panier), count(vu) FROM productused a ");
        query.append(" join geographicdata b on a.codpos=b.codpos ");
        query.append(" join customer c on a.customer=c.customer ");
        query.append(" where a.productid between '");
        query.append(start);
        query.append("' and '");
        query.append(end);
        query.append("' group by a.codpos ");
        
        
        Logger.log(CustomerDAO.class.getName(), Level.INFO, "Connecting to jdbc/bizmap from findAllCustomer");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                

            while (resultSet.next()) {
                ProductUsed productUsed = new ProductUsed();
                productUsed.setLatitude(resultSet.getString("latitude") == null ? "" : resultSet.getString("latitude"));
                productUsed.setLongitude(resultSet.getString("longitude") == null ? "" : resultSet.getString("longitude"));
                productUsed.setProduitAchat(resultSet.getString("toto") == null ? "" : resultSet.getString("toto"));
                
                result.add(productUsed);
            }

                } catch (SQLException exception) {
                    Logger.log(ProductUsedDAO.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
         
            } catch (SQLException exception) {
                Logger.log(ProductUsedDAO.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(ProductUsedDAO.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(ProductUsedDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/bizmap from findAllCustomer");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(ProductUsedDAO.class.getName(), Level.WARN, e.toString());
            }
        }

        return result;
    }
    
    
    
    
}
