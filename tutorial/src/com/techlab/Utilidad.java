package com.techlab;

import com.techlab.excepciones.IdNoEncontradaException;
import com.techlab.excepciones.NumeroInvalidoException;
import com.techlab.productos.Producto;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Utilidad {

  public static int numeroIntValido(Scanner teclado, String mensaje) {
    int numero;

    // Elegir un int válido
    while (true) {
      try {
        System.out.print(mensaje);
        numero = teclado.nextInt();
        teclado.nextLine();
        break;
      } catch (InputMismatchException e) {
        System.out.println("El número ingresado no es válido.");
        teclado.nextLine();
      }
    }

    return numero;
  }

  public static double numeroDoubleValido(Scanner teclado, String mensaje) {
    double numero;

    // Elegir un double válido
    while (true) {
      try {
        System.out.print(mensaje);
        numero = teclado.nextDouble();
        teclado.nextLine();
        break;
      } catch (InputMismatchException e) {
        System.out.println("El número ingresado no es válido.");
        teclado.nextLine();
      }
    }

    return numero;
  }

  public static double numeroDoublePositivo(Scanner teclado, String mensaje) {
    double numero;

    while (true) {
      try {
        numero = numeroDoubleValido(teclado, mensaje);

        if (numero < 0) {
          throw new NumeroInvalidoException("El número no puede ser negativo");
        }
        break;
      } catch (NumeroInvalidoException e) {
        System.out.println("Ingrese un número positivo");
      }
    }

    return numero;
  }

  public static int numeroIntPositivo(Scanner teclado, String mensaje) {
    int numero;

    while (true) {
      try {
        numero = numeroIntValido(teclado, mensaje);

        if (numero < 0) {
          throw new NumeroInvalidoException("El número no puede ser negativo");
        }
        break;
      } catch (NumeroInvalidoException e) {
        System.out.println("Ingrese un número positivo");
      }
    }

    return numero;
  }

  public static void dejarEspacios(int cantidad) {
    for (int i = 0; i < cantidad; i++) {
      System.out.println();
    }
  }

  public static void presioneEnterParaContinuar(Scanner scanner) {
    System.out.print("\nPresione 'ENTER' para continuar...");
    String enter = scanner.nextLine();
  }

  public static int posicionProductoId(int id, List<Producto> productos) {

    // Si la ID existe, devolver la posición de productos donde se encuentre
    // Si la ID no existe, devolver -1

    // Si la lista de productos está vacía devolver -1
    if (productos.isEmpty()) {
      return -1;
    }

    // Si la ID es 0, devolver -1
    if (id == 0) {
      return -1;
    }

    // Buscar producto por su id, si existe devolver su posición en la lista productos
    for (int i = 0; i < productos.size(); i++) {
      if (productos.get(i).getId() == id) {
        return i;
      }
    }

    // Si no encontró, devolver -1
    return -1;
  }

  public static int posicionId(Scanner teclado, List<Producto> productos) {
    // Si la lista de productos está vacía, entonces regresar
    if (productos.isEmpty()) {
      System.out.println("La lista de productos se encuentra vacía");
      return -2;
    }

    int id = Utilidad.numeroIntPositivo(teclado, "Ingrese la ID solicitada: ");

    if (Utilidad.posicionProductoId(id, productos) == -1) {
      System.out.println("No existe un producto con la ID solicitada");
      return -1;
    }

    return Utilidad.posicionProductoId(id, productos);
  }
}
