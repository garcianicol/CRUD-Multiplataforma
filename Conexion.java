/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectocrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Andrea Garcia
 */
public class Conexion {
    private static Connection _conexion = null;
    private Conexion() 
    {
      
    }
    public static Connection getConexion ()
    {
      try
      {
        if (_conexion == null)
        {
            Class.forName("org.sqlite.JDBC"); //Java Database Connection
            _conexion = DriverManager.getConnection("jdbc:sqlite:Supermercado.db");
        }
        return _conexion;
      } 
      catch (Exception ex)
      {
         System.err.println("Error: " + ex.getMessage());
         return null;
      }
    }
}