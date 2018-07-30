package StringsManipulations;

import java.util.ArrayList;
import java.util.List;

public class SecondByLength {

    public static void main(String[] args) {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("22");
        listOfStrings.add("55555");
        listOfStrings.add("333");
        listOfStrings.add("1");
        listOfStrings.add("4444");
        listOfStrings.add("666666");
        System.out.println("Initial " + listOfStrings);
        System.out.println("Second max length string is " + secondMax(listOfStrings));
    }

    private static String secondMax(List<String> list) {
        String tempString = list.get(0);
        if (list.size() > 1) {
            for (int i = 1; i < list.size(); i++)
                if (tempString.length() < list.get(i).length())
                    tempString = list.get(i);
            list.remove(tempString);
            tempString = list.get(0);
            for (int i = 1; i < list.size(); i++)
                if (tempString.length() < list.get(i).length())
                    tempString = list.get(i);
        }
        return tempString;
    }

}
