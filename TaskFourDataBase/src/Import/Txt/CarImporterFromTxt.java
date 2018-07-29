package Import.Txt;

import Garage.Brand;
import Garage.Car;
import Garage.CarBody;
import Import.CarInputType;
import Import.DataGetterFromStrings;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarImporterFromTxt implements DataGetterFromStrings {

    private int lineNumber = 0;
    private String fileName = "src/cars.txt";
    private boolean noErrors = true;

    public CarImporterFromTxt(List<Car> cars, Scanner scanner, int amountOfCarsToGenerate) {
        reader(cars, scanner, amountOfCarsToGenerate);
    }

    private void reader(List<Car> cars, Scanner scanner, int amountOfCarsToGenerate) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName)))) {
            List<Car> tempCars = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] values = line.split(", ");
                Brand brand = getBrand(values[0]);
                CarBody carBody = getCarBody(values[1]);
                double fuelConsumption = getFuelConsumption(values[2]);
                int price = getPrice(values[3]);
                if (brand != null &&
                        carBody != null &&
                        fuelConsumption != -1.0 &&
                        price != -1) {
                    tempCars.add(new Car(brand, carBody, fuelConsumption, price));
                }
            }
            /*getter return false if something goes wrong
            * exception can't be thrown from getter because of common interface
            * also all errors are tracked before going to errorHandling,
            * if there are no errors data is added to main list*/
            if (noErrors) cars.addAll(tempCars);
            else errorHandling(cars, scanner, amountOfCarsToGenerate);
        } catch (IOException e) {
            System.err.println("Txt file not found: " + e);
            errorHandling(cars, scanner, amountOfCarsToGenerate);
        }
    }

    private void errorHandling(List<Car> cars, Scanner scanner, int amountOfCarsToGenerate) {
        System.err.println("Please try other options.");
        new CarInputType(cars, scanner, amountOfCarsToGenerate);
    }

    public Brand getBrand(String line) {
        String brandValue = line.substring(1, line.length());
        for (Brand brand : Brand.values()) {
            if (brandValue.equals(String.valueOf(brand))) {
                return brand;
            }
        }
        System.err.println("In " + fileName + " at line " + lineNumber
                + " car doesn't have valid brand: " + brandValue);
        noErrors = false;
        return null;
    }

    public CarBody getCarBody(String line) {
        String carBodyValue = line.substring(0, line.length());
        for (CarBody carBody : CarBody.values()) {
            if (carBodyValue.equals(String.valueOf(carBody))) {
                return carBody;
            }
        }
        System.err.println("In " + fileName + " at line " + lineNumber
                + " car doesn't have valid car body: " + carBodyValue);
        noErrors = false;
        return null;
    }

    public double getFuelConsumption(String line) {
        try {
            String fuelCons = line.substring(0, line.indexOf(" "));
            if (fuelCons.matches("[0-9]+")
                    || fuelCons.matches("[0-9]+" + "." + "[0-9]+"))
                return Double.valueOf(fuelCons);
            else throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.err.println("In " + fileName + " at line " + lineNumber
                    + " car doesn't have valid fuel consumption: " + line);
        }
        noErrors = false;
        return -1.0;
    }

    public int getPrice(String line) {
        try {
            return Integer.valueOf(line.substring(line.indexOf("$") + 1, line.indexOf("]")));
        } catch (NumberFormatException e) {
            System.err.println("In " + fileName + " at line " + lineNumber
                    + " car doesn't have valid price: " + line);
        }
        noErrors = false;
        return -1;
    }

}
