package Actions;

import Garage.Car;

import java.util.List;

public class TotalCost {
    public TotalCost(List<Car> cars) {
        long total = 0;
        for (Car car : cars) {
            total += car.getPrice();
        }
        System.out.println("Total price of " + cars.size() + " cars is $" + total + ".");
    }
}
