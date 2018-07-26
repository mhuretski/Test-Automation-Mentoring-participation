package Search;

import Exceptions.ExceptionsHandler;
import Garage.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriceSearch {

    List<Car> priceRange(ExceptionsHandler ex, List<Car> cars, Scanner scanner) {
        List<Car> chosenCars;
        String description = "Type \"lower\" or \"higher\" to choose cars by price" +
                "\n\"all\" to choose all \n\"quit\" to exit.";
        System.out.println(description);
        label:
        while (true) {
            String whatToDo = scanner.nextLine().toLowerCase();
            switch (whatToDo) {
                case "lower":
                    chosenCars = lowPrices(ex, cars, scanner);
                    break label;
                case "higher":
                    chosenCars = highPrices(ex, cars, scanner);
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

    public List<Car> lowPrices(ExceptionsHandler ex, List<Car> cars, Scanner scanner) {
        System.out.println("Type maximum price:");
        List<Car> chosenCars = new ArrayList<>();
        String userInput = scanner.nextLine();
        try {
            for (Car car : cars) {
                if (car.getPrice() < Integer.valueOf(userInput))
                    chosenCars.add(car);
            }
        } catch (NumberFormatException e) {
            chosenCars = ex.catchNumFormatExDuringUserInput(ex, cars, scanner, userInput,
                    true, true);
        }
        return chosenCars;
    }

    public List<Car> highPrices(ExceptionsHandler ex, List<Car> cars, Scanner scanner) {
        System.out.println("Type maximum price:");
        List<Car> chosenCars = new ArrayList<>();
        String userInput = scanner.nextLine();
        try {
            for (Car car : cars) {
                if (car.getPrice() > Integer.valueOf(userInput))
                    chosenCars.add(car);
            }
        } catch (NumberFormatException e) {
            chosenCars = ex.catchNumFormatExDuringUserInput(ex, cars, scanner, userInput,
                    true, false);
        }
        return chosenCars;
    }
}
