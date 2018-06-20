package Calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Logic calculator = new Logic();

        calculator.openCalculator(scanner);

    }
}
