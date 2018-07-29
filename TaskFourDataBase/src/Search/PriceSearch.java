package Search;

import Garage.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriceSearch {

    List<Car> priceRange(List<Car> cars, Scanner scanner) {
        List<Car> chosenCars;
        String description = "Type \"lower\" or \"higher\" to choose cars by price" +
                "\n\"all\" to choose all \n\"quit\" to exit.";
        System.out.println(description);
        label:
        while (true) {
            String whatToDo = scanner.nextLine().toLowerCase();
            switch (whatToDo) {
                case "lower":
                    chosenCars = lowPrices(cars, scanner);
                    break label;
                case "higher":
                    chosenCars = highPrices(cars, scanner);
                    break label;
                case "all":
                    chosenCars = cars;
                    break label;
                case "quit":
                    System.out.println("Bye!");
                    System.exit(0);
                default:
                    System.out.println(description);
                    break;
            }
        }
        return chosenCars;
    }

    private List<Car> lowPrices(List<Car> cars, Scanner scanner) {
        System.out.println("Type maximum price:");
        List<Car> chosenCars = new ArrayList<>();
        String userInput = scanner.nextLine();
        if (userInput.matches("[0-9]+")) {
            for (Car car : cars) {
                if (car.getPrice() < Integer.valueOf(userInput))
                    chosenCars.add(car);
            }
        } else {
            System.out.println("Incorrect input: \"" + userInput + "\"");
            chosenCars = lowPrices(cars, scanner);
        }
        return chosenCars;
    }

    private List<Car> highPrices(List<Car> cars, Scanner scanner) {
        System.out.println("Type minimum price:");
        List<Car> chosenCars = new ArrayList<>();
        String userInput = scanner.nextLine();
        if (userInput.matches("[0-9]+")) {
            for (Car car : cars) {
                if (car.getPrice() > Integer.valueOf(userInput))
                    chosenCars.add(car);
            }
        } else {
            System.out.println("Incorrect input: \"" + userInput + "\"");
            chosenCars = highPrices(cars, scanner);
        }
        return chosenCars;
    }

}
