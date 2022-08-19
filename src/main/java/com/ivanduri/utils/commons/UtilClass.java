package com.ivanduri.utils.commons;

import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class UtilClass {

  private UtilClass() {
    super();
  }

  public static <T> void setIfNotNull(final Consumer<T> setter, final T value) {
    if (value != null) {
      setter.accept(value);
    }
  }

  public static void setIfNotNullParseInt(final IntConsumer setter, final String value) {
    if (value != null) {
      setter.accept(parseInt(value));
    }
  }

  public static void setIfNotNullParseDouble(final DoubleConsumer setter, final String value) {
    if (value != null) {
      setter.accept(parseDouble(value));
    }
  }
}
