package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  // mysql.istic.univ-rennes1.fr
  private static final String URL = "jdbc:mysql://localhost:3306/esirify";

  private static final String USER = "root";
  private static final String PASSWORD = "root";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}
