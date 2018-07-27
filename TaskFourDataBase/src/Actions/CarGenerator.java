package Actions;

import Garage.Car;

import java.util.List;

public class CarGenerator {
    public CarGenerator(List<Car> cars, int amountOfCars){
        for (int i = 0; i < amountOfCars; i++) cars.add(new Car());
    }
}
