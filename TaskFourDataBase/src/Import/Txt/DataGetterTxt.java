package Import.Txt;

import Import.DataGetter;

interface DataGetterTxt extends DataGetter {

    double getFuelConsumption(String data);

    int getPrice(String data);
}
