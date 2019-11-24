package problems;

import basics.Problem;
import basics.Tag;

import java.util.*;

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
@Problem(value = "https://leetcode.com/problems/two-sum", tags = {Tag.HashMap})
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int delta = target - nums[i];
            if (map.containsKey(delta)) return new int[]{map.get(delta), i};
            map.put(nums[i], i);
        }
        throw new RuntimeException("no solution");
    }
}
