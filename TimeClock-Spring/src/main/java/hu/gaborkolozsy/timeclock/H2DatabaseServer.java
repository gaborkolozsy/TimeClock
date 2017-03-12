/*
 * Copyright (c) 2017, Gábor Kolozsy. All rights reserved.
 * 
 */
package hu.gaborkolozsy.timeclock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import org.h2.tools.Server;

/**
 * Manage the H2 Console {@link org.h2.tools.Server WEB and TCP} servers.
 * 
 * @author Kolozsy Gábor (kolozsygabor@gmail.com)
 * @version 0.0.1-SNAPSHOT
 * 
 * @see java.io.BufferedReader
 * @see java.io.IOException
 * @see java.io.InputStreamReader
 * @see java.sql.SQLException
 */
public class H2DatabaseServer {

    /** <strong>{@code TCP}</strong> server. */
    private static Server tcpServer;
    
    /** <strong>{@code WEB}</strong> server. */
    private static Server webServer;
    
    /**
     * Create a new <strong>TCP</strong> and a new <strong>WEB</strong> server 
     * and start both.<br>
     * Pirnt the status.
     * 
     * @see java.sql.SQLException
     */
    private static void start() {
        try {
            tcpServer = Server.createTcpServer("-tcp", "-tcpAllowOthers").start();
            webServer = Server.createWebServer("-web", "-webAllowOthers").start();
            status();
        } catch (SQLException ex) {
            System.err.println("Can't start the database server!\n"
                    + "The reason is: " + ex.getMessage());
        }
    }
    
    /**
     * Stop the servers.
     */
    private static void stop() {
        tcpServer.stop();
        webServer.stop();
        status();
    }
    
    /**
     * Servers' status print.
     */
    private static void status() {
        System.out.println("Server Status:");
        System.out.printf("TCP server: %s\n", tcpServer.getStatus());
        System.out.printf("WEB server: %s\n", webServer.getStatus());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        start();
        while(true) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter server command (start|stop|status):");
                String command = br.readLine();
                
                if (command.equalsIgnoreCase("start")) {
                    start();
                } else {
                    if (command.equalsIgnoreCase("stop")) {
                        stop();
                    } else {
                        if (command.equalsIgnoreCase("status")) {
                            status();
                        } else {
                            System.err.println("Unknow command!");
                        }
                    }
                }
            } catch (IOException ex) {
                System.err.println("I/O error occure: " + ex.getMessage());
            }
        }
    }

}
