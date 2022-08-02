package com.ivanduri.abilities;

import static net.serenitybdd.core.environment.ConfiguredEnvironment.getEnvironmentVariables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Ability;

public class DatabaseConnection implements Ability {

  private static Connection connection;

  public DatabaseConnection() throws SQLException {
    String password =
        EnvironmentSpecificConfiguration.from(getEnvironmentVariables()).getProperty("db.password");
    connection =
        DriverManager.getConnection(
            "url", "user", password);

    boolean valid = connection.isValid(50000);
    if (!valid) {
      //TODO exception
    }
  }

  public static Connection getDatabaseConnection() throws SQLException {
    if (connection == null) {
      new DatabaseConnection();
    }
    return connection;
  }
}
