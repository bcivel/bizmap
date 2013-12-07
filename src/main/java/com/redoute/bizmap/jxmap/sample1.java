/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redoute.bizmap.jxmap;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.OSMTileFactoryInfo;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;

/**
 *
 * @author bcivel
 */
public class sample1
{
        /**
         * @param args the program args (ignored)
     * @return 
         */
        public Image generateLineChart()
        {
                JXMapViewer mapViewer = new JXMapViewer();

                // Create a TileFactoryInfo for OpenStreetMap
                TileFactoryInfo info = new OSMTileFactoryInfo();
                DefaultTileFactory tileFactory = new DefaultTileFactory(info);
                mapViewer.setTileFactory(tileFactory);
                
                // Use 8 threads in parallel to load the tiles
                tileFactory.setThreadPoolSize(8);

                // Set the focus
                GeoPosition frankfurt = new GeoPosition(50.11, 8.68);

                mapViewer.setZoom(8);
                mapViewer.setAddressLocation(frankfurt);
                
// Display the viewer in a JFrame
                JFrame frame = new JFrame("JXMapviewer2 Example 1");
                frame.getContentPane().add(mapViewer);
                frame.setSize(800, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                Image toto = frame.createImage(800, 600);
                
                return toto;
        }
}