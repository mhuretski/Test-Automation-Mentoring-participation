package Search;

import Garage.Brand;
import Garage.Car;
import Garage.CarBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TypeSearch {

    List<Car> chooseCarBody(List<Car> cars, Scanner scanner) {
        List<Car> chosenCars = new ArrayList<>();
        System.out.println("Type car body to choose cars: " + Arrays.toString(CarBody.values()) +
                "\n\"all\" to choose all bodies\n\"quit\" to exit.");
        String userInput = scanner.nextLine();
        if (checkCarBody(userInput)) {
            for (Car car : cars) {
                if (car.getCarBody().equals(carBodyToEnum(userInput)))
                    chosenCars.add(car);
            }
        } else switch (userInput.toUpperCase()) {
            case "QUIT":
                System.out.println("Bye!");
                System.exit(0);
            case "ALL":
                chosenCars = cars;
                break;
            default:
                System.out.println("Incorrect input: \"" + userInput + "\"");
                chosenCars = chooseCarBody(cars, scanner);
                break;
        }
        return chosenCars;
    }

    List<Car> chooseBrand(List<Car> cars, Scanner scanner) {
        List<Car> chosenCars = new ArrayList<>();
        System.out.println("Type car brand to choose cars: " + Arrays.toString(Brand.values())
                + "\n\"all\" to choose all brands\n\"quit\" to exit.");
        String userInput = scanner.nextLine().toUpperCase();
        if (checkBrand(userInput)) {
            for (Car car : cars) {
                if (car.getBrand().equals(brandToEnum(userInput)))
                    chosenCars.add(car);
            }
        } else switch (userInput) {
            case "QUIT":
                System.out.println("Bye!");
                System.exit(0);
            case "ALL":
                chosenCars = cars;
                break;
            default:
                System.out.println("Incorrect input: \"" + userInput + "\"");
                chosenCars = chooseBrand(cars, scanner);
                break;
        }
        return chosenCars;
    }

    private boolean checkBrand(String userInput) {
        for (Brand brand : Brand.values()) {
            if (userInput.equalsIgnoreCase(brand.name()))
                return true;
        }
        return false;
    }

    private boolean checkCarBody(String userInput) {
        for (CarBody carBody : CarBody.values()) {
            if (userInput.equals(carBody.name()))
                return true;
        }
        return false;
    }

    private Brand brandToEnum(String userInput) {
        return Brand.valueOf(userInput);
    }

    private CarBody carBodyToEnum(String userInput) {
        return CarBody.valueOf(userInput);
    }

}
