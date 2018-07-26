package Search;

import Exceptions.ExceptionsHandler;
import Garage.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsumptionSearch {

    List<Car> fuelConsumptionRange(ExceptionsHandler ex, List<Car> cars, Scanner scanner) {
        List<Car> chosenCars;
        String description = "Type \"lower\" or \"higher\" to choose cars by fuel consumption" +
                "\n\"all\" to choose all \n\"quit\" to exit.";
        System.out.println(description);
        label:
        while (true) {
            String whatToDo = scanner.nextLine().toLowerCase();
            switch (whatToDo) {
                case "lower":
                    chosenCars = lowConsumption(ex, cars, scanner);
                    break label;
                case "higher":
                    chosenCars = highConsumption(ex, cars, scanner);
                    break label;
                case "all":
                    chosenCars = cars;
                    break label;
                case "quit":
                    System.out.println("Bye!");
                    System.exit(0);
                default:
                    System.out.println("Incorrect input: \"" + whatToDo + "\"");
                    System.out.println(description);
                    break;
            }
        }
        return chosenCars;
    }

    public List<Car> lowConsumption(ExceptionsHandler ex, List<Car> cars, Scanner scanner) {
        System.out.println("Type maximum fuel consumption in litres:");
        List<Car> chosenCars = new ArrayList<>();
        String userInput = scanner.nextLine();
        try {
            for (Car car : cars) {
                if (car.getFuelConsumption() < Double.valueOf(userInput))
                    chosenCars.add(car);
            }
        } catch (NumberFormatException e) {
            chosenCars = ex.catchNumFormatExDuringUserInput(ex, cars, scanner, userInput,
                    false, true);
        }
        return chosenCars;
    }

    public List<Car> highConsumption(ExceptionsHandler ex, List<Car> cars, Scanner scanner) {
        System.out.println("Type maximum fuel consumption in litres:");
        List<Car> chosenCars = new ArrayList<>();
        String userInput = scanner.nextLine();
        try {
            for (Car car : cars) {
                if (car.getFuelConsumption() > Double.valueOf(userInput))
                    chosenCars.add(car);
            }
        } catch (NumberFormatException e) {
            chosenCars = ex.catchNumFormatExDuringUserInput(ex, cars, scanner, userInput,
                    false, false);
        }
        return chosenCars;
    }
}
