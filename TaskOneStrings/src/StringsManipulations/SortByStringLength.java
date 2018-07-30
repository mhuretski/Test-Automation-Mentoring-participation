package StringsManipulations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class SortByStringLength {

    public static void main(String[] args) {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("22");
        listOfStrings.add("55555");
        listOfStrings.add("333");
        listOfStrings.add("1");
        listOfStrings.add("4444");
        System.out.println("Initial " + listOfStrings);

        List<String> listOfStringsNew = new ArrayList<>(listOfStrings);
        System.out.println("Copied " + listOfStringsNew);

        long o1 = System.nanoTime();
        listOfStringsNew = sort(listOfStringsNew);
        long o2 = System.nanoTime();
        System.out.println("Sorted by script " + listOfStringsNew);

        long i1 = System.nanoTime();
        listOfStrings.sort(Comparator.comparingInt(String::length));
        long i2 = System.nanoTime();
        System.out.println("Sorted by comparator " + listOfStrings);

        System.out.println("Script " + (o2 - o1) + ". Comparator " + (i2 - i1));
    }

    private static List<String> sort(List<String> list) {
        List<String> tempList = new ArrayList<>();
        String tempString = list.get(0);
        for (Iterator<String> iter = list.listIterator(); iter.hasNext(); ) {
            for (int i = 1; i < list.size(); i++)
                if (tempString.length() > list.get(i).length())
                    tempString = list.get(i);
            list.remove(tempString);
            tempList.add(tempString);
            if (list.size() > 0) tempString = list.get(0);
        }
        return tempList;
    }

}
