package Export;

import Garage.Car;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CarExporter {

    private List<Car> cars;

    public CarExporter(List<Car> cars) {
        this.cars = cars;
        execute();
    }

    private void execute() {
        String path = "src/cars.txt";
        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(path, true),
                                StandardCharsets.UTF_8)))) {
            for (Car car : cars) {
                writer.append(car.toString()).println();
            }
            System.out.println(cars.size() + " cars are added to " + path);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
