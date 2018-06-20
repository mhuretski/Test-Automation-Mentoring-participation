import TypesOfCandies.ChocolateBar;
import TypesOfCandies.Toffee;

public class NewYearGift {
    public static void main(String[] args) {
        ChocolateBar chocolateBar = new ChocolateBar();
        int a = chocolateBar.getSize();
        System.out.println(a);
        int b = chocolateBar.getWeight();
        System.out.println(b);

        Toffee toffee = new Toffee();


        int e = toffee.size();
        System.out.println(e);
    }
}
