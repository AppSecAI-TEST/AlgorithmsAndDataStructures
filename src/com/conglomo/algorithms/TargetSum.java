package com.conglomo.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TargetSum {

    public static void main(String[] args) {
        int[] numbers = {1, 5, 5, 10, -11, 10};
        System.out.println(Arrays.toString(findTargetSum(numbers, 20)));
        System.out.println(Arrays.toString(findTargetSum(numbers, -10)));
        System.out.println(Arrays.toString(findTargetSum(numbers, 10)));
    }

    public static Integer[] findTargetSum(int[] numbers, int targetSum) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i; j < numbers.length; j++) {
                map.merge(numbers[i], new HashSet<>(Arrays.asList(i == j ? numbers[i] : numbers[i] + numbers[j])), (l1, l2) -> {
                    Set<Integer> integers = new HashSet<>(l1);
                    integers.addAll(l2);
                    return integers;
                });
            }
        }
        return map.keySet().stream()
                .filter(k -> map.get(k).contains(targetSum))
                .toArray(Integer[]::new);
    }
}
