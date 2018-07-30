package Import.Txt;

import Garage.Brand;
import Garage.Car;
import Garage.CarBody;
import Import.CarGetter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CarImporterFromTxt implements CarGetter {

    private int lineNumber = 0;
    private String fileName = "src/cars.txt";
    private boolean isNoErrors = true;
    private List<Car> tempCars;

    public CarImporterFromTxt() {
        reader();
    }

    private void reader() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName)))) {
            tempCars = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] values = line.split(", ");
                tempCars.add(new Car(getBrand(values[0]),
                        getCarBody(values[1]),
                        getFuelConsumption(values[2]),
                        getPrice(values[3])));
            }
        } catch (IOException e) {
            System.err.println("Txt file not found: " + e);
            isNoErrors = false;
        }
    }

    private Brand getBrand(String line) {
        String brandValue = line.substring(1, line.length());
        for (Brand brand : Brand.values()) {
            if (brandValue.equals(String.valueOf(brand))) {
                return brand;
            }
        }
        System.err.println("In " + fileName + " at line " + lineNumber
                + " car doesn't have valid brand: " + brandValue);
        isNoErrors = false;
        return null;
    }

    private CarBody getCarBody(String line) {
        String carBodyValue = line.substring(0, line.length());
        for (CarBody carBody : CarBody.values()) {
            if (carBodyValue.equals(String.valueOf(carBody))) {
                return carBody;
            }
        }
        System.err.println("In " + fileName + " at line " + lineNumber
                + " car doesn't have valid car body: " + carBodyValue);
        isNoErrors = false;
        return null;
    }

    private double getFuelConsumption(String line) {
        try {
            String fuelCons = line.substring(0, line.indexOf(" "));
            if (fuelCons.matches("[0-9]+")
                    || fuelCons.matches("[0-9]+" + "." + "[0-9]+")) {
                return Double.valueOf(fuelCons);
            } else throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.err.println("In " + fileName + " at line " + lineNumber
                    + " car doesn't have valid fuel consumption: " + line);
            isNoErrors = false;
        }
        return -1.0;
    }

    private int getPrice(String line) {
        try {
            int price = Integer.valueOf(line.substring(line.indexOf("$") + 1, line.indexOf("]")));
            if (price >= 0) return price;
            else throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.err.println("In " + fileName + " at line " + lineNumber
                    + " car doesn't have valid price: " + line);
            isNoErrors = false;
        }
        return -1;
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
