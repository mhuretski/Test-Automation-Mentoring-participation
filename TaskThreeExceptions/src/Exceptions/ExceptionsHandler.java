package Exceptions;

import Garage.Car;
import Search.ConsumptionSearch;
import Search.PriceSearch;
import Search.TypeSearch;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ExceptionsHandler {

    private int exAmount;
    private int removedElements;

    /*NullPointerException*/
    public void catchNullInTotalCost(NullPointerException e, List<Car> cars) {
        exAmount++;
        System.err.println(exAmount + ". Error in the list of cars: " + e);
        for (Iterator<Car> iter = cars.listIterator(); iter.hasNext(); ) {
            Car a = iter.next();
            if (a == null) {
                iter.remove();
                removedElements++;
            }
        }
        if (removedElements == 1)
            System.err.println(exAmount + ". " + removedElements
                    + " element is removed from list of cars due to null value");
        else
            System.err.println(exAmount + ". " + removedElements
                    + " elements are removed from list of cars due to null value");
    }

    /*IndexOutOfBoundsException*/
    public int catchOutOfBoundsInTotalCost(IndexOutOfBoundsException e,
                                           List<Car> cars,
                                           int size) {
        exAmount++;
        System.err.println(exAmount + ". Error in the list of cars: " + e);
        System.err.println(exAmount + ". Expected list size is " + size + " due to "
                + removedElements + " null elements in the list. Actual list size is "
                + cars.size() + ". Nulls are removed");
        return cars.size();
    }

    /*NumberFormatException*/
    public void catchNumFormatExDuringGeneration(NumberFormatException e,
                                                 List<Car> cars,
                                                 String price) {
        exAmount++;
        System.err.println(exAmount + ". Error during car generation: " + e);
        cars.add(new Car(price));
        System.err.println(exAmount + ". Car is added with price: $" + price);
    }

    /*ClassCastException*/
    public void catchClassCastEx(ClassCastException e, List<Car> cars) {
        exAmount++;
        System.err.println(exAmount + ". Error during car generation: " + e);
        cars.add(new Car());
        System.err.println(exAmount + ". Car is repaired and added");
    }

    /*ArrayIndexOutOfBoundsException*/
    public void catchArrIndexOutOfBoundsInCarGen(ArrayIndexOutOfBoundsException e,
                                                 Car[] arrayCar,
                                                 List<Car> cars) {
        exAmount++;
        System.err.println(exAmount + ". Error during car generation. Car isn't found: " + e);
        cars.add(arrayCar[arrayCar.length - 1]);
        System.err.println(exAmount + ". Car is found and added");
    }

    /*user NumberFormatException for 2 scenarios*/
    public List<Car> catchNumFormatExDuringUserInput(ExceptionsHandler ex,
                                                     List<Car> cars,
                                                     Scanner scanner,
                                                     String userInput,
                                                     boolean isPriceNotConsumption,
                                                     boolean low) {
        exAmount++;
        System.err.println(exAmount + ". Incorrect input: \"" + userInput + "\"");
        if (isPriceNotConsumption)
            if (low)
                return new PriceSearch().lowPrices(ex, cars, scanner);
            else
                return new PriceSearch().highPrices(ex, cars, scanner);
        else {
            if (low)
                return new ConsumptionSearch().lowConsumption(ex, cars, scanner);
            else
                return new ConsumptionSearch().highConsumption(ex, cars, scanner);
        }
    }

    /*user IllegalArgumentException for 2 scenarios*/
    public List<Car> catchIllArgExUserInput(ExceptionsHandler ex,
                                            List<Car> cars,
                                            Scanner scanner,
                                            String userInput,
                                            boolean isBrandNotCarType) {
        exAmount++;
        System.err.println(exAmount + ". Incorrect input: \"" + userInput + "\"");
        if (isBrandNotCarType)
            return new TypeSearch().chooseBrand(ex, cars, scanner);
        else
            return new TypeSearch().chooseCarBody(ex, cars, scanner);
    }
}
