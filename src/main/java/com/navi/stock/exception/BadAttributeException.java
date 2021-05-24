package com.navi.stock.exception;

/**
 * Bad Attribute Exception is thrown if the Input given is in wrong format
 */
public class BadAttributeException extends RuntimeException {

  /**
   * Constructs a new exception with the specified detail message.  The
   * cause is not initialized, and may subsequently be initialized by
   * a call to {@link #initCause}.
   *
   * @param message
   *     the detail message. The detail message is saved for
   *     later retrieval by the {@link #getMessage()} method.
   */
  public BadAttributeException(String message) {
    super(message);
  }
}
