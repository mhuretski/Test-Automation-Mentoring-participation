package Actions;

import Exceptions.ExceptionsHandler;
import Garage.Car;

import java.util.ArrayList;
import java.util.List;

public class CarGenerator {
    public CarGenerator(ExceptionsHandler ex, List<Car> cars, int amountOfCars) {
        for (int i = 0; i < amountOfCars - 3; i++) cars.add(new Car());
        /*2 nulls are added to trigger NullPointerException during TotalCost calculation*/
        addNull(cars);
        /*String with alphabetic symbols is added to Integer.valueOf()*/
        addStringInt(ex, amountOfCars, cars);
        /*primitive object to be treated as Car object*/
        addNotCar(ex, amountOfCars, cars);
        /*primitive object to be treated as Car object*/
        addNonexistentArrayCell(ex, amountOfCars, cars);
    }

    private void addNull(List<Car> cars) {
        for (int i = 0; i < 2; i++) {
            cars.add(null);
        }
    }

    private void addStringInt(ExceptionsHandler ex, int amountOfCars, List<Car> cars) {
        String price = "10000";
        if (amountOfCars > 0)
            try {
                cars.add(new Car(price + " dollars"));
            } catch (NumberFormatException e) {
                ex.catchNumFormatExDuringGeneration(e, cars, price);
            }
    }

    private void addNotCar(ExceptionsHandler ex, int amountOfCars, List<Car> cars) {
        if (amountOfCars > 1)
            try {
                cars.add((Car) new Generator());
            } catch (ClassCastException e) {
                ex.catchClassCastEx(e, cars);
            }
    }

    private void addNonexistentArrayCell(ExceptionsHandler ex, int amountOfCars, List<Car> cars) {
        if (amountOfCars > 2) {
            List<Car> car = new ArrayList<>();
            car.add(new Car());
            Car[] arrayCar = new Car[]{new Car()};
            try {
                cars.add(arrayCar[car.size()]);
            } catch (ArrayIndexOutOfBoundsException e) {
                ex.catchArrIndexOutOfBoundsInCarGen(e, arrayCar, cars);
            }
        }
    }

}
