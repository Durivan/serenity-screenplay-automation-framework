package com.ivanduri.abilities;

import java.sql.SQLException;
import net.serenitybdd.screenplay.Ability;

public class EstablishConnection implements Ability {

  private EstablishConnection() throws SQLException {
    new DatabaseConnection();
  }

  public static EstablishConnection toDataBase() throws SQLException {
    return new EstablishConnection();
  }
}
