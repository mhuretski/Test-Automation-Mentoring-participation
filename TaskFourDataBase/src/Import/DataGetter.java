package Import;

import Garage.Brand;
import Garage.CarBody;

public interface DataGetter {

    Brand getBrand(String data);

    CarBody getCarBody(String data);

}
