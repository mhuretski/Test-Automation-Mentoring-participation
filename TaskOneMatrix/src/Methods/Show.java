package Methods;

class Show extends Creation {

    void showMatrix(double[][] array) {

        int noZeros;
        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array[row].length; column++) {
                /* debug
                System.out.print(array[row][column] + "(" + row + ";" + column + ") ");
                */
                if (Double.toString(array[row][column]).endsWith(".0")) {
                    noZeros = (int) array[row][column];
                    System.out.print(noZeros + " ");
                } else System.out.print(array[row][column] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    void showDeterminant(double determinant) {

        if (Double.toString(determinant).endsWith(".0")) {
            int determinantInt = (int) determinant;
            System.out.println("Determinant is " + determinantInt);
        } else System.out.println("Determinant is " + determinant);
    }


}
