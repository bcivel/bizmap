/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redoute.bizmap.dao.impl;

import com.redoute.bizmap.dao.ICustomerDAO;
import com.redoute.bizmap.database.DatabaseSpring;
import com.redoute.bizmap.entity.Customer;
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
public class CustomerDAO implements ICustomerDAO {
    
    @Autowired
    DatabaseSpring databaseSpring;
    
    @Override
    public List<Customer> findAllCustomer() {
        List<Customer> result = new ArrayList<Customer>();
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT idcustomer, ");
        query.append(" customer,  ");
        query.append(" codpos, segment FROM customer ");

        Logger.log(CustomerDAO.class.getName(), Level.INFO, "Connecting to jdbc/bizmap from findAllCustomer");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getString("idcustomer") == null ? 0 : resultSet.getInt("idcustomer"));
                customer.setCustomer(resultSet.getString("customer") == null ? "" : resultSet.getString("customer"));
                customer.setCodpos(resultSet.getString("codpos") == null ? "" : resultSet.getString("codpos"));
                customer.setCustomer(resultSet.getString("segment") == null ? "" : resultSet.getString("segment"));
                
                result.add(customer);
            }

                } catch (SQLException exception) {
                    Logger.log(CustomerDAO.class.getName(), Level.ERROR, exception.toString());
                } finally {
                    resultSet.close();
                }
         
            } catch (SQLException exception) {
                Logger.log(CustomerDAO.class.getName(), Level.ERROR, exception.toString());
            } finally {
                preStat.close();
            }
        
        } catch (SQLException exception) {
            Logger.log(CustomerDAO.class.getName(), Level.ERROR, exception.toString());
        } finally {
            try {
                if (connection != null) {
                    Logger.log(CustomerDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/bizmap from findAllCustomer");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(CustomerDAO.class.getName(), Level.WARN, e.toString());
            }
        }

        return result;
    }
}
