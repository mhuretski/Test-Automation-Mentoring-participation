package Main;

import Actions.CarGenerator;
import Actions.CarSorting;
import Actions.Show;
import Actions.TotalCost;
import Export.CarExporter;
import Garage.Car;
import Search.Searcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        new CarGenerator(cars, 100);

        new TotalCost(cars);

        Scanner scanner = new Scanner(System.in);
        new CarSorting().sortedByFuelConsumption(cars, scanner);

        cars = new Searcher(cars, scanner).getChosenCars();
        new CarExporter(cars);

    }
}
