package com.techlab.excepciones;

public class IdNoEncontradaException extends RuntimeException {

  public IdNoEncontradaException(int id) {
    super("No se encontró la id: " + id);
  }
}
