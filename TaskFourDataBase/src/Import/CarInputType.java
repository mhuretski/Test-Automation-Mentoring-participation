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
        boolean isDone = true;
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
                    CarGenerator gen = new CarGenerator(amountOfCarsToGenerate);
                    cars.addAll(gen.getCars());
                    break label;
                case "txt":
                    CarImporterFromTxt txt = new CarImporterFromTxt();
                    if (txt.isNoErrors()) cars.addAll(txt.getCars());
                    else isDone = false;
                    break label;
                case "xml":
                    CarImporterFromXml xml = new CarImporterFromXml();
                    if (xml.isNoErrors()) cars.addAll(xml.getCars());
                    else isDone = false;
                    break label;
                case "json":
                    CarImporterFromJson json = new CarImporterFromJson();
                    if (json.isNoErrors()) cars.addAll(json.getCars());
                    else isDone = false;
                    break label;
                case "database":
                    DataBaseImporter db = new DataBaseImporter();
                    if (db.isNoErrors()) cars.addAll(db.getCars());
                    else isDone = false;
                    break label;
                default:
                    System.out.println("Incorrect input: \"" + whatToDo + "\"");
                    System.out.println("Commands: \"generate\", \"txt\", \"xml\", \"json\", \"database\"");
                    break;
            }
        }
        if (!isDone) {
            System.err.println("Try other options.");
            whatToDo(cars, amountOfCarsToGenerate, scanner);
        }
    }

}
