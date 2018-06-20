package Methods;

import java.util.Scanner;

class InputLogic extends Show {

    private String chooseMethodMessage;

    double[][] chooseMatrix(Scanner scanner, int chosenMatrixCreationMethod) {
        double[][] matrix;

        if (chosenMatrixCreationMethod == 1) {
            matrix = createRandomMatrix();

        } else
            matrix = createMatrixManually(scanner);

        return matrix;
    }

    int chooseMatrixCreationMethod(Scanner scanner) {
        int chosenMethod;
        String typeOfMatrix;

        System.out.println("Type \"auto\" to generate matrix or \"manual\" to create it manually.");

        label:
        while (true) {
            typeOfMatrix = scanner.nextLine();
            switch (typeOfMatrix) {
                case "auto":
                    chosenMethod = 1;
                    break label;
                case "manual":
                    chosenMethod = 0;
                    break label;
                default:
                    if (this.chooseMethodMessage == null) {
                        this.chooseMethodMessage = "Choose \"auto\" or \"manual\".";
                    }
                    System.out.println(chooseMethodMessage);
                    break;
            }
        }

        return chosenMethod;
    }


}
