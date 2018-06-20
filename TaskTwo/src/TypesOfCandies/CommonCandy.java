package TypesOfCandies;

abstract class CommonCandy {

    private int size;
    private int width;
    private int length;
    private int height;

    public int setSize(int height, int length, int width){
        size = height * length * width;
        return size;
}

    abstract int getWeight() ;

    abstract int getSize() ;

    abstract boolean hasShinyWrapper() ;



}
