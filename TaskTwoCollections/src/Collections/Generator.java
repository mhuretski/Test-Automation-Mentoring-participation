package Collections;

import java.util.Collection;
import java.util.List;
import java.util.Map;

class Generator {

    double fill(Collection<Integer> list, int i) {
        long starTime = System.nanoTime();
        for (int j = 0; j < i; j++) {
            list.add(j);
        }
        long endTime = System.nanoTime();
        return endTime - starTime;
    }

    double fill(Map<Integer, String> map, int i) {
        long starTime = System.nanoTime();
        char a;
        int j;
        for (j = 0, a = 'a'; j < i; j++, a++) {
            map.put(j, String.valueOf(a));
        }
        long endTime = System.nanoTime();
        return endTime - starTime;
    }

    double searchForFirstElement(Collection<Integer> list) {
        long starTime = System.nanoTime();
        for (int item : list) {
            if (item == 0) break;
        }
        long endTime = System.nanoTime();
        return endTime - starTime;
    }

    double searchForFirstElement(Map<Integer, String> map) {
        long starTime = System.nanoTime();
        map.get(0);
        long endTime = System.nanoTime();
        return endTime - starTime;
    }

    double searchForLastElement(Collection<Integer> list) {
        long starTime = System.nanoTime();
        for (int item : list) {
            if (item == list.size() - 1) break;
        }
        long endTime = System.nanoTime();
        return endTime - starTime;
    }

    double searchForLastElement(Map<Integer, String> map) {
        long starTime = System.nanoTime();
        map.get(map.size() - 1);
        long endTime = System.nanoTime();
        return endTime - starTime;
    }

    double addElementToStart(List<Integer> list) {
        long starTime = System.nanoTime();
        list.add(0, 1);
        long endTime = System.nanoTime();
        return endTime - starTime;
    }

    double deleteLast(Collection<Integer> list) {
        long starTime = System.nanoTime();
        list.remove(list.size() - 1);
        long endTime = System.nanoTime();
        return endTime - starTime;
    }

    double deleteLast(Map<Integer, String> map) {
        long starTime = System.nanoTime();
        map.remove(map.size() - 1);
        long endTime = System.nanoTime();
        return endTime - starTime;
    }

    double deleteFirst(Collection<Integer> list) {
        long starTime = System.nanoTime();
        list.remove(0);
        long endTime = System.nanoTime();
        return endTime - starTime;
    }

    double deleteFirst(Map<Integer, String> map) {
        long starTime = System.nanoTime();
        map.remove(0);
        long endTime = System.nanoTime();
        return endTime - starTime;
    }

}
