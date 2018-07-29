package Import;

import Garage.Brand;
import Garage.CarBody;

public interface CarSetter {

    Brand getBrand();

    CarBody getCarBody();

    double getFuelConsumption();

    int getPrice();
}
