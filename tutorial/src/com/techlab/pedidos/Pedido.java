package com.techlab.pedidos;

import com.techlab.productos.Producto;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

  // Atributos
  private List<Producto> productos = new ArrayList<>();

  // Getters y Setters
  public List<Producto> getProductos() {
    return productos;
  }

  // Métodos
  public void agregarProducto(Producto producto) {

    // Si la ID ya existe, sumar los stock
    for (Producto p : productos) {
      if (p.getId() == producto.getId()) {
        p.setStock(p.getStock() + producto.getStock());
        return;
      }
    }

    // Si la ID no existe, crear un nuevo pedido
    productos.add(producto);
  }
}
