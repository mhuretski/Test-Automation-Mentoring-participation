package Import.Json;

/*gson-2.8.5.jar is added to External Libraries*/

import Garage.Brand;
import Garage.Car;
import Garage.CarBody;
import Import.CarGetter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CarImporterFromJson implements CarGetter {

    private int carSequenceInJson = 1;
    private boolean isNoErrors = true;
    private String fileName = "src/cars.json";
    private List<Car> tempCars;

    public CarImporterFromJson() {
        parseJson();
    }

    private void parseJson() {
        try {
            JsonElement jsonElement = new JsonParser()
                    .parse(new FileReader(fileName));
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray cars = getAllCars(jsonObject);
            extractCars(cars);
        } catch (FileNotFoundException e) {
            System.out.println("Json file not found: " + e.getMessage());
            isNoErrors = false;
        } catch (NullPointerException e) {
            System.out.println("Json file doesn't contain specified data at car " + carSequenceInJson);
            isNoErrors = false;
        } catch (UnsupportedOperationException | IllegalStateException e) {
            System.err.println("Json contains unexpected data: " + e);
            isNoErrors = false;
        } catch (com.google.gson.JsonSyntaxException e) {
            System.err.println("Failed to parse Json: " + e);
            isNoErrors = false;
        }
    }

    private JsonArray getAllCars(JsonObject jsonObject) {
        try {
            return jsonObject.getAsJsonArray("cars");
        } catch (NullPointerException e) {
            System.out.println("Json file doesn't contain any cars.");
            isNoErrors = false;
        }
        return null;
    }

    private void extractCars(JsonArray cars) throws NullPointerException {
        tempCars = new ArrayList<>();
        if (cars != null)
            for (int i = 0; i < cars.size(); i++, carSequenceInJson++) {
                tempCars.add(new Car(setBrand(cars.get(i).getAsJsonObject().get("brand").getAsString()),
                        setCarBody(cars.get(i).getAsJsonObject().get("car_body").getAsString()),
                        setFuelConsumption(cars.get(i).getAsJsonObject().get("fuel_consumption").getAsString()),
                        setPrice(cars.get(i).getAsJsonObject().get("price").getAsString())));
            }
    }

    private Brand setBrand(String data) {
        for (Brand brand : Brand.values()) {
            if (data.equals(String.valueOf(brand))) {
                return brand;
            }
        }
        System.err.println("In " + fileName + " car " + carSequenceInJson
                + " doesn't have valid brand: " + data);
        isNoErrors = false;
        return null;
    }

    private CarBody setCarBody(String data) {
        for (CarBody carBody : CarBody.values()) {
            if (data.equals(String.valueOf(carBody))) {
                return carBody;
            }
        }
        System.err.println("In " + fileName + " car " + carSequenceInJson
                + " doesn't have valid car body: " + data);
        isNoErrors = false;
        return null;
    }

    private double setFuelConsumption(String data) {
        if (data.matches("[0-9]+")
                || data.matches("[0-9]+" + "." + "[0-9]+")) {
            return Double.valueOf(data);
        } else
            System.err.println("In " + fileName + " car " + carSequenceInJson
                    + " doesn't have valid fuel consumption: " + data);
        isNoErrors = false;
        return -1.0;
    }

    private int setPrice(String data) {
        try {
            int price = Integer.valueOf(data);
            if (price >= 0) return price;
            else throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.err.println("In " + fileName + " car " + carSequenceInJson
                    + " doesn't have valid price: " + data);
        }
        isNoErrors = false;
        return -1;
    }

    public List<Car> getCars() {
        return tempCars;
    }

    public boolean isNoErrors() {
        return isNoErrors;
    }

}
