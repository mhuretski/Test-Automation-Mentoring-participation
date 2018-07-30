package Actions;

import Garage.Car;

import java.util.List;
import java.util.Scanner;

public class Show {

    public void whatToDo(List<Car> cars, Scanner scanner) {
        if (cars.size() != 0) chooseAction(cars, scanner);
        else {
            nothingToShow();
        }
    }

    private void chooseAction(List<Car> cars, Scanner scanner) {
        System.out.println("Type \"show\" or \"skip\"");
        label:
        while (true) {
            String whatToDo = scanner.nextLine();
            switch (whatToDo) {
                case "show":
                    show(cars);
                case "skip":
                    break label;
                default:
                    System.out.println("Incorrect input: \"" + whatToDo + "\"");
                    System.out.println("Type \"show\" or \"skip\"");
                    break;
            }
        }
    }

    private void nothingToShow() {
        System.out.println("There are no cars to show. Bye!");
        System.exit(0);
    }

    private void show(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car);
        }
        System.out.println();
    }

}
