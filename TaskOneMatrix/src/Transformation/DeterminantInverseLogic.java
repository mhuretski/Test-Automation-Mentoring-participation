package Transformation;

import org.jetbrains.annotations.NotNull;

class DeterminantInverseLogic {

    int numberOfRowChanges;

    void swapRowsInMatrix(int diagonalPosition, @NotNull double[][] array, double[][] tempArrDouble) {

        if (array[diagonalPosition][diagonalPosition] == 0) {

            /* loop to find row with cell in diagonal != 0 and to swap it */
            for (int swapRow = 0; diagonalPosition + swapRow < array.length; swapRow++) {

                if (array[diagonalPosition + swapRow][diagonalPosition] != 0) {

                    /* tracking number of row swapping */
                    this.numberOfRowChanges++;

                    for (int changedArrayColumn = 0; changedArrayColumn < array[0].length; changedArrayColumn++) {

                        tempArrDouble[diagonalPosition][changedArrayColumn] = array[diagonalPosition][changedArrayColumn];
                        array[diagonalPosition][changedArrayColumn] = array[diagonalPosition + swapRow][changedArrayColumn];
                        array[diagonalPosition + swapRow][changedArrayColumn] = tempArrDouble[diagonalPosition][changedArrayColumn];
                    }
                    break;
                }

            }

        }
    }

    double rounding(double numToRound) {

        String determinantString = Double.toString(numToRound);
        if (determinantString.contains(".00") || determinantString.contains("E-")) {
            numToRound = (int) numToRound;
        } else if (determinantString.contains(".99") && determinantString.contains("-")) {
            numToRound = (int) numToRound - 1;
        } else if (determinantString.contains(".99") && !determinantString.contains("-")) {
            numToRound = (int) numToRound + 1;
        }
        return numToRound;
    }


}
