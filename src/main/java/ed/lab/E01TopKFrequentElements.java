package ed.lab;

import java.util.*;

public class E01TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
       HashMap<Integer, Integer> map = new HashMap<>();
       for (int num : nums) {
           map.merge(num, 1, Integer::sum);
       }

       Queue<Map.Entry<Integer, Integer>> q = new PriorityQueue<>((a, b) -> {
           return a.getValue() - b.getValue() ;
       });

        for (Map.Entry<Integer, Integer> i : map.entrySet()) {
           q.offer(i);
           if (q.size() > k) {
               q.poll();
           }
        }

        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = q.poll().getKey();
        }

        return res;
    }
}
