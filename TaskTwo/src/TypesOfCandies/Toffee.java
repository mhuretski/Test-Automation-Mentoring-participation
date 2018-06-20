package TypesOfCandies;

public class Toffee extends CommonCandy {

    private int size;

    public int size() {
        int width = 1;
        int length = 2;
        int height = 3;
        return setSize(height, length, width);
    }

    int getWeight() {
        return 0;
    }

    int getSize() {
        return size();
    }

    boolean hasShinyWrapper() {
        return false;
    }
}
