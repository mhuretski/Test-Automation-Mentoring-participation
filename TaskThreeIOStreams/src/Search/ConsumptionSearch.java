package Search;

import Garage.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsumptionSearch {

    List<Car> fuelConsumptionRange(List<Car> cars, Scanner scanner) {
        List<Car> chosenCars;
        String description = "Type \"lower\" or \"higher\" to choose cars by fuel consumption" +
                "\n\"all\" to choose all \n\"quit\" to exit.";
        System.out.println(description);
        label:
        while (true) {
            String whatToDo = scanner.nextLine().toLowerCase();
            switch (whatToDo) {
                case "lower":
                    chosenCars = lowConsumption(cars, scanner);
                    break label;
                case "higher":
                    chosenCars = highConsumption(cars, scanner);
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

    private List<Car> lowConsumption(List<Car> cars, Scanner scanner) {
        System.out.println("Type maximum fuel consumption in litres:");
        List<Car> chosenCars = new ArrayList<>();
        String userInput = scanner.nextLine();
        if (!userInput.contains(",")
                && (userInput.matches("[0-9]+")
                || userInput.matches("[0-9]+" + "." + "[0-9]+"))) {
            for (Car car : cars) {
                if (car.getFuelConsumption() < Double.valueOf(userInput))
                    chosenCars.add(car);
            }
        } else {
            System.out.println("Incorrect input: " + userInput);
            chosenCars = lowConsumption(cars, scanner);
        }
        return chosenCars;
    }

    private List<Car> highConsumption(List<Car> cars, Scanner scanner) {
        System.out.println("Type minimum fuel consumption in litres:");
        List<Car> chosenCars = new ArrayList<>();
        String userInput = scanner.nextLine();
        if (!userInput.contains(",")
                && (userInput.matches("[0-9]+")
                || userInput.matches("[0-9]+" + "." + "[0-9]+"))) {
            for (Car car : cars) {
                if (car.getFuelConsumption() > Double.valueOf(userInput))
                    chosenCars.add(car);
            }
        } else {
            System.out.println("Incorrect input: \"" + userInput + "\"");
            chosenCars = highConsumption(cars, scanner);
        }
        return chosenCars;
    }
}
