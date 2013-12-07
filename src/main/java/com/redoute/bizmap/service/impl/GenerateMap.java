/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redoute.bizmap.service.impl;

import com.redoute.bizmap.log.Logger;
import com.redoute.bizmap.service.IGenerateMap;
import com.redoute.bizmap.servlet.DrawMap;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import org.apache.log4j.Level;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.OSMTileFactoryInfo;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;
import org.springframework.stereotype.Service;

/**
 *
 * @author bcivel
 */
@Service
public class GenerateMap implements IGenerateMap {
    
    @Override
    public BufferedImage generateLineChart()
        {
                JXMapViewer mapViewer = new JXMapViewer();

                // Create a TileFactoryInfo for OpenStreetMap
                TileFactoryInfo info = new OSMTileFactoryInfo();
                DefaultTileFactory tileFactory = new DefaultTileFactory(info);
                mapViewer.setTileFactory(tileFactory);
                
                // Use 8 threads in parallel to load the tiles
                tileFactory.setThreadPoolSize(8);

                // Set the focus
                GeoPosition frankfurt = new GeoPosition(-7.502778, 111.263056);

                mapViewer.setZoom(12);
                mapViewer.setAddressLocation(frankfurt);
                //Image image = mapViewer.createImage(800, 600);
                //Logger.log(GenerateMap.class.getName(), Level.INFO, image.toString());
                // Display the viewer in a JFrame
                JFrame frame = new JFrame("JXMapviewer2 Example 1");
                frame.getContentPane().add(mapViewer);
                frame.setSize(800, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                
                BufferedImage bufferedImage = new BufferedImage(
                frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics gr = bufferedImage.getGraphics();
                frame.printAll(gr);
                gr.dispose();
//                Image image = frame.createImage(800, 600);
                
                return bufferedImage;
        }
    
}
