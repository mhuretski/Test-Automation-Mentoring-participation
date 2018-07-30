package Import.DataBase;

import Garage.Car;
import Import.CarGetter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*mysql-connector-java-8.0.11.jar is added to External Libraries*/

public class DataBaseImporter extends DataBaseLogic implements CarGetter {

    private Connection connection;
    private List<Car> tempCars;

    public DataBaseImporter() {
        connect();
        if (isNoErrors) getCarsFromDB();
        disconnect();
    }

    private void connect() {
        String url = "jdbc:mysql://localhost:3306/garage?&useSSL=false";
        String username = "root";
        String password = "admin123";
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("No connection to database: " + e.getMessage());
            isNoErrors = false;
        }
    }

    private void getCarsFromDB() {
        try {
            tempCars = new ArrayList<>();
            if (connection != null) {
                int lines = getAmountOfAllLinesInTable(connection);
                for (int i = 1; i <= lines; i++) {
                    readInfo(i, tempCars, connection);
                }
            }
            for (Car car : tempCars) {
                if (car == null) throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.err.println("Cars in database contain null values.");
            isNoErrors = false; /*if exception occurs track it to choose other options*/
        }
    }

    private void disconnect() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.err.println("Error during closing database connection " + e.getMessage());
            /*this doesn't affect data so no error tracking, just notification*/
        }
    }

    @Override
    public List<Car> getCars() {
        return tempCars;
    }

    @Override
    public boolean isNoErrors() {
        return isNoErrors;
    }

}
