package Import.Txt;

import Garage.Brand;
import Garage.Car;
import Garage.CarBody;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CarImporterFromTxt implements DataGetterTxt {

    private int lineNumber = 0;
    private String fileName = "src/cars.txt";

    public CarImporterFromTxt(List<Car> cars) {
        reader(cars);
    }

    private void reader(List<Car> cars) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName)))) {
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
                    cars.add(new Car(brand, carBody, fuelConsumption, price));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Brand getBrand(String line) {
        String brandValue = line.substring(1, line.length());
        for (Brand brand : Brand.values()) {
            if (brandValue.equals(String.valueOf(brand))) {
                return brand;
            }
        }
        System.err.println("In " + fileName + " at line " + lineNumber
                + " car doesn't have valid brand: " + brandValue
                + ".\nCheck input file.");
        System.exit(1);
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
                + " car doesn't have valid car body: " + carBodyValue
                + ".\nCheck input file.");
        System.exit(1);
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
                    + " car doesn't have valid fuel consumption: " + line
                    + ".\nCheck input file.");
            System.exit(1);
        }
        return -1.0;
    }

    public int getPrice(String line) {
        try {
            return Integer.valueOf(line.substring(line.indexOf("$") + 1, line.indexOf("]")));
        } catch (NumberFormatException e) {
            System.err.println("In " + fileName + " at line " + lineNumber
                    + " car doesn't have valid price: " + line
                    + ".\nCheck input file.");
            System.exit(1);
        }
        return -1;
    }

}
