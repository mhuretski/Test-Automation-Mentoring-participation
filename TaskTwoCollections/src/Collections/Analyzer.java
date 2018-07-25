package Collections;

import java.util.Collection;
import java.util.List;
import java.util.Map;

class Analyzer extends Generator {

    void timeToCreate(Collection<Integer> l1, Collection<Integer> l2, int amount) {
        Double t1 = fill(l1, amount);
        Double t2 = fill(l2, amount);
        String description = "%s is faster than %s by %.2f%% when adding elements to the end.\n";
        getResult(t1, t2, l1, l2, description);
    }

    void timeToCreate(Map<Integer, String> m1, Map<Integer, String> m2, int amount) {
        Double t1 = fill(m1, amount);
        Double t2 = fill(m2, amount);
        String description = "%s is faster than %s by %.2f%% when adding elements to the end.\n";
        getResult(t1, t2, m1, m2, description);
    }

    void timeToSearchForFirstElement(Collection<Integer> l1, Collection<Integer> l2) {
        Double t1 = searchForFirstElement(l1);
        Double t2 = searchForFirstElement(l2);
        String description = "%s is faster than %s by %.2f%% when searching for the first added element.\n";
        getResult(t1, t2, l1, l2, description);
    }

    void timeToSearchForFirstElement(Map<Integer, String> m1, Map<Integer, String> m2) {
        Double t1 = searchForFirstElement(m1);
        Double t2 = searchForFirstElement(m2);
        String description = "%s is faster than %s by %.2f%% when searching for the first added element.\n";
        getResult(t1, t2, m1, m2, description);
    }

    void timeToSearchForLastElement(Collection<Integer> l1, Collection<Integer> l2) {
        Double t1 = searchForLastElement(l1);
        Double t2 = searchForLastElement(l2);
        String description = "%s is faster than %s by %.2f%% when searching for the last added element.\n";
        getResult(t1, t2, l1, l2, description);
    }

    void timeToSearchForLastElement(Map<Integer, String> m1, Map<Integer, String> m2) {
        Double t1 = searchForLastElement(m1);
        Double t2 = searchForLastElement(m2);
        String description = "%s is faster than %s by %.2f%% when searching for the last added element.\n";
        getResult(t1, t2, m1, m2, description);
    }

    void timeToAddElementToBeginning(List<Integer> l1, List<Integer> l2) {
        Double t1 = addElementToStart(l1);
        Double t2 = addElementToStart(l2);
        String description = "%s is faster than %s by %.2f%% when element is added to the beginning.\n";
        getResult(t1, t2, l1, l2, description);
    }

    void timeToDeleteLastElement(Map<Integer, String> m1, Map<Integer, String> m2) {
        Double t1 = deleteLast(m1);
        Double t2 = deleteLast(m2);
        String description = "%s is faster than %s by %.2f%% when the last added element is deleted.\n";
        getResult(t1, t2, m1, m2, description);
    }

    void timeToDeleteLastElement(Collection<Integer> l1, Collection<Integer> l2) {
        Double t1 = deleteLast(l1);
        Double t2 = deleteLast(l2);
        String description = "%s is faster than %s by %.2f%% when the last added element is deleted.\n";
        getResult(t1, t2, l1, l2, description);
    }

    void timeToDeleteFirstElement(Map<Integer, String> m1, Map<Integer, String> m2) {
        Double t1 = deleteFirst(m1);
        Double t2 = deleteFirst(m2);
        String description = "%s is faster than %s by %.2f%% when the first added element is deleted.\n";
        getResult(t1, t2, m1, m2, description);
    }

    void timeToDeleteFirstElement(Collection<Integer> l1, Collection<Integer> l2) {
        Double t1 = deleteFirst(l1);
        Double t2 = deleteFirst(l2);
        String description = "%s is faster than %s by %.2f%% when the first added element is deleted.\n";
        getResult(t1, t2, l1, l2, description);
    }

    private void getResult(Double t1, Double t2, Object l1, Object l2, String description) {
        if (t1 > t2) {
            Double result = (t1 / t2 - 1) * 100;
            System.out.format(description,
                    l2.getClass().getSimpleName(),
                    l1.getClass().getSimpleName(),
                    result);
        } else {
            Double result = (t2 / t1 - 1) * 100;
            System.out.format(description,
                    l1.getClass().getSimpleName(),
                    l2.getClass().getSimpleName(),
                    result);
        }
    }
}
