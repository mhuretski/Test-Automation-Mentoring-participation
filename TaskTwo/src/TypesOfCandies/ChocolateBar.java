package TypesOfCandies;

public class ChocolateBar extends CommonCandy {

    private int size;

    public ChocolateBar(){
        int width = 4;
        int length = 10;
        int height = 1;
        this.size = height * length * width;
    }

    public int getWeight() {
        int weight = 10;
        return weight;
    }

    public int getSize() {
        return this.size;
    }

    public boolean hasShinyWrapper() {
        return false;
    }


}
