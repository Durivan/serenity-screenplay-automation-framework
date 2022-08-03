package com.ivanduri.utils.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestData {

  public <T> List<T> getObjects(Class<T> tClass, @NonNull String fileName) {
    List<T> tList = new ArrayList<>();
    try {
      tList =
          new ObjectMapper()
              .readValue(
                  this.readFile(new FileInputStream(fileName)),
                  new ObjectMapper().getTypeFactory().constructCollectionType(List.class, tClass));
    } catch (IOException e) {
      Logger.error(TestData.class.getName(), "Failed load JSON File - ", e.toString());
    }
    return tList;
  }

  private String readFile(@NonNull InputStream inputStream) throws IOException {
    StringBuilder builder = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = reader.readLine()) != null) {
        builder.append(line).append("\n");
      }
    }
    return builder.toString();
  }
}
