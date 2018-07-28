package Import;

import Actions.CarGenerator;
import Garage.Car;
import Import.DataBase.DataBaseImporter;
import Import.Txt.CarImporterFromTxt;

import java.util.List;
import java.util.Scanner;

public class CarInputType {

    public CarInputType(List<Car> cars, Scanner scanner, int amountOfCarsToGenerate) {
        whatToDo(cars, amountOfCarsToGenerate, scanner);
    }

    private void whatToDo(List<Car> cars, int amountOfCarsToGenerate, Scanner scanner) {
        System.out.println("In order to get cars type:\n" +
                "1. \"generate\" to generate cars\n" +
                "2. \"file\" to get cars from file\n" +
                "3. \"database\" to get cars from database");
        label:
        while (true) {
            String whatToDo = scanner.nextLine();
            switch (whatToDo.toLowerCase()) {
                case "generate":
                    new CarGenerator(cars, amountOfCarsToGenerate);
                    break label;
                case "file":
                    new CarImporterFromTxt(cars);
                    break label;
                case "database":
                    new DataBaseImporter(cars, scanner, amountOfCarsToGenerate);
                    break label;
                default:
                    System.out.println("Incorrect input: \"" + whatToDo + "\"");
                    System.out.println("Type \"generate\" or \"file\" or \"database\"");
                    break;
            }
        }
    }
}
