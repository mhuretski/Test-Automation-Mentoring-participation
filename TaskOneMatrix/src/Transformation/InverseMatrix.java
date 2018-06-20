package Transformation;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class InverseMatrix extends DeterminantInverseLogic {

    private double[][] inverseMatrix;

    public InverseMatrix(double[][] arrayInt){

        double[][] inverseWithInitial = inverseMatrixWithInitialMatrix(arrayInt);
        this.inverseMatrix = inverseMatrix(inverseWithInitial, arrayInt);

    }

    public double[][] returnInverseMatrix(){
        return inverseMatrix;
    }

    private double[][] inverseMatrixWithInitialMatrix(@NotNull double[][] arrayInt) {

        /* restriction after division */
        DecimalFormat df = new DecimalFormat("#.#################");

        double[][] array = new double[arrayInt.length][arrayInt[0].length * 2];
        double[][] tempArrDouble = new double[arrayInt.length][arrayInt[0].length * 2];

        for (int row = 0; row < arrayInt.length; row++) {
            for (int column = 0; column < arrayInt[0].length; column++) {
                array[row][column] = arrayInt[row][column];
            }
        }

        /* adding additional return matrix data */
        for (int row = 0; row < arrayInt.length; row++) {
            int column = arrayInt[0].length + row;
            array[row][column] = 1;
        }

        double fractionMatrix;
        double tempReturn;

        for (int diagonalPosition = 0; diagonalPosition < array.length; diagonalPosition++) {

            /* if cell in diagonal == 0, row should be swapped */
            swapRowsInMatrix(diagonalPosition, array, tempArrDouble);

            tempReturn = array[diagonalPosition][diagonalPosition];

            /* row division to get 1 in diagonal */
            for (int column = 0; column < array[0].length; column++) {

                fractionMatrix = array[diagonalPosition][column] / tempReturn;
                fractionMatrix = Double.valueOf(df.format(fractionMatrix));
                array[diagonalPosition][column] = fractionMatrix;

            }

            /* changing other rows after first row division */
            for (int row = 0; row < array.length; row++) {

                if (row != diagonalPosition) {
                    tempReturn = array[row][diagonalPosition];

                    for (int column = 0; column < array[0].length; column++) {
                        array[row][column] = array[row][column] - tempReturn * array[diagonalPosition][column];
                    }

                }

            }

        }

        return array;
    }

    private double[][] inverseMatrix(double[][] inverseWithInitialMatrix, @NotNull double[][] initialMatrix) {

        double[][] inverseMatrix = new double[initialMatrix.length][initialMatrix[0].length];

        /* excluding inverse matrix from initial+inverse matrix array */
        for (int row = 0; row < inverseMatrix.length; row++) {
            for (int column = 0; column < inverseMatrix[0].length; column++) {
                inverseMatrix[row][column] = inverseWithInitialMatrix[row][inverseWithInitialMatrix.length + column];
            }
        }

        return inverseMatrix;
    }

    public InverseMatrix(){}

    public void multiplyInverseMatrix(int[][] arrayOne, double[][] arrayTwo) { /*  case "inverse" debugger */

        if (arrayOne[0].length != arrayTwo.length) {
            System.out.println("Matrices can't be multiplied.");
        } else {

            System.out.println("Multiplied Matrix:");
            for (int rowArrOne = 0; rowArrOne < arrayOne.length; rowArrOne++) {

                for (int columnArrTwo = 0; columnArrTwo < arrayTwo[0].length; columnArrTwo++) {

                    double matrixCell = 0;

                    for (int rowArrTwo = 0; rowArrTwo < arrayTwo.length; rowArrTwo++) {

                        matrixCell = matrixCell + arrayOne[rowArrOne][rowArrTwo] * arrayTwo[rowArrTwo][columnArrTwo];
                        if (rowArrTwo == arrayTwo.length - 1) {

                            matrixCell = rounding(matrixCell);

                            if (Double.toString(matrixCell).endsWith(".0")) {
                                int matrixCellInt = (int) matrixCell;
                                System.out.print(matrixCellInt + " ");
                            } else
                                System.out.print(matrixCell + " ");
                        }
                    }

                }
                System.out.println("");

            }
        }

    }


}