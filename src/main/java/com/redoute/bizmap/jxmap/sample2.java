/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.redoute.bizmap.jxmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.OSMTileFactoryInfo;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.DefaultWaypoint;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;
import org.jdesktop.swingx.painter.CompoundPainter;
import org.jdesktop.swingx.painter.Painter;

/**
 *
 * @author bcivel
 */


/**
* A simple sample application that shows
* a OSM map of Europe containing a route with waypoints
* @author Martin Steiger
*/
public class sample2
{
        /**
         * @param args the program args (ignored)
         */
        public static <T> void main(String[] args)
        {
                JXMapViewer mapViewer = new JXMapViewer();

                // Display the viewer in a JFrame
                JFrame frame = new JFrame("JXMapviewer2 Example 2");
                frame.getContentPane().add(mapViewer);
                frame.setSize(800, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                // Create a TileFactoryInfo for OpenStreetMap
                TileFactoryInfo info = new OSMTileFactoryInfo();
                DefaultTileFactory tileFactory = new DefaultTileFactory(info);
                tileFactory.setThreadPoolSize(8);
                mapViewer.setTileFactory(tileFactory);
                
                GeoPosition frankfurt = new GeoPosition(51, 7, 0, 4, 41, 0);
                GeoPosition wiesbaden = new GeoPosition(50, 5, 0, 8, 14, 0);
                GeoPosition mainz = new GeoPosition(48, 0, 0, -4, 06, 0);
                GeoPosition darmstadt = new GeoPosition(43, 52, 0, 4, 39, 0);
                GeoPosition offenbach = new GeoPosition(48, 6, 0, 8, 46, 0);

                // Create a track from the geo-positions
                List<GeoPosition> track = Arrays.asList(frankfurt, wiesbaden, mainz, darmstadt, offenbach);
                //RoutePainter routePainter = new RoutePainter(track);

                // Set the focus
                mapViewer.zoomToBestFit(new HashSet<GeoPosition>(track), 1);

                // Create waypoints from the geo-positions
                Set<Waypoint> waypoints = new HashSet<Waypoint>(Arrays.asList(
                                new DefaultWaypoint(frankfurt),
                                new DefaultWaypoint(wiesbaden),
                                new DefaultWaypoint(mainz),
                                new DefaultWaypoint(darmstadt),
                                new DefaultWaypoint(offenbach)));

                // Create a waypoint painter that takes all the waypoints
                WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
                waypointPainter.setWaypoints(waypoints);
                
                // Create a compound painter that uses both the route-painter and the waypoint-painter
                List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
               // painters.add(routePainter);
                painters.add(waypointPainter);
                
                
                CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
                mapViewer.setOverlayPainter(painter);
        }
}
