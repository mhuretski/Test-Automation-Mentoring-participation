package TaskOne.TaskOneMatrix;

import java.text.DecimalFormat;
import java.util.Scanner;

class Matrix {

    Matrix(Scanner scanner) {
        openMatrix(scanner);
    }

    private int matrixSign;
    private int numberOfRowChanges;

    private void openMatrix(Scanner scanner) {

        /* auto filling matrix data or manual data input */
        int chosenMethod = chooseMatrixCreationMethod(scanner);
        int[][] matrix = chooseMatrix(scanner, chosenMethod);

        System.out.println("Type \"help\" to see the list of commands.");
        while (true) {

            String whatToDo = scanner.nextLine();
            switch (whatToDo) {
                case "help":
                    System.out.println("Commands: " + "\n" +
                            "\"transpose\" - transpose matrix, show initial and transposed matrix." + "\n" +
                            "\"show\" - show initial matrix." + "\n" +
                            "\"rotate\" - rotate matrix on 90 degrees clockwise" + "\n" +
                            "\"multiply\" - multiply 2 matrices, show both and multiplied matrix." + "\n" +
                            "\"determinant\" - show matrix and matrix determinant." + "\n" +
                            "\"inverse\" - show initial and inverse matrix." + "\n" +
                            "\"choose\" - selection between generated and manually entered matrix." + "\n" +
                            "\"quit\" - close the application.");
                    break;
                case "choose":
                    openMatrix(scanner);
                case "transpose":
                    System.out.println("Initial Matrix:");
                    showMatrix(matrix);
                    validateMatrix(matrix);
                    transposeMatrix(matrix);
                    break;
                case "show":
                    System.out.println("Matrix:");
                    showMatrix(matrix);
                    break;
                case "rotate":
                    System.out.println("Initial Matrix:");
                    showMatrix(matrix);
                    validateMatrix(matrix);
                    rotateMatrix(matrix);
                    break;
                case "multiply":
                    System.out.println("Choose creation method for second matrix.");
                    chosenMethod = chooseMatrixCreationMethod(scanner);
                    int[][] secondMatrixForMultiply;
                    if (chosenMethod == 1) {
                        secondMatrixForMultiply = createSecondRandomMatrixForMultiply(matrix);

                    } else
                        secondMatrixForMultiply = createMatrixManually(scanner);
                    System.out.println("First Matrix:");
                    showMatrix(matrix);
                    validateMatrix(matrix);
                    System.out.println("Second random Matrix:");
                    showMatrix(secondMatrixForMultiply);
                    validateMatrix(secondMatrixForMultiply);
                    multiplyMatrix(matrix, secondMatrixForMultiply);
                    break;
                case "determinant":
                case "inverse":
                    System.out.println("Initial Matrix:");
                    showMatrix(matrix);
                    validateMatrix(matrix);
                    boolean isSquare = validateSquareMatrix(matrix);
                    if (chosenMethod == 1 && !isSquare) {
                        System.out.println("Square Matrix is generated:");
                        matrix = createRandomSquareMatrix();
                        isSquare = validateSquareMatrix(matrix);
                        showMatrix(matrix);
                    }
                    if (isSquare) {
                        double[][] remadeMatrix = zerosBelowMatrixDeterminant(matrix);
                        double determinant = determinantMatrix(remadeMatrix);

                        if (whatToDo.equals("determinant")) {
                            showDeterminant(determinant);
                            break;
                        }

                        if (determinant != 0) {
                            double[][] inverseWithInitial = inverseMatrixWithInitialMatrix(matrix);
                            double[][] inverseMatrix = inverseMatrix(inverseWithInitial, matrix);
                            System.out.println("Inverse Matrix:");
                            showMatrix(inverseMatrix);
                            /* debug
                            multiplyInverseMatrix(matrix, inverseMatrix);
                            */
                        } else
                            System.out.println("Determinant is equal to 0. Inverse matrix doesn't exist.");

                    }
                    break;
                case "quit":
                    System.out.println("Bye!");
                    System.exit(0);
                default:
                    System.out.println("Unknown command.");
                    System.out.println("Type \"help\" to see the list of commands.");
            }

        }

    }

    private int chooseMatrixCreationMethod(Scanner scanner) {
        int chosenMethod;
        String typeOfMatrix;
        System.out.println("Type \"auto\" to generate matrix or \"manual\" to create it manually.");

        while (true) {
            typeOfMatrix = scanner.nextLine();
            if (typeOfMatrix.equals("auto")) {
                chosenMethod = 1;
                break;
            } else if (typeOfMatrix.equals("manual")) {
                chosenMethod = 0;
                break;
            } else
                System.out.println("Choose \"auto\" or \"manual\".");
        }

        return chosenMethod;
    }

    private int[][] chooseMatrix(Scanner scanner, int chosenMatrixCreationMethod) {
        int[][] matrix;

        if (chosenMatrixCreationMethod == 1) {
            matrix = createRandomMatrix();

        } else
            matrix = createMatrixManually(scanner);

        return matrix;
    }

    private int[][] createMatrixManually(Scanner scanner) {

        System.out.println("Choose number of rows:");
        int rowScanner = validateNumbers(scanner);
        System.out.println("Choose number of columns:");
        int columnScanner = validateNumbers(scanner);
        int[][] matrix = new int[rowScanner][columnScanner];

        for (int row = 0; row < rowScanner; row++) {
            for (int column = 0; column < columnScanner; column++) {
                System.out.println("Fill " + (row + 1) + " row, " + (column + 1) + " column:");
                matrix[row][column] = validateNumbers(scanner);
            }
        }
        return matrix;
    }

    private int[][] createRandomMatrix() {

        int rowAmount = (int) (2 + Math.random() * 10);
        int columnAmount = (int) (2 + Math.random() * 10);
        int[][] matrix = new int[rowAmount][columnAmount];

        for (int row = 0; row < rowAmount; row++) {
            for (int column = 0; column < columnAmount; column++) {
                matrix[row][column] = (int) (Math.random() * 10);
            }
        }
        return matrix;

    }

    private int[][] createRandomSquareMatrix() {

        int matrixSize = (int) (2 + Math.random() * 10);
        int[][] matrix = new int[matrixSize][matrixSize];

        for (int row = 0; row < matrixSize; row++) {
            for (int column = 0; column < matrixSize; column++) {
                matrix[row][column] = (int) (Math.random() * 10);
            }
        }
        return matrix;

    }

    private int[][] createSecondRandomMatrixForMultiply(int[][] firstArray) {

        int rowScanner = firstArray[0].length;
        int columnScanner = (int) (2 + Math.random() * 10);
        int[][] matrix = new int[rowScanner][columnScanner];

        for (int row = 0; row < rowScanner; row++) {
            for (int column = 0; column < columnScanner; column++) {
                matrix[row][column] = (int) (Math.random() * 10);
            }
        }
        return matrix;

    }

    private int validateNumbers(Scanner scanner) {

        int firstValue;

        while (true) {

            String potentialNumber = scanner.nextLine();

            boolean onlyNumberValidation = potentialNumber.matches("[0-9]+");
            boolean zeroValidation = potentialNumber.matches("0");

            if (zeroValidation)
                System.out.println("Amount of rows or columns can't be equal to 0.");

            else if (onlyNumberValidation) {
                if (potentialNumber.length() < 5) {
                    if (Integer.parseInt(potentialNumber) <= 1000) {
                        firstValue = Integer.parseInt(potentialNumber);
                        break;
                    } else
                        System.out.println("Number is too high.");
                } else
                    System.out.println("Number is too high.");
            } else
                System.out.println("Incorrect number: \"" + potentialNumber + "\". Only numbers are allowed.");

        }
        return firstValue;

    }

    private void showMatrix(int[][] array) {

        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array[row].length; column++) {
                /* debug
                System.out.print(array[row][column] + "(" + row + ";" + column + ") ");
                */
                System.out.print(array[row][column] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void showMatrix(double[][] array) {

        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array[row].length; column++) {
                /* debug
                System.out.print(array[row][column] + "(" + row + ";" + column + ") ");
                */
                System.out.print(array[row][column] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void validateMatrix(int[][] array) {
        boolean isMatrix = true;
        for (int i = 0; i < array.length; i++) {
            if (array[0].length != array[i].length) {
                isMatrix = false;
            }
        }
        if (!isMatrix) {
            System.out.println("It's not a matrix!");
            System.exit(0);
        }
    }

    private boolean validateSquareMatrix(int[][] array) {
        boolean isSquare = true;
        if (array.length != array[0].length) {
            System.out.println("It's a not square matrix!");
            isSquare = false;
        }
        return isSquare;
    }

    private void transposeMatrix(int[][] array) {
        System.out.println("Transposed Matrix:");
        for (int row = 0; row < array[0].length; row++) {
            for (int column = 0; column < array.length; column++) {
                /* debug
                System.out.print(array[row][column] + "(" + row + ";" + column + ") ");
                */
                System.out.print(array[column][row] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void rotateMatrix(int[][] array) {
        System.out.println("Rotated Matrix:");
        for (int row = 0; row < array[0].length; row++) {
            for (int column = array.length; column > 0; column--) {
                /* debug
                System.out.print(array[column - 1][row] + "(" + (row) + ";" + (column - 1) + ") ");
                */
                System.out.print(array[column - 1][row] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    private void multiplyMatrix(int[][] arrayOne, int[][] arrayTwo) {

        if (arrayOne[0].length != arrayTwo.length) {
            System.out.println("Matrices can't be multiplied.");
        } else {

            System.out.println("Multiplied Matrix:");
            for (int rowArrOne = 0; rowArrOne < arrayOne.length; rowArrOne++) {

                for (int columnArrTwo = 0; columnArrTwo < arrayTwo[0].length; columnArrTwo++) {

                    int matrixCell = 0;

                    for (int rowArrTwo = 0; rowArrTwo < arrayTwo.length; rowArrTwo++) {

                        matrixCell = matrixCell + arrayOne[rowArrOne][rowArrTwo] * arrayTwo[rowArrTwo][columnArrTwo];
                        if (rowArrTwo == arrayTwo.length - 1)
                            System.out.print(matrixCell + " ");

                    }

                }
                System.out.println("");

            }
        }

    }

    private void multiplyInverseMatrix(int[][] arrayOne, double[][] arrayTwo) { /*  case "inverse" debugger */

        if (arrayOne[0].length != arrayTwo.length) {
            System.out.println("Matrices can't be multiplied.");
        } else {

            System.out.println("Multiplied Matrix:");
            for (int rowArrOne = 0; rowArrOne < arrayOne.length; rowArrOne++) {

                for (int columnArrTwo = 0; columnArrTwo < arrayTwo[0].length; columnArrTwo++) {

                    double matrixCell = 0;
                    String matrixCellString;

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

    private double rounding(double numToRound) {

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

    private double[][] zerosBelowMatrixDeterminant(int[][] arrayInt) {

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
        this.numberOfRowChanges = 0;

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
        if (numberOfRowChanges % 2 == 0) {
            this.matrixSign = 0;
        } else this.matrixSign = 1;

        return array;
    }

    private void swapRowsInMatrix(int diagonalPosition, double[][] array, double[][] tempArrDouble) {

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

    private double determinantMatrix(double[][] array) {

        double determinant = 0;

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

    private double[][] inverseMatrixWithInitialMatrix(int[][] arrayInt) {

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

    private double[][] inverseMatrix(double[][] inverseWithInitialMatrix, int[][] initialMatrix) {

        double[][] inverseMatrix = new double[initialMatrix.length][initialMatrix[0].length];

        /* excluding inverse matrix from initial+inverse matrix array */
        for (int row = 0; row < inverseMatrix.length; row++) {
            for (int column = 0; column < inverseMatrix[0].length; column++) {
                inverseMatrix[row][column] = inverseWithInitialMatrix[row][inverseWithInitialMatrix.length + column];
            }
        }

        return inverseMatrix;
    }

    private void showDeterminant(double determinant) {

        if (Double.toString(determinant).endsWith(".0")) {
            int determinantInt = (int) determinant;
            System.out.println("Determinant is " + determinantInt);
        } else System.out.println("Determinant is " + determinant);
    }

}

