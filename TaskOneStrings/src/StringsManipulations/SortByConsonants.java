package StringsManipulations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SortByConsonants {

    public static void main(String[] args) {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("7Consonants");
        listOfStrings.add("6Consonant");
        listOfStrings.add("3Vowel");
        listOfStrings.add("1v");
        listOfStrings.add("3Cons");
        listOfStrings.add("2Co n");
        System.out.println("Initial " + listOfStrings);

        List<String> listOfStringsNew = new ArrayList<>(listOfStrings);

        long o1 = System.nanoTime();
        listOfStringsNew = sortByVowels(listOfStringsNew);
        long o2 = System.nanoTime();
        System.out.println("Sorted by script " + listOfStringsNew);

        long i1 = System.nanoTime();
        sortByComparator(listOfStrings);
        long i2 = System.nanoTime();
        System.out.println("Sorted by comparator " + listOfStrings);

        System.out.println("Script " + (o2 - o1) + ". Comparator " + (i2 - i1));
    }

    private static void sortByComparator(List<String> list) {
        String regex = "([^aeiouyAEIOUY0-9\\W]+)";
        list.sort((o1, o2) -> {
            int o1l = 0;
            for (int i = 0; i < o1.length(); i++)
                if (Character.toString(o1.charAt(i)).matches(regex)) o1l++;
            int o2l = 0;
            for (int i = 0; i < o2.length(); i++)
                if (Character.toString(o2.charAt(i)).matches(regex)) o2l++;
            return Integer.compare(o1l, o2l);
        });
    }

    private static List<String> sortByVowels(List<String> list) {
        String regex = "([^aeiouyAEIOUY0-9\\W]+)";
        List<String> tempList = new ArrayList<>();
        for (Iterator<String> iter = list.listIterator(); iter.hasNext(); ) {
            String tempString = null;
            int vowelCountDefault = -1;
            for (String aList : list) {
                int vowelCount = 0;
                for (int j = 0; j < aList.length(); j++)
                    if (Character.toString(aList.charAt(j)).matches(regex))
                        vowelCount++;
                if (vowelCountDefault > vowelCount || vowelCountDefault == -1) {
                    vowelCountDefault = vowelCount;
                    tempString = aList;
                }
            }
            list.remove(list.indexOf(tempString));
            tempList.add(tempString);
        }
        return tempList;
    }

}
