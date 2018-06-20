package Interface;

import java.util.Scanner;

public interface MatrixMethods {

    void openMatrix(Scanner scanner);

    void quit();

    void help();

    void choose();

    void transpose();

    void show();

    void rotate();

    void multiply(Scanner scanner);

    double determinant(String whatToDo);

    void inverse();

    void defaultAction();

}
