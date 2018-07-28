package Import.DataBase;

import Garage.Car;
import Import.CarInputType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/*mysql-connector-java-8.0.11.jar is added to External Libraries*/

public class DataBaseImporter {

    private Connection connection;

    public DataBaseImporter(List<Car> cars, Scanner scanner, int amountOfCarsToGenerate) {
        connect(cars, scanner, amountOfCarsToGenerate);
        getCars(new DataBaseLogic(connection), cars);
        disconnect();
    }

    private void connect(List<Car> cars, Scanner scanner, int amountOfCarsToGenerate) {
        String url = "jdbc:mysql://localhost:3306/garage?&useSSL=false";
        String username = "root";
        String password = "admin123";
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("No connection to database, please try another options.");
            new CarInputType(cars, scanner, amountOfCarsToGenerate);
        }
    }

    private void getCars(DataBaseLogic reader, List<Car> cars) {
        if (connection != null) {
            int lines = reader.getAmountOfAllLinesInTable();
            for (int i = 1; i <= lines; i++) {
                cars.add(reader.readInfo(i));
            }
        }
    }

    private void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
