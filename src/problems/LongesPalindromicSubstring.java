package problems;

import basics.Problem;
import basics.Tag;


/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 */
@Problem(value = "https://leetcode.com/problems/longest-palindromic-substring/", tags = {Tag.DP})
public class LongesPalindromicSubstring {


    static public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String tmp = expand(i, s, i); // odd case: aba
            if (tmp.length() > res.length()) res = tmp;
            tmp = expand(i, s, i + 1); // even case: abba
            if (tmp.length() > res.length()) res = tmp;
        }
        return res;
    }

    static String expand(int l, String s, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }

}
