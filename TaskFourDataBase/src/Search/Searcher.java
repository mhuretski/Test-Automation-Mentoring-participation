package Search;

import Actions.Show;
import Garage.Car;

import java.util.List;
import java.util.Scanner;

public class Searcher {

    private List<Car> chosenCars;

    public Searcher(List<Car> cars, Scanner scanner) {
        TypeSearch ts = new TypeSearch();
        Show s = new Show();
        chosenCars = ts.chooseBrand(cars, scanner);
        amountOfCars();
        s.whatToDo(chosenCars, scanner);
        chosenCars = ts.chooseCarBody(chosenCars, scanner);
        amountOfCars();
        s.whatToDo(chosenCars, scanner);
        chosenCars = new ConsumptionSearch().fuelConsumptionRange(chosenCars, scanner);
        amountOfCars();
        s.whatToDo(chosenCars, scanner);
        chosenCars = new PriceSearch().priceRange(chosenCars, scanner);
        amountOfCars();
        s.whatToDo(chosenCars, scanner);

    }

    private void amountOfCars() {
        System.out.println(chosenCars.size() + " cars found.");
    }

    public List<Car> getChosenCars() {
        return chosenCars;
    }

}
