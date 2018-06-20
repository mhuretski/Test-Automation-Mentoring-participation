package Calculator;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

class Logic {

    void openCalculator(Scanner scanner) {

        System.out.println("Enter the first number:");
        double firstValue = validateNumbers(scanner);

        System.out.println("Enter maths sign:");
        String signValue = allowedSigns(scanner);

        System.out.println("Enter the second number:");
        double secondValue = validateNumbers(scanner);

        double result = resultCalc(firstValue, signValue, secondValue, scanner);
        zeroBeautifier(result, scanner);

    }

    private double validateNumbers(@NotNull Scanner scanner) {

        double firstValue;

        while (true) {

            String potentialNumber = scanner.nextLine();
            byeIfQuit(potentialNumber);
            boolean onlyNumberValidation = !potentialNumber.contains(",") && (potentialNumber.matches("[0-9]+") || potentialNumber.matches("[0-9]+" + "." + "[0-9]+"));

            if (onlyNumberValidation) {
                firstValue = Double.parseDouble(potentialNumber);
                break;
            } else
                System.out.println("Incorrect number: \"" + potentialNumber + "\". Only numbers and dot are allowed.");

        }
        return firstValue;

    }

    private String allowedSigns(@NotNull Scanner scanner) {

        String signValue;
        for (int i = 0; ; i++) {

            signValue = scanner.nextLine();
            byeIfQuit(signValue);

            String[] allowedSigns = {"+", "/", "-", "*", "%"};

            boolean valid = false;

            if (i != 0 && i % 5 == 0) {
                System.out.println("The list of supported mathematical signs: ");

                for (String signArr : allowedSigns) {
                    System.out.println(signArr);
                }

            }

            for (String signArr : allowedSigns) {

                if (signValue.equals(signArr)) {
                    valid = true;
                    break;
                }
            }
            if (valid) break;
            else
                System.out.println("Incorrect mathematical sign: \"" + signValue + "\". Try again.");

        }
        return signValue;
    }

    private double resultCalc(double firstValue, @NotNull String signValue, double secondValue, Scanner scanner) {
        double result = 0;

        switch (signValue) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "/":
                for (; ; ) {

                    if (secondValue == 0) {
                        System.out.println("Illegal division by zero. Re-enter the second number:");
                        secondValue = validateNumbers(scanner);
                    } else break;
                }
                result = firstValue / secondValue;
                break;
            case "*":
                result = firstValue * secondValue;
                break;
            case "%":
                result = firstValue % secondValue;
                break;

        }
        return result;
    }

    private void zeroBeautifier(double result, Scanner scanner) {
        String totalValueString = Double.toString(result);
        if (totalValueString.endsWith(".0")) {
            System.out.println("The result: " + (int) result);
        } else
            System.out.println("The result: " + result);
        System.out.println("If you want to close the application, type \"quit\".");
        openCalculator(scanner);
    }

    private void byeIfQuit(@NotNull String quitValue) {

        if (quitValue.toLowerCase().contains("quit")) {
            System.out.println("Bye!");
            System.exit(0);
        }
    }

}