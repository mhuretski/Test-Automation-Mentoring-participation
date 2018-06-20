package Methods;

import Interface.MatrixMethods;
import Transformation.*;

import java.util.Scanner;

public class Actions extends InputLogic implements MatrixMethods {

    private String listOfCommandsMessage;
    private String helpMessage;
    private String unknownCommandMessage;
    private double[][] matrix;
    private int chosenMethod; /* 1 = auto, 0 = manual */
    private double determinant = 0;

    public void openMatrix(Scanner scanner) {
        chosenMethod = chooseMatrixCreationMethod(scanner);
        this.matrix = chooseMatrix(scanner, chosenMethod);

        if (listOfCommandsMessage == null) {
            listOfCommandsMessage = "Type \"help\" to see the list of commands.";
        }
        System.out.println(listOfCommandsMessage);

    }

    public void quit() {
        System.out.println("Bye!");
    }

    public void help() {
        if (helpMessage == null) {
            helpMessage = "Commands: \n" +
                    "\"transpose\" - transpose matrix, show initial and transposed matrix.\n" +
                    "\"show\" - show initial matrix.\n" +
                    "\"rotate\" - rotate matrix on 90 degrees clockwise\n" +
                    "\"multiply\" - multiply 2 matrices, show both and multiplied matrix.\n" +
                    "\"determinant\" - show matrix and matrix determinant.\n" +
                    "\"inverse\" - show initial and inverse matrix.\n" +
                    "\"choose\" - selection between generated and manually entered matrix.\n" +
                    "\"quit\" - close the application.";
        }
        System.out.println(helpMessage);
    }

    public void choose() {
        System.out.println("Let's choose another matrix.");
    }

    public void transpose(){
        System.out.println("Initial Matrix:");
        showMatrix(this.matrix);
        validateMatrix(this.matrix);
        System.out.println("Transposed Matrix:");
        this.matrix = new TransposeMatrix(this.matrix).returnTransposesMatrix();
        showMatrix(this.matrix);
    }

    public void show() {
        System.out.println("Matrix:");
        showMatrix(this.matrix);
    }

    public void rotate(){
        System.out.println("Initial Matrix:");
        showMatrix(this.matrix);
        validateMatrix(this.matrix);
        System.out.println("Rotated Matrix:");
        this.matrix = new RotateMatrix(this.matrix).returnRotatedMatrix();
        showMatrix(this.matrix);
    }

    public void multiply(Scanner scanner) {
        System.out.println("Choose creation method for second matrix.");
        int chosenMethodForMultiply = chooseMatrixCreationMethod(scanner);
        double[][] secondMatrixForMultiply;
        if (chosenMethodForMultiply == 1) {
            secondMatrixForMultiply = createSecondRandomMatrixForMultiply(this.matrix);
        } else
            secondMatrixForMultiply = createMatrixManually(scanner);

        System.out.println("First Matrix:");
        showMatrix(this.matrix);
        validateMatrix(this.matrix);

        if (chosenMethodForMultiply == 1) {
            System.out.println("Second random Matrix:");
        } else System.out.println("Second Matrix:");

        showMatrix(secondMatrixForMultiply);
        validateMatrix(secondMatrixForMultiply);
        MultiplyMatrix multiply = new MultiplyMatrix(this.matrix, secondMatrixForMultiply);
        boolean isEligibleForMultiplication = multiply.isEligibleForMultiplication();
        if (isEligibleForMultiplication) {
            double[][] multipliedMatrix = multiply.returnMultipliedMatrix();
            showMatrix(multipliedMatrix);
        }
    }

    public double determinant(String whatToDo) {
        System.out.println("Initial Matrix:");
        showMatrix(this.matrix);
        validateMatrix(this.matrix);
        boolean isSquare = validateSquareMatrix(this.matrix);

        if (chosenMethod == 1 && !isSquare) {
            System.out.println("Square Matrix is generated:");
            this.matrix = createRandomSquareMatrix();
            isSquare = validateSquareMatrix(this.matrix);
            showMatrix(this.matrix);
        }
        if (isSquare) {
            determinant = new DeterminantMatrix(matrix).returnDeterminant();
            if (whatToDo.equals("determinant")) {
                showDeterminant(determinant);
            }
        }

        return determinant;
    }

    public void inverse() {
        determinant = determinant("inverse");

        if (determinant != 0) {

            double[][] inverseMatrix = new InverseMatrix(this.matrix).returnInverseMatrix();
            System.out.println("Inverse Matrix:");
            showMatrix(inverseMatrix);
                /* debugger
            new InverseMatrix().multiplyInverseMatrix(this.matrix, inverseMatrix);
                */
        } else
            System.out.println("Inverse matrix doesn't exist.");
    }

    public void defaultAction() {
        if (unknownCommandMessage == null) {
            unknownCommandMessage =
                    "Unknown command. \n" +
                            "Type \"help\" to see the list of commands.";
        }
        System.out.println(unknownCommandMessage);
    }


}
