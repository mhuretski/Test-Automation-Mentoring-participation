package Logic;

import java.util.Scanner;

class Validation {

    private String zeroValidationMessage;
    private String highNumberMessage;

    private void highNumber() {
        if (highNumberMessage == null) {
            highNumberMessage = "Number is too high.";
        }
    }

    private void validateZero() {
        if (zeroValidationMessage == null) {
            zeroValidationMessage = "Amount of rows or columns can't be equal to 0.";
        }
        System.out.println(zeroValidationMessage);
    }

    void validateMatrix(double[][] array) {
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

    boolean validateSquareMatrix(double[][] array) {
        boolean isSquare = true;
        if (array.length != array[0].length) {
            isSquare = false;
        }
        if (!isSquare) System.out.println("It's a not square matrix!");
        return isSquare;
    }

    int validateNumbers(Scanner scanner) {

        int firstValue;

        while (true) {

            String potentialNumber = scanner.nextLine();

            boolean onlyNumberValidation = potentialNumber.matches("[0-9]+");
            boolean zeroValidation = potentialNumber.matches("0");

            if (zeroValidation) validateZero();
            else if (onlyNumberValidation) {
                if (potentialNumber.length() < 5) {
                    if (Integer.parseInt(potentialNumber) <= 1000) {
                        firstValue = Integer.parseInt(potentialNumber);
                        break;
                    } else
                        highNumber();
                } else
                    highNumber();
            } else
                System.out.println("Incorrect number: \"" + potentialNumber + "\". Only numbers are allowed.");

        }
        return firstValue;

    }


}
