package com.techlab;

import com.techlab.excepciones.NumeroInvalidoException;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.Producto;
import com.techlab.servicio.ProductoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainTutorial {

  public static void main(String[] args) {

    Scanner teclado = new Scanner(System.in);
    int opcion;

    List<Producto> productos = new ArrayList<>();
    Pedido pedido = new Pedido();
    ProductoService servicio = new ProductoService();

    do {
      // Mostrar las opciones
      System.out.println("""
          1) Agregar producto
          2) Listar productos
          3) Buscar producto
          4) Actualizar producto
          5) Eliminar producto
          6) Crear un pedido
          7) Listar pedidos
          8) Salir
          """);

      // Elegir un número válido
      while (true) {
        opcion = Utilidad.numeroIntValido(teclado, "Elija una opción: ");

        try {
          // Validar la opción que no sea incorrecta
          if (opcion < 1 || opcion > 8) {
            throw new NumeroInvalidoException("Opción inválida");
          }

          break;
        } catch (NumeroInvalidoException e) {
          System.out.println("Opción inválida");
        }
      }

      switch (opcion) {
        case 1: // Agregar producto
          servicio.agregarProducto(teclado, productos);
          Utilidad.presioneEnterParaContinuar(teclado);
          Utilidad.dejarEspacios(30);
          break;
        case 2: // Listar productos
          servicio.listarProductos(productos);
          Utilidad.presioneEnterParaContinuar(teclado);
          Utilidad.dejarEspacios(30);
          break;
        case 3: // Buscar producto
          servicio.buscarProductoPorId(teclado, productos);
          Utilidad.presioneEnterParaContinuar(teclado);
          Utilidad.dejarEspacios(30);
          break;
        case 4: // Actualizar producto
          servicio.actualizarProducto(teclado, productos);
          Utilidad.presioneEnterParaContinuar(teclado);
          Utilidad.dejarEspacios(30);
          break;
        case 5: // Eliminar producto
          servicio.eliminarProductoPorId(teclado, productos);
          Utilidad.presioneEnterParaContinuar(teclado);
          Utilidad.dejarEspacios(30);
          break;
        case 6: // Crear un pedido
          servicio.crearPedido(teclado, productos, pedido);
          Utilidad.presioneEnterParaContinuar(teclado);
          Utilidad.dejarEspacios(30);
          break;
        case 7: // Listar pedidos
          servicio.listarPedidos(pedido);
          Utilidad.presioneEnterParaContinuar(teclado);
          Utilidad.dejarEspacios(30);
          break;
        case 8: // Salir
          break;
      }
    } while (opcion != 8);
  }
}
