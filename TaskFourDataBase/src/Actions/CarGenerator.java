package Actions;

import Garage.Car;
import Import.CarGetter;

import java.util.ArrayList;
import java.util.List;

public class CarGenerator implements CarGetter {

    private List<Car> tempCars;

    public CarGenerator(int amountOfCars){
        tempCars = new ArrayList<>();
        for (int i = 0; i < amountOfCars; i++) tempCars.add(new Car());
    }

    @Override
    public List<Car> getCars() {
        return tempCars;
    }

    @Override
    public boolean isNoErrors() {
        return true;
    }

}
