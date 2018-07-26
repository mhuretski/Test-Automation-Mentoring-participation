package Garage;

import Actions.Generator;

public class Car extends Generator {

    private Brand brand;
    private CarBody carBody;
    private double fuelConsumption;
    private int price;

    public Car() {
        setCar(Generator.random(Brand.values()),
                Generator.random(CarBody.values()),
                Generator.randFuelConsumption(),
                Generator.randPrice());
    }

    public Car(String price){
        setCar(Generator.random(Brand.values()),
                Generator.random(CarBody.values()),
                Generator.randFuelConsumption(),
                Integer.valueOf(price));
    }

    private void setCar(Brand brand, CarBody carBody, double fuelConsumption, int price) {
        this.brand = brand;
        this.carBody = carBody;
        this.fuelConsumption = fuelConsumption;
        this.price = price;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public Brand getBrand() {
        return brand;
    }

    public CarBody getCarBody() {
        return carBody;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s, %.1f litre/100km, price $%d]",
                brand, carBody, fuelConsumption, price);
    }

}
