package com.ivanduri.utils.commons;

import org.slf4j.LoggerFactory;

public final class Logger {

  private Logger() {}

  public static void error(String nameClass, String message) {
    LoggerFactory.getLogger(nameClass).error(message);
  }

  public static void error(String nameClass, String message, String error) {
    error(nameClass, String.format("%s %s", message, error));
  }

  public static void info(String clase, String mensaje) {
    LoggerFactory.getLogger(clase).info(mensaje);
  }
}
