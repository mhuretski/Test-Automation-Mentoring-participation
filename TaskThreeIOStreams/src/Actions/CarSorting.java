package Actions;

import Garage.Car;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CarSorting implements Comparator<Car> {

    public void sortedByFuelConsumption(List<Car> cars, Scanner scanner) {
        cars.sort(this);
        System.out.println("Cars are sorted by fuel consumption.");

        new Show(cars, scanner);
    }

    @Override
    public int compare(Car o1, Car o2) {
        return Double.compare(o1.getFuelConsumption(), o2.getFuelConsumption());
    }

}
