package Actions;

import Garage.Car;

import java.util.List;
import java.util.Scanner;

public class Show {
    public Show(List<Car> cars, Scanner scanner) {
        whatToDo(cars, scanner);
    }

    public Show() {
    }

    public void whatToDo(List<Car> cars, Scanner scanner) {
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

    private void show(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car);
        }
        System.out.println();
    }
}
