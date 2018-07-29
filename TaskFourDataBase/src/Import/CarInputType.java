package Import;

import Actions.CarGenerator;
import Garage.Car;
import Import.DataBase.DataBaseImporter;
import Import.Json.CarImporterFromJson;
import Import.Txt.CarImporterFromTxt;
import Import.Xml.CarImporterFromXml;

import java.util.List;
import java.util.Scanner;

public class CarInputType {

    public CarInputType(List<Car> cars, Scanner scanner, int amountOfCarsToGenerate) {
        whatToDo(cars, amountOfCarsToGenerate, scanner);
    }

    private void whatToDo(List<Car> cars, int amountOfCarsToGenerate, Scanner scanner) {
        System.out.println("In order to get cars type:\n" +
                "1. \"generate\" to generate cars\n" +
                "2. \"txt\" to get cars from txt file\n" +
                "3. \"xml\" to get cars from xml file\n" +
                "3. \"json\" to get cars from json file\n" +
                "4. \"database\" to get cars from database");
        label:
        while (true) {
            String whatToDo = scanner.nextLine();
            switch (whatToDo.toLowerCase()) {
                case "generate":
                    new CarGenerator(cars, amountOfCarsToGenerate);
                    break label;
                case "txt":
                    new CarImporterFromTxt(cars, scanner, amountOfCarsToGenerate);
                    break label;
                case "xml":
                    new CarImporterFromXml(cars, scanner, amountOfCarsToGenerate);
                    break label;
                case "json":
                    new CarImporterFromJson(cars, scanner, amountOfCarsToGenerate);
                    break label;
                case "database":
                    new DataBaseImporter(cars, scanner, amountOfCarsToGenerate);
                    break label;
                default:
                    System.out.println("Incorrect input: \"" + whatToDo + "\"");
                    System.out.println("Commands: \"generate\", \"txt\", \"xml\", \"json\", \"database\"");
                    break;
            }
        }
    }

}
