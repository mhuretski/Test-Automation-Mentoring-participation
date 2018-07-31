package StringsManipulations;

import java.util.ArrayList;
import java.util.List;

public class FirstLastLetter {

    public static void main(String[] args) {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("Consonants");
        listOfStrings.add("");
        listOfStrings.add("Consonant");
        listOfStrings.add("b");
        listOfStrings.add("3Vowel");
        listOfStrings.add("av");
        listOfStrings.add("Co a");
        listOfStrings.add("Cons");
        listOfStrings.add("Co an");
        System.out.println("Initial " + listOfStrings);

        List<String> listOfStringsNew = new ArrayList<>(listOfStrings);
        System.out.println("Copied " + listOfStringsNew);

        long o1 = System.nanoTime();
        listOfStrings = replace(listOfStrings);
        long o2 = System.nanoTime();
        System.out.println("Replaced with strings " + listOfStrings);

        long i1 = System.nanoTime();
        listOfStringsNew = replaceWithBuilder(listOfStringsNew);
        long i2 = System.nanoTime();
        System.out.println("Replaced with builder " + listOfStringsNew);

        System.out.println("Strings " + (o2 - o1) + ". Builder " + (i2 - i1));
    }

    private static List<String> replace(List<String> list) {
        List<String> tempList = new ArrayList<>();
        char first;
        String tempWord;
        char last;
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0 && i != 0) {
                if (list.get(i).length() > 2) {
                    first = list.get(i).charAt(0);
                    tempWord = list.get(i).substring(1, list.get(i).length() - 1);
                    last = list.get(i).charAt(list.get(i).length() - 1);
                    tempWord = String.valueOf(last) + tempWord + String.valueOf(first);
                } else if (list.get(i).length() == 2) {
                    first = list.get(i).charAt(0);
                    last = list.get(i).charAt(list.get(i).length() - 1);
                    tempWord = String.valueOf(last) + String.valueOf(first);
                } else tempWord = list.get(i);
            } else tempWord = list.get(i);
            tempList.add(tempWord);
        }
        return tempList;
    }

    private static List<String> replaceWithBuilder(List<String> list) {
        List<String> tempList = new ArrayList<>();
        StringBuilder builder;
        String tempWord;
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0 && i != 0) {
                builder = new StringBuilder(list.get(i).length());
                if (list.get(i).length() > 2) {
                    builder.append(list.get(i).charAt(list.get(i).length() - 1))
                            .append(list.get(i).substring(1, list.get(i).length() - 1))
                            .append(list.get(i).charAt(0));
                    tempWord = builder.toString();
                } else if (list.get(i).length() == 2) {
                    builder.append(list.get(i).charAt(list.get(i).length() - 1))
                            .append(list.get(i).charAt(0));
                    tempWord = builder.toString();
                } else tempWord = list.get(i);
            } else tempWord = list.get(i);
            tempList.add(tempWord);
        }
        return tempList;
    }

}
