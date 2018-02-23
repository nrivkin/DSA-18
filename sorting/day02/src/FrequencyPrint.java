import java.util.*;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        String[] words = s.split("\\s+");
        SortByValue sortByValue = new SortByValue();
        HashMap<String, Integer> histogram = new HashMap<>();
        for(String word : words) histogram.put(word, histogram.getOrDefault(word, 0) + 1);
        ArrayList<Map.Entry<String, Integer>> sortedList = new ArrayList<>(histogram.entrySet());
        sortedList.sort(sortByValue);
        StringBuilder biulder = new StringBuilder();
        for (Map.Entry<String, Integer> e : sortedList) {
            for (int i = 0; i < e.getValue(); i++) {
                biulder.append(e.getKey()).append(' ');
            }
        }
        return biulder.toString();
    }
}

class SortByValue implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        if (o1.getValue() > o2.getValue()){
            return 1;
        } else if (o1.getValue() < o2.getValue()) {
            return -1;
        } else{
            return 0;
        }
    }
}
