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

        listOfStrings = replaceEachSecond(listOfStrings);
        System.out.println("Replaced " + listOfStrings);

    }

    private static List<String> replaceEachSecond(List<String> list) {
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

}
