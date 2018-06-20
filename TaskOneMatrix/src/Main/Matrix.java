package Main;

import Methods.Actions;

import java.util.Scanner;

public class Matrix {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Actions action = new Actions();

        quitAction:
        while (true) {
            action.openMatrix(scanner);

            chooseAction:
            while (true) {

                String whatToDo = scanner.nextLine();
                switch (whatToDo) {
                    case "help":
                        action.help();
                        break;
                    case "choose":
                        action.choose();
                        break chooseAction;
                    case "transpose":
                        action.transpose();
                        break;
                    case "show":
                        action.show();
                        break;
                    case "rotate":
                        action.rotate();
                        break;
                    case "multiply":
                        action.multiply(scanner);
                        break;
                    case "determinant":
                        action.determinant(whatToDo);
                        break;
                    case "inverse":
                        action.inverse();
                        break;
                    case "quit":
                        action.quit();
                        break quitAction;
                    default:
                        action.defaultAction();
                }

            }
        }
    }

}


