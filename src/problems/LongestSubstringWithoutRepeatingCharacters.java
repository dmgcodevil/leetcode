package problems;

import basics.Problem;
import basics.Tag;

import java.util.HashMap;
import java.util.Map;

/*
Given a string, find the length of the longest substring without repeating characters.
 */
@Problem(value = "https://leetcode.com/problems/longest-substring-without-repeating-characters/",
        tags = {Tag.TwoPointers, Tag.HashMap})
public class LongestSubstringWithoutRepeatingCharacters {

    static public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        // where [i, j) (left-closed, right-open)
        // map contains position of j for the current window [i, j)
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}
