package Import.Txt;

import Garage.Brand;
import Garage.Car;
import Garage.CarBody;
import Import.CarInputType;
import Import.CarSetter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarImporterFromTxt implements CarSetter {

    private int lineNumber = 0;
    private String fileName = "src/cars.txt";
    private boolean noErrors = true;
    private Brand brand;
    private CarBody carBody;
    private double fuelConsumption;
    private int price;

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
                setBrand(values[0]);
                setCarBody(values[1]);
                setFuelConsumption(values[2]);
                setPrice(values[3]);

                if (brand != null && carBody != null &&
                        fuelConsumption != -1.0 && price != -1) {
                    tempCars.add(new Car(getBrand(), getCarBody(),
                            getFuelConsumption(), getPrice()));
                }
            }
            /*setter return false if something goes wrong
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

    private void setBrand(String line) {
        boolean stop = false;
        String brandValue = line.substring(1, line.length());
        for (Brand brand : Brand.values()) {
            if (brandValue.equals(String.valueOf(brand))) {
                this.brand = brand;
                stop = true;
                break;
            }
        }
        if (!stop) {
            System.err.println("In " + fileName + " at line " + lineNumber
                    + " car doesn't have valid brand: " + brandValue);
            noErrors = false;
        }
    }

    private void setCarBody(String line) {
        boolean stop = false;
        String carBodyValue = line.substring(0, line.length());
        for (CarBody carBody : CarBody.values()) {
            if (carBodyValue.equals(String.valueOf(carBody))) {
                this.carBody = carBody;
                stop = true;
                break;
            }
        }
        if (!stop) {
            System.err.println("In " + fileName + " at line " + lineNumber
                    + " car doesn't have valid car body: " + carBodyValue);
            noErrors = false;
        }
    }

    private void setFuelConsumption(String line) {
        boolean stop = false;
        try {
            String fuelCons = line.substring(0, line.indexOf(" "));
            if (fuelCons.matches("[0-9]+")
                    || fuelCons.matches("[0-9]+" + "." + "[0-9]+")) {
                fuelConsumption = Double.valueOf(fuelCons);
                stop = true;
            } else throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.err.println("In " + fileName + " at line " + lineNumber
                    + " car doesn't have valid fuel consumption: " + line);
        }
        if (!stop) {
            noErrors = false;
        }
    }

    private void setPrice(String line) {
        boolean stop = false;
        try {
            int price = Integer.valueOf(line.substring(line.indexOf("$") + 1, line.indexOf("]")));
            if (price >= 0) {
                this.price = price;
                stop = true;
            } else throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.err.println("In " + fileName + " at line " + lineNumber
                    + " car doesn't have valid price: " + line);
        }
        if (!stop) {
            noErrors = false;
        }
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
