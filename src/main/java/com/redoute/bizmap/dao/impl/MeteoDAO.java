/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redoute.bizmap.dao.impl;

import com.redoute.bizmap.dao.IMeteoDAO;
import com.redoute.bizmap.database.DatabaseSpring;
import com.redoute.bizmap.entity.Customer;
import com.redoute.bizmap.entity.MeteoInformation;
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
public class MeteoDAO implements IMeteoDAO {

    @Autowired
    DatabaseSpring databaseSpring;
    
    @Override
    public List<MeteoInformation> getMeteoInformation(String date) {
        
        List<MeteoInformation> result = new ArrayList<MeteoInformation>();
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT date, ");
        query.append(" latitude,  ");
        query.append(" longitude, symbol FROM meteoinformation ");

        Logger.log(CustomerDAO.class.getName(), Level.INFO, "Connecting to jdbc/bizmap from getMeteoInformation");
        Connection connection = this.databaseSpring.connect();
        try {
            PreparedStatement preStat = connection.prepareStatement(query.toString());
            try {
                ResultSet resultSet = preStat.executeQuery();
                try {
                

            while (resultSet.next()) {
                MeteoInformation meteoInformation = new MeteoInformation();
                meteoInformation.setDate(resultSet.getString("date") == null ? "" : resultSet.getString("date"));
                meteoInformation.setLatitude(resultSet.getString("latitude") == null ? "" : resultSet.getString("latitude"));
                meteoInformation.setLongitude(resultSet.getString("longitude") == null ? "" : resultSet.getString("longitude"));
                meteoInformation.setSymbol(resultSet.getString("symbol") == null ? "" : resultSet.getString("symbol"));
                result.add(meteoInformation);
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
                    Logger.log(CustomerDAO.class.getName(), Level.INFO, "Disconnecting to jdbc/bizmap from getMeteoInformation");
                    connection.close();
                }
            } catch (SQLException e) {
                Logger.log(CustomerDAO.class.getName(), Level.WARN, e.toString());
            }
        }

        return result;
    }
    
}
