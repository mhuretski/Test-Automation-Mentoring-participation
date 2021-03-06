package StringsManipulations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SortByVowels {

    public static void main(String[] args) {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("3vowelsA");
        listOfStrings.add("2vowels");
        listOfStrings.add("1vowL");
        listOfStrings.add("5EvoUwElsA");
        listOfStrings.add("4IVowelsA");
        listOfStrings.add("6EoUeEA");
        System.out.println("Initial " + listOfStrings);

        List<String> listOfStringsNew = new ArrayList<>(listOfStrings);
        System.out.println("Copied " + listOfStringsNew);

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
        Character[] vowels = new Character[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        list.sort((o1, o2) -> {
            int o1l = 0;
            for (int i = 0; i < o1.length(); i++)
                for (char vowel : vowels)
                    if (o1.charAt(i) == vowel) o1l++;
            int o2l = 0;
            for (int i = 0; i < o2.length(); i++)
                for (char vowel : vowels)
                    if (o2.charAt(i) == vowel) o2l++;
            return Integer.compare(o1l, o2l);
        });
    }

    private static List<String> sortByVowels(List<String> list) {
        Character[] vowels = new Character[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        List<String> tempList = new ArrayList<>();
        for (Iterator<String> iter = list.listIterator(); iter.hasNext(); ) {
            String tempString = null;
            int vowelCountDefault = -1;
            for (String aList : list) {
                int vowelCount = 0;
                for (int j = 0; j < aList.length(); j++)
                    for (char vowel : vowels)
                        if (aList.charAt(j) == vowel) vowelCount++;
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
