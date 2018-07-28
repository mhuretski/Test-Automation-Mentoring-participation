package Import.DataBase;


import Garage.Brand;
import Garage.Car;
import Garage.CarBody;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* Table used to get cars:
CREATE TABLE 'garage'.'cars' (
'id' INT NOT NULL AUTO_INCREMENT,
'brand' ENUM('MERCEDES', 'BMW', 'AUDI') NOT NULL,
'car_body' ENUM('Sedan', 'Wagon', 'Van') NOT NULL,
'fuel_consumption' DOUBLE NOT NULL,
'price' INT NOT NULL,
PRIMARY KEY('id'));
*/

public class DataBaseLogic implements DataGetterDB {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    DataBaseLogic(Connection connection) {
        this.connection = connection;
    }

    public int getAmountOfAllLinesInTable() {
        int size = 0;
        try {
            statement = connection.prepareStatement("SELECT * from cars;");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                size++;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return size;
    }

    public Car readInfo(int id) {
        try {
            statement = connection.prepareStatement("SELECT * from cars where id=?;");
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("ID") == id) {
                    return new Car(getBrand(resultSet.getString("BRAND")),
                            getCarBody(resultSet.getString("CAR_BODY")),
                            getFuelConsumption(resultSet.getDouble("FUEL_CONSUMPTION")),
                            getPrice(resultSet.getInt("PRICE")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
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
