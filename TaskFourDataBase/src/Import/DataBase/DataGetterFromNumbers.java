package Import.DataBase;

import Import.DataGetter;

interface DataGetterFromNumbers extends DataGetter {

    double getFuelConsumption(double data);

    int getPrice(int data);

}
