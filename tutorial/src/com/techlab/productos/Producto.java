package com.techlab.productos;

public class Producto {

  // Atributos
  private static int idContador = 1;

  private int id;
  private String nombre;
  private double precio;
  private int stock;

  // Constructores
  public Producto(String nombre, double precio, int stock) {
    this.nombre = nombre;
    this.precio = precio;
    this.stock = stock;

    // id única autoincremental
    id = idContador;
    idContador++;
  }

  // Constructor de pedido solicitado
  // La id es personalizada y no autoincremental
  public Producto(int id, String nombre, double precio, int stock) {
    this.id = id;
    this.nombre = nombre;
    this.precio = precio;
    this.stock = stock;
  }

  // Métodos
  public String toString() {
    /*
    String formato:
    %n -> dejar un espacio
    %s -> string
    %f -> decimal
    %.2f -> decimal solo con 2 números decimales
    %d -> entero
     */

    return String.format("ID: %d | Nombre: %s | Precio: %.2f | Stock: %d",
        id, nombre, precio, stock);
  }

  // Getters y Setters
  public int getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  // Metodos
  public void reducirStock(int cantidad) {
    // stock = stock - cantidad;
    stock -= cantidad;
  }

  // Calcular precio
  public double precioTotal() {
    return precio * stock;
  }
}
