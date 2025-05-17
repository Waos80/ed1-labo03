package ed.lab;
import java.util.*;

public class E02AutocompleteSystem {
    private HashMap<String, Integer> map;
    private StringBuilder builder;

    private List<String> getMostFrequent(String str) {
        ArrayList<Map.Entry<String, Integer>> entry_matches = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getKey().startsWith(str)) {
                entry_matches.add(entry);
            }
        }

        Queue<Map.Entry<String, Integer>> heap = new PriorityQueue<>((e1, e2) -> {
            if (e1.getValue().equals(e2.getValue())) {
                return e1.getKey().compareTo(e2.getKey());
            }
            return e2.getValue().compareTo(e1.getValue());
        });

        heap.addAll(entry_matches);

        if (heap.isEmpty()) {
            return List.of();
        }

        ArrayList<String> most_freq = new ArrayList<>();
        int i = 0;
        while (i < 3 && !heap.isEmpty()) {
            most_freq.add(heap.poll().getKey());
            i++;
        }

        return most_freq;
    }

    public E02AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.builder = new StringBuilder();
        for (int i = 0; i < sentences.length; i++) {
            this.map.put(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            map.merge(builder.toString(), 1, Integer::sum);
            builder = new StringBuilder();
            return List.of();
        }

        builder.append(c);
        return getMostFrequent(builder.toString());
    }
}
