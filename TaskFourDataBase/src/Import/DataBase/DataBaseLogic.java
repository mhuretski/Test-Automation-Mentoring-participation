package Import.DataBase;


import Garage.Brand;
import Garage.Car;
import Garage.CarBody;
import Import.CarInputType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/* Table used to get cars:
CREATE TABLE 'garage'.'cars' (
'id' INT NOT NULL AUTO_INCREMENT,
'brand' ENUM('MERCEDES', 'BMW', 'AUDI') NOT NULL,
'car_body' ENUM('Sedan', 'Wagon', 'Van') NOT NULL,
'fuel_consumption' DOUBLE NOT NULL,
'price' INT NOT NULL,
PRIMARY KEY('id'));
*/

public class DataBaseLogic implements DataGetterFromNumbers {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private boolean noErrorsGettingAmountOfRows = true;
    private boolean noErrorsReadingInfo = true;

    DataBaseLogic(Connection connection) {
        this.connection = connection;
    }

    public int getAmountOfAllLinesInTable(List<Car> cars, Scanner scanner, int amountOfCarsToGenerate) {
        int size = 0;
        try {
            statement = connection.prepareStatement("SELECT * from cars;");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                size++;
            }
        } catch (SQLException e) {
            System.err.println("Failed to count all cars: " + e.getMessage());
            System.err.println("Please try other options");
            noErrorsGettingAmountOfRows = false;
        } finally {
            closeSetAndStatement();
            if (!noErrorsGettingAmountOfRows)
                new CarInputType(cars, scanner, amountOfCarsToGenerate);
        }
        return size;
    }

    public Car readInfo(int id, List<Car> cars, Scanner scanner, int amountOfCarsToGenerate) {
        Car car = null;
        try {
            statement = connection.prepareStatement("SELECT * from cars where id=?;");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next() && resultSet.getInt("ID") == id) {
                car = new Car(getBrand(resultSet.getString("BRAND")),
                        getCarBody(resultSet.getString("CAR_BODY")),
                        getFuelConsumption(resultSet.getDouble("FUEL_CONSUMPTION")),
                        getPrice(resultSet.getInt("PRICE")));
            }
        } catch (SQLException e) {
            System.err.println("Error during reading table at line " + id + ": " + e.getMessage());
            System.err.println("Please try other options");
            noErrorsReadingInfo = false;
        } finally {
            closeSetAndStatement();
            if (!noErrorsReadingInfo) {
                new CarInputType(cars, scanner, amountOfCarsToGenerate);
            }
        }
        return car;
    }

    private void closeSetAndStatement() {
        try {
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            System.err.println("Error during closing ResultSet " + e.getMessage());
        }
        try {
            if (statement != null) statement.close();
        } catch (SQLException e) {
            System.err.println("Error during closing PreparedStatement " + e.getMessage());
        }
    }

    @Override
    public double getFuelConsumption(double data) {
        return data;
    }

    @Override
    public int getPrice(int data) {
        return data;
    }

    @Override
    public Brand getBrand(String data) {
        return Brand.valueOf(data);
    }

    @Override
    public CarBody getCarBody(String data) {
        return CarBody.valueOf(data);
    }

}
