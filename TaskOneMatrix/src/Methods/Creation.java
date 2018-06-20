package Methods;

import java.util.Scanner;

class Creation extends Validation{

    double[][] createMatrixManually(Scanner scanner) {

        System.out.println("Choose number of rows:");
        int rowScanner = validateNumbers(scanner);
        System.out.println("Choose number of columns:");
        int columnScanner = validateNumbers(scanner);
        double[][] matrix = new double[rowScanner][columnScanner];

        for (int row = 0; row < rowScanner; row++) {
            for (int column = 0; column < columnScanner; column++) {
                System.out.println("Fill " + (row + 1) + " row, " + (column + 1) + " column:");
                matrix[row][column] = validateNumbers(scanner);
            }
        }
        return matrix;
    }

    double[][] createRandomMatrix() {

        int rowAmount = (int) (2 + Math.random() * 10);
        int columnAmount = (int) (2 + Math.random() * 10);
        double[][] matrix = new double[rowAmount][columnAmount];

        for (int row = 0; row < rowAmount; row++) {
            for (int column = 0; column < columnAmount; column++) {
                matrix[row][column] = (int) (Math.random() * 10);
            }
        }
        return matrix;

    }

    double[][] createRandomSquareMatrix() {

        int matrixSize = (int) (2 + Math.random() * 10);
        double[][] matrix = new double[matrixSize][matrixSize];

        for (int row = 0; row < matrixSize; row++) {
            for (int column = 0; column < matrixSize; column++) {
                matrix[row][column] = (int) (Math.random() * 10);
            }
        }
        return matrix;

    }

    double[][] createSecondRandomMatrixForMultiply(double[][] firstArray) {

        if (firstArray != null) {
            int rowScanner = firstArray[0].length;
            int columnScanner = (int) (2 + Math.random() * 10);
            double[][] matrix = new double[rowScanner][columnScanner];

            for (int row = 0; row < rowScanner; row++) {
                for (int column = 0; column < columnScanner; column++) {
                    matrix[row][column] = (int) (Math.random() * 10);
                }
            }
            return matrix;
        } else {
            System.out.println("Something went wrong.");
            new Actions().quit();
            return null;
        }
    }


}
