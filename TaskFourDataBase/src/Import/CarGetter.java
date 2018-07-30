package Import;

import Garage.Car;

import java.util.List;

public interface CarGetter {

    List<Car> getCars();

    boolean isNoErrors();
}
