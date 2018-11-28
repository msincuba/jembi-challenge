package org.jembi.challenge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

  private static final Logger LOGGER = LogManager.getLogger(Main.class);

  public static void main(String[] args) {
    URL url;
    try {
      url = new URL("https://www.mockaroo.com/04de5930/download?count=100&key=e27814e0");
    } catch (MalformedURLException e) {
      LOGGER.error("Failed to get data fromm URL", e);
      return; // No need to proceed when we can't get data
    }

    try (Scanner input = new Scanner(new BufferedReader(new InputStreamReader(url.openStream())))) {
      new Main().savePatients(input);
    } catch (IOException e) {
      LOGGER.error("Failed to read data", e);
    }

  }

  public int savePatients(Scanner input) {

    PreparedStatement statement;
    final String tableStatement = "create table if not exists patients (" +
            "id primary key, identityNumber string, firstName string, lastName string, " +
            "phoneNumber string, address string, gender string, race string, " +
            "constraint uk_identityDocument unique (identityNumber))";
    try (Connection db = DriverManager.getConnection("jdbc:sqlite::memory:")) {
      statement = db.prepareStatement(tableStatement);
      statement.executeUpdate();

      Set<Patient> patients = new HashSet<>(100); //Initialise to count (URL above) to improve performance
      while (input.hasNextLine()) {

        String line = input.nextLine();
        line = line.replace("[", "")
                .replace("]", "")
                .replace("{", "")
                .replace("}", "")
                .replace("\"", "");

        String[] fields = line.split(",");

        for (int i = 0; i < fields.length; i++) {
          fields[i] = fields[i].substring(fields[i].indexOf(":") + 1);
        }

        Patient newPatient = new Patient()
                .identityNumber(fields[0])
                .firstName(fields[1])
                .lastName(fields[2])
                .phoneNumber(fields[3])
                .address(fields[4])
                .gender(fields[5])
                .race(fields[6]);
        patients.add(newPatient);

      }

      String insert =
              "insert into patients(identityNumber, firstName, lastName, phoneNumber, address, gender, race) "
                      + "values(?, ?, ?, ?, ?, ?, ?)";
      for (Patient patient : patients) {
        statement = db.prepareStatement(insert);
        statement.setString(1, patient.getIdentityNumber());
        statement.setString(2, patient.getFirstName());
        statement.setString(3, patient.getLastName());
        statement.setString(4, patient.getPhoneNumber());
        statement.setString(5, patient.getAddress());
        statement.setString(6, patient.getGender());
        statement.setString(7, patient.getRace());
        try {
          statement.executeUpdate();
        } catch (SQLException e) { // Catch it for each row so that other rows can continue if the current one fails
          LOGGER.error(String.format("Record [%s] failed due to [%s]", patient.toString(), e.getLocalizedMessage()), e);
        }
      }

      displayResults(db);

      return patients.size();
    } catch (SQLException e) {
      LOGGER.error("Failed to to execute query", e);
    }

    return 0;

  }

  private void displayResults(Connection connection) {
    Statement statement = null;
    ResultSet count = null;
    ResultSet rows = null;
    try {
      statement = connection.createStatement();
      count = statement.executeQuery("select count(*) as count from patients");
      LOGGER.info(count.getInt("count"));

      rows = statement.executeQuery("select * from patients limit 10");
      while (rows.next()) {
        LOGGER.info(rows.getString("identityNumber") + ", " +
                rows.getString("firstName") + ", " +
                rows.getString("lastName") + ", " +
                rows.getString("phoneNumber") + ", " +
                rows.getString("address") + ", " +
                rows.getString("gender") + ", " +
                rows.getString("race"));
      }
    } catch (SQLException ex) {
      LOGGER.error("Failed to read data from database", ex);
    } finally {
      close(count);
      close(rows);
      close(statement);
    }
  }

  private void close(ResultSet resultSet) {
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        LOGGER.error("Failed to close result set", e);
      }
    }
  }

  private void close(Statement statement) {
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        LOGGER.error("Failed to close statement", e);
      }
    }
  }
}
