package Import.DataBase;


import Garage.Brand;
import Garage.Car;
import Garage.CarBody;
import Import.CarInputType;
import Import.CarSetter;

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

public class DataBaseLogic implements CarSetter {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private boolean noErrorsGettingAmountOfRows = true;
    private boolean noErrorsReadingInfo = true;
    private Brand brand;
    private CarBody carBody;
    private double fuelConsumption;
    private int price;

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
            noErrorsGettingAmountOfRows = false; /*tracking error*/
        } finally {
            /*final block is executed anyway so first closing set and statement
             * than providing user other options to get data because if user
             * interactions will stay at catch block, set and statement
             * won't be closed until new CarInputType worked out*/
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
                setBrand(resultSet.getString("BRAND"));
                setCarBody(resultSet.getString("CAR_BODY"));
                setFuelConsumption(resultSet.getDouble("FUEL_CONSUMPTION"));
                setPrice(resultSet.getInt("PRICE"));

                car = new Car(getBrand(),getCarBody(),getFuelConsumption(),getPrice());
            }
        } catch (SQLException e) {
            System.err.println("Error during reading table at line " + id + ": " + e.getMessage());
            System.err.println("Please try other options");
            noErrorsReadingInfo = false; /*tracking error*/
        } finally {
            /*final block is executed anyway so first closing set and statement
             * than providing user other options to get data because if user
             * interactions will stay at catch block, set and statement
             * won't be closed until new CarInputType worked out*/
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

    private void setBrand(String data) {
        brand = Brand.valueOf(data);
    }

    private void setCarBody(String data) {
        carBody = CarBody.valueOf(data);
    }

    private void setFuelConsumption(double data) {
        fuelConsumption = data;
    }

    private void setPrice(int data) {
        price = data;
    }

    @Override
    public Brand getBrand() {
        return brand;
    }

    @Override
    public CarBody getCarBody() {
        return carBody;
    }

    @Override
    public double getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public int getPrice() {
        return price;
    }

}
