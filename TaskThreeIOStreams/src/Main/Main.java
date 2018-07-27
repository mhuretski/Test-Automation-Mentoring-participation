package Main;

import Actions.CarSorting;
import Actions.TotalCost;
import Export.CarExporter;
import Garage.Car;
import Import.CarInputType;
import Search.Searcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        new CarInputType(cars, scanner, 100);

        new TotalCost(cars);

        new CarSorting().sortedByFuelConsumption(cars, scanner);

        cars = new Searcher(cars, scanner).getChosenCars();
        new CarExporter(cars);

    }
}
