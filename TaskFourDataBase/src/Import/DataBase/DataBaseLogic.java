package Import.DataBase;


import Garage.Brand;
import Garage.Car;
import Garage.CarBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/* Table used to get cars:
CREATE TABLE 'garage'.'cars' (
'id' INT NOT NULL AUTO_INCREMENT,
'brand' ENUM('MERCEDES', 'BMW', 'AUDI') NOT NULL,
'car_body' ENUM('Sedan', 'Wagon', 'Van') NOT NULL,
'fuel_consumption' DOUBLE NOT NULL,
'price' INT NOT NULL,
PRIMARY KEY('id'));
*/

class DataBaseLogic {

    private PreparedStatement statement;
    private ResultSet resultSet;
    boolean isNoErrors = true;

    int getAmountOfAllLinesInTable(Connection connection) {
        int size = 0;
        try {
            statement = connection.prepareStatement("SELECT * from cars;");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                size++;
            }
        } catch (SQLException e) {
            System.err.println("Failed to count all cars: " + e.getMessage());
            isNoErrors = false;
        } finally {
            closeSetAndStatement();
        }
        return size;
    }

    void readInfo(int id, List<Car> tempCars, Connection connection) {
        try {
            statement = connection.prepareStatement("SELECT * from cars where id=?;");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next() && resultSet.getInt("ID") == id) {
                tempCars.add(new Car(getBrand(resultSet.getString("BRAND")),
                        getCarBody(resultSet.getString("CAR_BODY")),
                        getFuelConsumption(resultSet.getDouble("FUEL_CONSUMPTION")),
                        getPrice(resultSet.getInt("PRICE"))));
            }
        } catch (SQLException e) {
            System.err.println("Error during reading table at line " + id + ": " + e.getMessage());
            isNoErrors = false;
        } finally {
            closeSetAndStatement();
        }
    }

    private void closeSetAndStatement() {
        try {
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            System.err.println("Error during closing ResultSet " + e.getMessage());
            /*this doesn't affect data adding so no error tracking, just notification*/
        }
        try {
            if (statement != null) statement.close();
        } catch (SQLException e) {
            System.err.println("Error during closing PreparedStatement " + e.getMessage());
            /*this doesn't affect data adding so no error tracking, just notification*/
        }
    }

    private Brand getBrand(String data) {
        return Brand.valueOf(data);
    }

    private CarBody getCarBody(String data) {
        return CarBody.valueOf(data);
    }

    private double getFuelConsumption(double data) {
        return data;
    }

    private int getPrice(int data) {
        return data;
    }

}
