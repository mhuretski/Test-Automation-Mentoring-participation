package Import.DataBase;

import Garage.Car;
import Import.CarInputType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*mysql-connector-java-8.0.11.jar is added to External Libraries*/

public class DataBaseImporter {

    private Connection connection;
    private boolean noErrors = true;

    public DataBaseImporter(List<Car> cars, Scanner scanner, int amountOfCarsToGenerate) {
        connect();
        if (noErrors)
            getCars(new DataBaseLogic(connection), cars, scanner, amountOfCarsToGenerate);
        disconnect();
        if (!noErrors) new CarInputType(cars, scanner, amountOfCarsToGenerate);
    }

    private void connect() {
        String url = "jdbc:mysql://localhost:3306/garage?&useSSL=false";
        String username = "root";
        String password = "admin123";
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("No connection to database: " + e.getMessage());
            System.err.println("Please try other options");
            noErrors = false;
        }
    }

    private void getCars(DataBaseLogic reader, List<Car> cars, Scanner scanner, int amountOfCarsToGenerate) {
        try {
            List<Car> tempCars = new ArrayList<>();
            if (connection != null) {
                int lines = reader.getAmountOfAllLinesInTable(cars, scanner, amountOfCarsToGenerate);
                for (int i = 1; i <= lines; i++) {
                    tempCars.add(reader.readInfo(i, cars, scanner, amountOfCarsToGenerate));
                }
            }
            for (Car car : tempCars) {
                if (car == null) throw new NullPointerException();
            }
            cars.addAll(tempCars);
        } catch (NullPointerException e) {
            System.err.println("Cars in database contain null values.");
            System.err.println("Please try other options");
            noErrors = false;
        }
    }

    private void disconnect() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.err.println("Error during closing database connection " + e.getMessage());
        }
    }

}
