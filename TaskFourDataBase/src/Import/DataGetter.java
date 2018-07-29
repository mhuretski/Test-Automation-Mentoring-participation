package Import;

import Garage.Brand;
import Garage.CarBody;

/*According to the task import classes should extend common interface
* but some data from database is gotten from numbers instead of strings*/
public interface DataGetter {

    Brand getBrand(String data);

    CarBody getCarBody(String data);

}
