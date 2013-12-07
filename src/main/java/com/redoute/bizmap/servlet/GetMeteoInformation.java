/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redoute.bizmap.servlet;

import com.redoute.bizmap.entity.MeteoInformation;
import com.redoute.bizmap.log.Logger;
import com.redoute.bizmap.service.IGenerateMap;
import com.redoute.bizmap.service.IMeteoService;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.json.JSONArray;
import org.json.JSONObject;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author bcivel
 */
public class GetMeteoInformation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/png");
        
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        String date = policy.sanitize(request.getParameter("date"));
        
        ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        IMeteoService meteoService = appContext.getBean(IMeteoService.class);
        try {
            JSONArray meteoList = new JSONArray();
            try {
                for (MeteoInformation meteo : meteoService.getMeteoInformation(date)) {
                    JSONObject invariantList = new JSONObject();
                    invariantList.put("date", meteo.getDate());
                    invariantList.put("latitude", meteo.getLatitude());
                    invariantList.put("longitude", meteo.getLongitude());
                    invariantList.put("symbol", meteo.getSymbol());
                    meteoList.put(invariantList);
                }
            } catch (Exception ex) {
                response.setContentType("text/html");
                response.getWriter().print(ex.toString());
            }
            response.setContentType("application/json");
            response.getWriter().print(meteoList.toString());
        } catch (Exception e) {
            Logger.log(GetMeteoInformation.class.getName(), Level.FATAL, "" + e);
            response.setContentType("text/html");
            response.getWriter().print(e.getMessage());
        }
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
