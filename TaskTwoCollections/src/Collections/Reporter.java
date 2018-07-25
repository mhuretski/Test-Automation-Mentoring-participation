package Collections;

import java.util.List;
import java.util.Map;
import java.util.Set;

class Reporter extends Analyzer {
    Reporter(List<Integer> arrayList,
             List<Integer> linkedList,
             Set<Integer> hashSet,
             Set<Integer> treeSet,
             Map<Integer, String> hashMap,
             Map<Integer, String> treeMap,
             int amount) {

        arrayVsLinked(arrayList, linkedList, amount);
        System.out.println("--------");
        hashSetVsTreeSet(hashSet, treeSet, amount);
        System.out.println("--------");
        hashMapVsTreeMap(hashMap, treeMap, amount);

    }

    private void arrayVsLinked(List<Integer> arrayList, List<Integer> linkedList, int amount) {
        timeToCreate(arrayList, linkedList, amount);
        timeToSearchForFirstElement(arrayList, linkedList);
        timeToSearchForLastElement(arrayList, linkedList);
        timeToAddElementToBeginning(arrayList, linkedList);
        timeToDeleteLastElement(arrayList, linkedList);
        timeToDeleteFirstElement(arrayList, linkedList);
    }

    private void hashSetVsTreeSet(Set<Integer> hashSet, Set<Integer> treeSet, int amount) {
        timeToCreate(hashSet, treeSet, amount);
        timeToSearchForFirstElement(hashSet, treeSet);
        timeToSearchForLastElement(hashSet, treeSet);
        timeToDeleteLastElement(hashSet, treeSet);
        timeToDeleteFirstElement(hashSet, treeSet);
    }

    private void hashMapVsTreeMap(Map<Integer, String> hashSet, Map<Integer, String> treeSet, int amount) {
        timeToCreate(hashSet, treeSet, amount);
        timeToSearchForFirstElement(hashSet, treeSet);
        timeToSearchForLastElement(hashSet, treeSet);
        timeToDeleteLastElement(hashSet, treeSet);
        timeToDeleteFirstElement(hashSet, treeSet);
    }
}
