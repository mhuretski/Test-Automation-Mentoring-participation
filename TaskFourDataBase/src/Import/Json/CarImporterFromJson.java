package Import.Json;

/*gson-2.8.5.jar is added to External Libraries*/

import Garage.Brand;
import Garage.Car;
import Garage.CarBody;
import Import.CarInputType;
import Import.CarSetter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarImporterFromJson implements CarSetter {

    public CarImporterFromJson(List<Car> cars, Scanner scanner, int amountOfCarsToGenerate) {
        parseJson();
        if (noErrors) cars.addAll(tempCars);
        else new CarInputType(cars, scanner, amountOfCarsToGenerate);
    }

    private int carSequenceInJson = 1;
    private boolean noErrors = true;
    private String fileName = "src/cars.json";
    private List<Car> tempCars;
    private Brand brand;
    private CarBody carBody;
    private double fuelConsumption;
    private int price;

    private void parseJson() {
        try {
            JsonElement jsonElement = new JsonParser()
                    .parse(new FileReader(fileName));
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray cars = getAllCars(jsonObject);
            extractCars(cars);
        } catch (FileNotFoundException e) {
            System.out.println("Json file not found: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Json file doesn't contain specified data at car " + carSequenceInJson);
        } catch (UnsupportedOperationException | IllegalStateException e) {
            System.err.println("Json contains unexpected data: " + e);
        } catch (com.google.gson.JsonSyntaxException e) {
            System.err.println("Failed to parse Json: " + e);
        }
    }

    private JsonArray getAllCars(JsonObject jsonObject) {
        try {
            return jsonObject.getAsJsonArray("cars");
        } catch (NullPointerException e) {
            System.out.println("Json file doesn't contain any cars.");
            noErrors = false;
        }
        return null;
    }

    private void extractCars(JsonArray cars) throws NullPointerException {
        tempCars = new ArrayList<>();
        if (cars != null)
            for (int i = 0; i < cars.size(); i++, carSequenceInJson++) {
                setBrand(cars.get(i).getAsJsonObject().get("brand").getAsString());
                setCarBody(cars.get(i).getAsJsonObject().get("car_body").getAsString());
                setFuelConsumption(cars.get(i).getAsJsonObject().get("fuel_consumption").getAsString());
                setPrice(cars.get(i).getAsJsonObject().get("price").getAsString());

                tempCars.add(new Car(getBrand(),
                        getCarBody(),
                        getFuelConsumption(),
                        getPrice()));
            }
    }

    private void setBrand(String data) {
        boolean stop = false;
        for (Brand brand : Brand.values()) {
            if (data.equals(String.valueOf(brand))) {
                this.brand = brand;
                stop = true;
                break;
            }
        }
        if (!stop) {
            System.err.println("In " + fileName + " car " + carSequenceInJson
                    + " doesn't have valid brand: " + data);
            noErrors = false;
        }
    }

    private void setCarBody(String data) {
        boolean stop = false;
        for (CarBody carBody : CarBody.values()) {
            if (data.equals(String.valueOf(carBody))) {
                this.carBody = carBody;
                stop = true;
                break;
            }
        }
        if (!stop) {
            System.err.println("In " + fileName + " car " + carSequenceInJson
                    + " doesn't have valid car body: " + data);
            noErrors = false;
        }
    }

    private void setFuelConsumption(String data) {
        boolean stop = false;
        if (data.matches("[0-9]+")
                || data.matches("[0-9]+" + "." + "[0-9]+")) {
            fuelConsumption = Double.valueOf(data);
            stop = true;
        } else
            System.err.println("In " + fileName + " car " + carSequenceInJson
                    + " doesn't have valid fuel consumption: " + data);
        if (!stop) {
            noErrors = false;
        }
    }

    private void setPrice(String data) {
        boolean stop = false;
        try {
            int price = Integer.valueOf(data);
            if (price >= 0) {
                this.price = price;
                stop = true;
            } else throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.err.println("In " + fileName + " car " + carSequenceInJson
                    + " doesn't have valid price: " + data);
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
