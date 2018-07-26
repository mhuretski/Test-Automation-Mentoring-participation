package Search;

import Exceptions.ExceptionsHandler;
import Garage.Brand;
import Garage.Car;
import Garage.CarBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TypeSearch {

    public List<Car> chooseCarBody(ExceptionsHandler ex, List<Car> cars, Scanner scanner) {
        List<Car> chosenCars = new ArrayList<>();
        System.out.println("Type car body to choose cars: " + Arrays.toString(CarBody.values()) +
                "\n\"all\" to choose all bodies\n\"quit\" to exit.");
        String userInput = scanner.nextLine();
        switch (userInput.toUpperCase()) {
            case "QUIT":
                System.out.println("Bye!");
                System.exit(0);
            case "ALL":
                chosenCars = cars;
                break;
            default:
                try {
                    for (Car car : cars) {
                        if (car.getCarBody().equals(carBodyToEnum(userInput)))
                            chosenCars.add(car);
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    chosenCars = ex.catchIllArgExUserInput(ex, cars, scanner, userInput,
                            false);
                }
        }
        return chosenCars;
    }

    public List<Car> chooseBrand(ExceptionsHandler ex, List<Car> cars, Scanner scanner) {
        List<Car> chosenCars = new ArrayList<>();
        System.out.println("Type car brand to choose cars: " + Arrays.toString(Brand.values())
                + "\n\"all\" to choose all brands\n\"quit\" to exit.");
        String userInput = scanner.nextLine().toUpperCase();
        switch (userInput) {
            case "QUIT":
                System.out.println("Bye!");
                System.exit(0);
            case "ALL":
                chosenCars = cars;
                break;
            default:
                try {
                    for (Car car : cars) {
                        if (car.getBrand().equals(brandToEnum(userInput)))
                            chosenCars.add(car);
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    chosenCars = ex.catchIllArgExUserInput(ex, cars, scanner, userInput,
                            true);
                }
        }
        return chosenCars;
    }

    private Brand brandToEnum(String userInput) {
        return Brand.valueOf(userInput);
    }

    private CarBody carBodyToEnum(String userInput) {
        return CarBody.valueOf(userInput);
    }

}
