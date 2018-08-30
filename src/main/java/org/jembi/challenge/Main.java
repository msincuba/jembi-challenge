package org.jembi.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException, SQLException {
    URL url = new URL("https://www.mockaroo.com/04de5930/download?count=1000&key=e27814e0");
    Scanner input = new Scanner(new BufferedReader(new InputStreamReader(url.openStream())));

    Connection db = DriverManager.getConnection("jdbc:sqlite::memory:");
    Statement statement = db.createStatement();
    statement.executeUpdate(
        "create table if not exists patients (id primary key, identityNumber string, " +
            "firstName string, lastName string, phoneNumber string, address string, gender string, race string)");

    while (input.hasNextLine()) {
      String line = input.nextLine();
      line = line.replace("[", "");
      line = line.replace("]", "");
      line = line.replace("{", "");
      line = line.replace("}", "");
      line = line.replace("\"", "");

      String[] fields = line.split(",");

      for (int i = 0; i < fields.length; i++) {
        fields[i] = fields[i].substring(fields[i].indexOf(":") + 1);
      }

      String insert =
          "insert into patients(identityNumber, firstName, lastName, phoneNumber, address, gender, race) "
              +
              "values(\"" + fields[0] + "\", \"" + fields[1] + "\", \"" + fields[2] + "\", \"" +
              fields[3] + "\", \"" + fields[4] + "\", \"" + fields[5] + "\", \"" + fields[6]
              + "\")";

      ResultSet duplicates = statement.executeQuery("select * from patients " +
          "where identityNumber =" + fields[0] + " or " +
          "(firstName = \"" + fields[1] + "\" and lastName =\"" + fields[2] + "\")");

      if (duplicates.isBeforeFirst()) {
        while (duplicates.next()) {
          if (fields[0] == duplicates.getString("identityNumber")) {
            continue;
          } else if (fields[1] == duplicates.getString("firstName") && fields[2] == duplicates
              .getString("lastName")) {
            if (fields[5] == duplicates.getString("gender") && fields[6] == duplicates
                .getString("race")) {
              continue;
            }
          } else {
            statement.executeUpdate(insert);
          }
        }
      } else {
        statement.executeUpdate(insert);
      }
    }

    ResultSet count = statement.executeQuery("select count(*) as count from patients");
    System.out.println(count.getInt("count"));

    ResultSet rows = statement.executeQuery("select * from patients limit 10");
    while (rows.next()) {
      System.out.println(rows.getString("identityNumber") + ", " +
          rows.getString("firstName") + ", " +
          rows.getString("lastName") + ", " +
          rows.getString("phoneNumber") + ", " +
          rows.getString("address") + ", " +
          rows.getString("gender") + ", " +
          rows.getString("race"));
    }
  }
}
