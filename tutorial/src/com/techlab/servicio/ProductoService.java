package com.techlab.servicio;

import com.techlab.Utilidad;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.Producto;
import java.util.List;
import java.util.Scanner;

public class ProductoService {

  // ====== Métodos ======

  // Agregar producto
  public void agregarProducto(Scanner teclado, List<Producto> productos) {
    Utilidad.dejarEspacios(30);
    System.out.println("========== AGREGAR PRODUCTO ==========\n");

    System.out.println("Datos del nuevo producto");
    System.out.print("Nombre: ");
    String nombre = teclado.nextLine();
    double precio = Utilidad.numeroDoublePositivo(teclado, "Precio: ");
    int stock = Utilidad.numeroIntPositivo(teclado, "Stock: ");

    Producto producto = new Producto(nombre, precio, stock);

    productos.add(producto);
  }

  // Listar productos
  public void listarProductos(List<Producto> productos) {
    Utilidad.dejarEspacios(30);
    System.out.println("========== LISTA DE PRODUCTOS ==========\n");

    for (Producto p : productos) {
      System.out.println(p.toString());
    }
  }

  // Buscar producto (por ID)
  public void buscarProductoPorId(Scanner teclado, List<Producto> productos) {
    Utilidad.dejarEspacios(30);
    System.out.println("========== BUSCAR PRODUCTO POR ID ==========\n");

    int posicion = Utilidad.posicionId(teclado, productos);

    if (posicion >= 0) {
      System.out.println(productos.get(posicion).toString());
    }
  }

  // Actualizar producto
  public void actualizarProducto(Scanner teclado, List<Producto> productos) {
    Utilidad.dejarEspacios(30);
    System.out.println("========== ACTUALIZAR PRODUCTO POR ID ==========\n");

    int posicion = Utilidad.posicionId(teclado, productos);

    if (posicion >= 0) {
      System.out.println("Esta por actualizar el producto:");
      System.out.println(productos.get(posicion).toString());
      System.out.println("\nIngrese los nuevos valores");
      // Ingresar los nuevos valores del producto
      System.out.print("Nombre: ");
      String nombre = teclado.nextLine();
      double precio = Utilidad.numeroDoublePositivo(teclado, "Precio: ");
      int stock = Utilidad.numeroIntPositivo(teclado, "Stock: ");

      // Actualizar los valores del producto
      productos.get(posicion).setNombre(nombre);
      productos.get(posicion).setPrecio(precio);
      productos.get(posicion).setStock(stock);
    }
  }

  // Eliminar producto
  public void eliminarProductoPorId(Scanner teclado, List<Producto> productos) {
    Utilidad.dejarEspacios(30);
    System.out.println("========== ELIMINAR PRODUCTO POR ID ==========\n");

    int posicion = Utilidad.posicionId(teclado, productos);
    String opcion;

    if (posicion >= 0) {
      System.out.println("Esta por eliminar el producto:");
      System.out.println(productos.get(posicion).toString());

      System.out.print("\nConfirmar si/no: ");
      opcion = teclado.nextLine();

      if (opcion.equalsIgnoreCase("si")) {
        productos.remove(posicion);
      } else if (opcion.equalsIgnoreCase("no")) {
        System.out.println("Se ha cancelado la eliminación del producto.");
      } else {
        System.out.println("La opción ingresada no es válida.");
      }
    }
  }

  // Crear un pedido
  public void crearPedido(Scanner teclado, List<Producto> productos, Pedido pedido) {
    Utilidad.dejarEspacios(30);
    System.out.println("========== CREAR PEDIDO ==========\n");

    int stock;

    // 1. Pedir ID del producto
    int posicion = Utilidad.posicionId(teclado, productos);

    if (posicion >= 0) {

      System.out.println("\nProducto solicitado: ");
      System.out.println(productos.get(posicion).toString());

      // 2. Manejar stock
      stock = Utilidad.numeroIntPositivo(teclado, "\nStock solicitado: ");

      // Si el stock pedido es 0, regresar
      if (stock == 0) {
        System.out.println("\nNo se puede solicitar la cantidad 0 de un producto.");
        return;
      }

      // Si el stock pedido es mayor que el disponible, regresar
      if (stock > productos.get(posicion).getStock()) {
        System.out.println("\nNo hay stock suficiente.");
        return;
      }

      // Si el stock es correcto, manejar sus cantidades
      // Reducir el stock disponible del producto
      productos.get(posicion).reducirStock(stock);

      // Creamos el producto en la lista de pedidos
      int id = productos.get(posicion).getId();
      String nombre = productos.get(posicion).getNombre();
      double precio = productos.get(posicion).getPrecio();

      Producto producto = new Producto(id, nombre, precio, stock);

      pedido.agregarProducto(producto);
    }
  }

  // Listar pedidos
  public void listarPedidos(Pedido pedido) {
    Utilidad.dejarEspacios(30);
    System.out.println("========== LISTA DE PEDIDOS ==========\n");

    // Si esta la lista de pedidos vacía, regresar
    if (pedido.getProductos().isEmpty()) {
      System.out.println("La lista de pedidos se encuentra vacía.");
      return;
    }

    double total = 0;

    // Mostrar los productos
    for (Producto p : pedido.getProductos()) {
      System.out.println(p.toString());

      // total = total + p.precioTotal();
      total += p.precioTotal();
    }

    // %.2f -> mostrar con 2 decimales
    // %n -> dejar una linea
    System.out.printf("%nPrecio final: $%.2f%n", total);
  }
}
