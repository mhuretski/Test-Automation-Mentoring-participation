package Transformation;

import org.jetbrains.annotations.NotNull;

public class TransposeMatrix {

    private double[][] transposesMatrix;

    public TransposeMatrix(@NotNull double[][] array){

        transposesMatrix = new double[array[0].length][array.length];
        for (int row = 0; row < array[0].length; row++) {
            for (int column = 0; column < array.length; column++) {

                transposesMatrix[row][column] = array[column][row];

            }

        }

    }

    public double[][] returnTransposesMatrix(){
        return transposesMatrix;
    }


}
