package com.ivanduri.exceptions;

public class FileNotFoundError extends AssertionError {
  public FileNotFoundError(String message, Throwable cause) {
    super(message, cause);
  }
}
