package Import.DataBase;

import Import.DataGetter;

interface DataGetterDB extends DataGetter {

    double getFuelConsumption(double data);

    int getPrice(int data);
}
