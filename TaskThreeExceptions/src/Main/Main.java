package Main;

import Actions.CarGenerator;
import Actions.CarSorting;
import Actions.Show;
import Actions.TotalCost;
import Exceptions.ExceptionsHandler;
import Garage.Car;
import Search.Searcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        ExceptionsHandler ex = new ExceptionsHandler();
        new CarGenerator(ex, cars, 100);

        new TotalCost(ex, cars);

        Scanner scanner = new Scanner(System.in);
        new CarSorting().sortedByFuelConsumption(cars, scanner);

        List<Car> chosenCars = new Searcher(ex, cars, scanner).getChosenCars();
        new Show(chosenCars, scanner);

    }
}
