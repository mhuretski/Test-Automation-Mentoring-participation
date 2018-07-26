package Actions;

import Exceptions.ExceptionsHandler;
import Garage.Car;

import java.util.List;

public class TotalCost {

    public TotalCost(ExceptionsHandler ex, List<Car> cars) {
        int total = totalCost(ex, cars, cars.size());
        System.out.println("Total price of " + cars.size() + " cars is $" + total + ".");
    }

    private int totalCost(ExceptionsHandler ex, List<Car> cars, int size) {
        int total = 0;
        try {
            for (int i = 0; i < size; i++) {
                total += cars.get(i).getPrice();
            }
            return total;
        } catch (NullPointerException e) {
            ex.catchNullInTotalCost(e, cars);
            return totalCost(ex, cars, size);
        } catch (IndexOutOfBoundsException e) {
            size =  ex.catchOutOfBoundsInTotalCost(e, cars, size);
            return totalCost(ex, cars, size);
        }
    }

}
