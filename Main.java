/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectocrud;
import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;
/**
 *
 * @author Andrea Garcia
 */
public class Main {
    
     private static Scanner entrada = new Scanner(System.in);
    private static ModeloSupermercado model = new ModeloSupermercado();
    public static void main (String[] args)
    {
        Utilidades.encabezado("Gestion de Inventario");
        String menuOption = "";
        System.out.println();
        Utilidades.menu();
        System.out.println("\nIngrese opcion a ingresar: ");
        menuOption = entrada.nextLine().toUpperCase();
        while (!menuOption.contentEquals("S"))
        {
            switch (menuOption) {
                case "N":
                    insertarInventario();
                    break;
                case "A":
                    actualizarInventario();
                    break;
                case "E":
                    eliminarInventario();
                    break;
                default:
                    Utilidades.print("Opcion no encontrada!!!");
            }
        Utilidades.menu();
        menuOption = entrada.nextLine().toUpperCase();
        }
    }
    private static void insertarInventario()
    {
      Supermercado nuevoInventario = new Supermercado();
      Utilidades.encabezado("Nuevo Inventario");
      nuevoInventario.setId(Integer.parseInt(Utilidades.capturarCampo(entrada, "Id", "")));
      nuevoInventario.setNombre(Utilidades.capturarCampo(entrada, "Nombre", ""));
      nuevoInventario.setCantidad(Integer.parseInt(Utilidades.capturarCampo(entrada, "Cantidad", "10")));
      nuevoInventario.setPrecio(Double.parseDouble(Utilidades.capturarCampo(entrada, "Precio", "100.00")));
      nuevoInventario.setObservacion(Utilidades.capturarCampo(entrada, "Observacion", ""));
      Utilidades.separador();
      int insertado = model.agregarInventario(nuevoInventario);
      if (insertado > 0)
      {
       Utilidades.print("Inventario Agregado Satisfactoriamente!");
      }
      Utilidades.separador();
    }
     private static void actualizarInventario()
    {
      Supermercado updateInventario = new Supermercado();
      int setId;
      Utilidades.encabezado("Inventario a Actualizar");
      updateInventario.setId(Integer.parseInt(Utilidades.capturarCampo(entrada, "Id", "")));
      updateInventario = model.obtenerInventario(updateInventario.getId());
      updateInventario.setNombre(Utilidades.capturarCampo(entrada, "Nombre",
              updateInventario.getNombre()));
      updateInventario.setCantidad(Double.parseDouble(Utilidades.capturarCampo(entrada, 
              "Cantidad", Double.toString(updateInventario.getCantidad()))));
      updateInventario.setPrecio(Double.parseDouble(Utilidades.capturarCampo(entrada, 
              "Precio", Double.toString(updateInventario.getPrecio()))));
      updateInventario.setObservacion(Utilidades.capturarCampo(entrada, 
              "Observacion", updateInventario.getObservacion()));
      int actualizar = model.actualizarInventario(updateInventario);
      if (actualizar > 0)
      {
       Utilidades.print("Inventario Actualizado Satisfactoriamente!");
      }
      Utilidades.separador();
    }
   private static void eliminarInventario()
   {
     Supermercado delProducto = new Supermercado();
     int setId;
     Utilidades.encabezado("Producto a Eliminar");
     delProducto.setId(Integer.parseInt(Utilidades.capturarCampo(entrada, "Id", "")));
     int eliminar = model.deleteInventario(delProducto);
     if (eliminar != -1)
      {
        Utilidades.print("Inventario Eliminado Satisfactoriamente!");
      }
      Utilidades.separador();
   }
}
