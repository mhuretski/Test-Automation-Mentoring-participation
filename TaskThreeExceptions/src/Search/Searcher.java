package Search;

import Actions.Show;
import Exceptions.ExceptionsHandler;
import Garage.Car;

import java.util.List;
import java.util.Scanner;

public class Searcher {

    private List<Car> chosenCars;

    public Searcher(ExceptionsHandler ex, List<Car> cars, Scanner scanner) {
        TypeSearch ts = new TypeSearch();
        Show s = new Show();
        chosenCars = ts.chooseBrand(ex, cars, scanner);
        amountOfCars();
        s.whatToDo(chosenCars, scanner);
        chosenCars = ts.chooseCarBody(ex, chosenCars, scanner);
        amountOfCars();
        s.whatToDo(chosenCars, scanner);
        chosenCars = new ConsumptionSearch().fuelConsumptionRange(ex, chosenCars, scanner);
        amountOfCars();
        s.whatToDo(chosenCars, scanner);
        chosenCars = new PriceSearch().priceRange(ex, chosenCars, scanner);
        amountOfCars();

    }

    private void amountOfCars() {
        System.out.println(chosenCars.size() + " cars found.");
    }

    public List<Car> getChosenCars() {
        return chosenCars;
    }
}
