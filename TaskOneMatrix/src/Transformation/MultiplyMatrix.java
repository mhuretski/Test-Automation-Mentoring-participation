package Transformation;

import org.jetbrains.annotations.NotNull;

public class MultiplyMatrix {

    private double[][] multiplied;
    private boolean isEligibleForMultiplication;

    public MultiplyMatrix(@NotNull double[][] arrayOne, @NotNull double[][] arrayTwo) {

        if (arrayOne[0].length != arrayTwo.length) {
            System.out.println("Matrices can't be multiplied.");
            isEligibleForMultiplication = false;
        } else {
            this.multiplied = new double[arrayOne.length][arrayTwo[0].length];

            System.out.println("Multiplied Matrix:");
            for (int rowArrOne = 0; rowArrOne < arrayOne.length; rowArrOne++) {

                for (int columnArrTwo = 0; columnArrTwo < arrayTwo[0].length; columnArrTwo++) {

                    double matrixCell = 0;

                    for (int rowArrTwo = 0; rowArrTwo < arrayTwo.length; rowArrTwo++) {

                        matrixCell = matrixCell + arrayOne[rowArrOne][rowArrTwo] * arrayTwo[rowArrTwo][columnArrTwo];
                        if (rowArrTwo == arrayTwo.length - 1) {
                            multiplied[rowArrOne][columnArrTwo] = matrixCell;
                        }
                    }

                }


            }
            isEligibleForMultiplication = true;
        }

    }

    public double[][] returnMultipliedMatrix() {
        return multiplied;
    }

    public boolean isEligibleForMultiplication(){
        return isEligibleForMultiplication;
    }
}
