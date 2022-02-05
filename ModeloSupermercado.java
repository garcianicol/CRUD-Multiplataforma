/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectocrud;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author Andrea Garcia
 */
public class ModeloSupermercado {
    
    private Connection _conexion = null;
    public ModeloSupermercado()
    {
        _conexion = Conexion.getConexion();
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS Supermercado" 
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," 
                + " nombre TEXT NOT NULL," 
                + " cantidad NUMERIC,"
                + " precio DECIMAL(10,2),"
                + " observacion TEXT);";
        try
        {
          Statement comando = _conexion.createStatement();
          int resultado = comando.executeUpdate(sqlCreateTable);
        }
        catch (Exception ex)
        {
          System.err.println(ex.getMessage());
        }
    }
    public ArrayList<Supermercado> obtenerInventario ()
    {
        try
        {
           
       ArrayList Supermercado = new ArrayList<Supermercado>();
       Statement comandoSQL = _conexion.createStatement();
       ResultSet registroEnTabla = comandoSQL.executeQuery("SELECT * FROM Supermercado;");
       while (registroEnTabla.next())
       {
         Supermercado inventarioactual = new Supermercado();
         inventarioactual.setId(registroEnTabla.getInt("id"));
         inventarioactual.setNombre(registroEnTabla.getString("nombre"));
         inventarioactual.setCantidad(registroEnTabla.getInt("cantidad"));
         inventarioactual.setPrecio(registroEnTabla.getDouble("precio"));
         inventarioactual.setObservacion(registroEnTabla.getString("observacion"));
         
         Supermercado.add(inventarioactual);
       }
       return Supermercado;
        }
       catch (Exception ex)
        {
          System.err.println(ex.getMessage());
          return new ArrayList<Supermercado>();
        }
    }
    public Supermercado obtenerInventario (int id)
    {
        try
        {
       PreparedStatement comandoSQL = _conexion.prepareStatement("SELECT * FROM Supermercado where id =?;");
       comandoSQL.setInt(1, id);
       ResultSet registroEnTabla = comandoSQL.executeQuery();
       Supermercado inventarioactual = new Supermercado();
       while (registroEnTabla.next())
       {
         inventarioactual.setId(registroEnTabla.getInt("id"));
         inventarioactual.setNombre(registroEnTabla.getString("nombre"));
         inventarioactual.setCantidad(registroEnTabla.getDouble("cantidad"));
         inventarioactual.setPrecio(registroEnTabla.getDouble("precio"));
         inventarioactual.setObservacion(registroEnTabla.getString("observacion"));
         
         break;
       }
       return inventarioactual;
        }
       catch (Exception ex)
        {
          System.err.println(ex.getMessage());
          return null;
        }
    }
    public int agregarInventario(Supermercado nuevoInventario)
    {
        try
        {
          String insertSql = "INSERT INTO Supermercado (nombre, cantidad, precio, observacion) values (?, ?, ?, ?);";
          PreparedStatement comandoSQL = _conexion.prepareStatement(insertSql);
          comandoSQL.setString(1, nuevoInventario.getNombre());
          comandoSQL.setDouble(2, nuevoInventario.getCantidad());
          comandoSQL.setDouble(3, nuevoInventario.getPrecio());
          comandoSQL.setString(4, nuevoInventario.getObservacion());
         
          int RegistroAfectados = comandoSQL.executeUpdate();
          comandoSQL.close();
          return RegistroAfectados;
        }
      catch (Exception ex)
      {
        System.err.println(ex.getMessage());
        return 0;
      }
    }
    public int actualizarInventario (Supermercado updateInventario)
    {
        try
        {
          String updateSql = "UPDATE Supermercado SET nombre=?, cantidad=?, precio=?, observacion=?,  where id =?;";
          PreparedStatement comandoSQL = _conexion.prepareStatement(updateSql);
          comandoSQL.setString(1, updateInventario.getNombre());
           comandoSQL.setDouble(2, updateInventario.getCantidad());
          comandoSQL.setDouble(3, updateInventario.getPrecio());
          comandoSQL.setString(4, updateInventario.getObservacion());
          comandoSQL.setInt(5, updateInventario.getId());
         
          int RegistroAfectados = comandoSQL.executeUpdate();
          comandoSQL.close();
          return RegistroAfectados;
        }
      catch (Exception ex)
      {
        System.err.println(ex.getMessage());
        return 0;
      }
    }
    public int deleteInventario (Supermercado delInventario)
    {
        try
        {
          String deleteSql = "DELETE FROM Supermercado where id =?;";
          PreparedStatement comandoSQL = _conexion.prepareStatement(deleteSql);
          comandoSQL.setInt(1, delInventario.getId());
          int RegistroAfectados = comandoSQL.executeUpdate();
          comandoSQL.close();
          return RegistroAfectados;
        }
      catch (Exception ex)
      {
        System.err.println(ex.getMessage());
        return 0;
      }
    }
}
