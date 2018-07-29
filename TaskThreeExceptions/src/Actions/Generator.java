package Actions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {

    protected <T extends Enum> T random(T[] enumValues) {
        return enumValues[new Random().nextInt(enumValues.length)];
    }

    protected double randFuelConsumption() {
        double fuelCons = ThreadLocalRandom.current().nextDouble(6, 16 + 1);
        fuelCons = new BigDecimal(fuelCons).setScale(1, RoundingMode.HALF_UP).doubleValue();
        return fuelCons;
    }

    protected int randPrice() {
        return ThreadLocalRandom.current().nextInt(70, 250 + 1) * 100;
    }

}
