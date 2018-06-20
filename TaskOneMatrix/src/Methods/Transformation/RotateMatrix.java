package Methods.Transformation;

import org.jetbrains.annotations.NotNull;

public class RotateMatrix {

    private double[][] rotatedMatrix;

    public RotateMatrix(@NotNull double[][] array){

        rotatedMatrix = new double[array[0].length][array.length];
        for (int row = 0; row < array[0].length; row++) {
            for (int rotatedColumn = 0, column = array.length; column > 0; column--, rotatedColumn++) {
                rotatedMatrix[row][rotatedColumn] = array[column - 1][row];
            }

        }

    }

    public double[][] returnRotatedMatrix(){
        return rotatedMatrix;
    }
}
