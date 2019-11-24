package problems;

import basics.Problem;
import basics.Tag;

import java.util.*;


/*

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s
can be segmented into a space-separated sequence of one or more dictionary words.

 Solutions:
 wordBreak1 - recursion + memo
 wordBreak2 - dp

 */
@Problem(value = "https://leetcode.com/problems/word-break/", tags = {Tag.DP})
public class WordBreak {


    public static void main(String[] args) {
        System.out.println(wordBreak2("applepenapple", new ArrayList<>(Arrays.asList("apple", "pen")))); // true
    }

    static public boolean wordBreak1(String s, List<String> wordDict) {
        return wordBreak1(s, wordDict, new HashMap<>());
    }

    static public boolean wordBreak1(String s, List<String> wordDict, HashMap<String, Boolean> memo) {
        if (s.isEmpty()) return true;
        if (memo.containsKey(s)) return memo.get(s);

        boolean ans = false;

        for (int i = 0; i < wordDict.size(); i++) {
            String word = wordDict.get(i);
            int idx = s.indexOf(word);
            if (idx != -1) {
                if (wordBreak1(s.substring(0, idx), wordDict, memo) &&
                        wordBreak1(s.substring(idx + word.length()), wordDict, memo)) {
                    ans = true;
                    break;
                }
            }
        }

        memo.put(s, ans);
        return ans;
    }

    public static boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}
