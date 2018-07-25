package Collections;

import java.util.*;

class Main {
    public static void main(String[] args) {

        int amount = 100000;

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        Set<Integer> hashSet = new HashSet<>();
        Set<Integer> treeSet = new TreeSet<>();

        Map<Integer, String> hashMap = new HashMap<>();
        Map<Integer, String> treeMap = new TreeMap<>();

        new Reporter(arrayList, linkedList, hashSet, treeSet, hashMap, treeMap, amount);

    }
}
