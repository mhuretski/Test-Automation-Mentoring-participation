package Main;

import Actions.CarSorting;
import Search.Searcher;
import Actions.Show;
import Actions.TotalCost;
import Garage.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 100; i++) cars.add(new Car());
        Scanner scanner = new Scanner(System.in);

        new TotalCost(cars);

        new CarSorting().sortedByFuelConsumption(cars, scanner);

        List<Car> chosenCars = new Searcher(cars, scanner).getChosenCars();
        new Show(chosenCars, scanner);


    }
}
