package Methods.Transformation;

import org.jetbrains.annotations.NotNull;
import java.text.DecimalFormat;

public class DeterminantMatrix extends DeterminantInverseLogic {

    private int matrixSign;
    private double determinant;

    public DeterminantMatrix (double[][] array){
        double[][] remadeMatrix = zerosBelowMatrixDeterminant(array);
        this.determinant = determinantMatrix(remadeMatrix);

    }

    public double returnDeterminant(){
        return determinant;
    }

    private double determinantMatrix(@NotNull double[][] array) {

        this.determinant = 0;

        /* multiplying all numbers in matrix diagonal */
        for (int diagonalPosition = 0; diagonalPosition < array.length; diagonalPosition++) {
            if (diagonalPosition == 0) {
                determinant = array[diagonalPosition][diagonalPosition];
            } else
                determinant = determinant * array[diagonalPosition][diagonalPosition];
        }

        /* if quantity of changing rows is odd then matrix should be marked with minus */
        if (matrixSign == 1) {
            determinant = -determinant;
        }

        determinant = rounding(determinant);

        return determinant;
    }

    private double[][] zerosBelowMatrixDeterminant(@NotNull double[][] arrayInt) {

        /* restriction after division */
        DecimalFormat df = new DecimalFormat("#.##########");

        double fractionMatrix;
        double[][] array = new double[arrayInt.length][arrayInt[0].length];
        double[][] tempArrDouble = new double[array.length][array[0].length];
        for (int i = 0; i < arrayInt.length; i++) {
            for (int j = 0; j < arrayInt[0].length; j++) {
                array[i][j] = arrayInt[i][j];
            }
        }


        double temp;
        super.numberOfRowChanges = 0;

        /* loop to get right position (position in diagonal of the matrix) in the matrix */
        for (int diagonalPosition = 0; diagonalPosition < array.length; diagonalPosition++) {

            /* if cell in diagonal == 0, row should be swapped */
            swapRowsInMatrix(diagonalPosition, array, tempArrDouble);

            /* if after swapping nothing changed with diagonal cell determinant == 0 */
            if (array[diagonalPosition][diagonalPosition] != 0) {

                /* loop for getting 0 in each column below matrix diagonal */
                for (int row = diagonalPosition, column = diagonalPosition; row + 1 < array.length && column < array[0].length; row++, column++) {

                    /* loop for transforming each cell in the row */
                    temp = array[row + 1][diagonalPosition];
                    for (int newColumn = diagonalPosition; newColumn < array[0].length; newColumn++) {

                        fractionMatrix = array[diagonalPosition][newColumn] / array[diagonalPosition][diagonalPosition];
                        fractionMatrix = Double.valueOf(df.format(fractionMatrix));
                        array[row + 1][newColumn] -= temp * fractionMatrix;

                    }

                }

            } else break;

        }

        /* if quantity of changing rows is odd then matrix should be marked with minus */
        if (super.numberOfRowChanges % 2 == 0) {
            this.matrixSign = 0;
        } else this.matrixSign = 1;

        return array;
    }


}
