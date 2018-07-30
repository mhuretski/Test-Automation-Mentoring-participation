package StringsManipulations;

import java.util.ArrayList;
import java.util.List;

public class RevertStrings {

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

        listOfStrings = replace(listOfStrings);
        System.out.println("Reverted " + listOfStrings);

    }

    private static List<String> replace(List<String> list) {
        List<String> tempList = new ArrayList<>();
        StringBuilder builder;
        for (String word : list) {
            builder = new StringBuilder(word.length());
            for (int j = word.length() - 1; j >= 0; j--)
                builder.append(word.charAt(j));
            tempList.add(builder.toString());
        }
        return tempList;
    }

}
