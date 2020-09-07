package dynamic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1048. Longest String Chain
 *
 Given a list of words, each word consists of English lowercase letters.

 Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

 A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

 Return the longest possible length of a word chain with words chosen from the given list of words.



 Example 1:

 Input: ["a","b","ba","bca","bda","bdca"]
 Output: 4
 Explanation: one of the longest word chain is "a","ba","bda","bdca".


 Note:

 1 <= words.length <= 1000
 1 <= words[i].length <= 16
 words[i] only consists of English lowercase letters.
 */
public class Q1048_LongestStringChain {

    /**
     * limit time exceeded
     */
    private int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b)->a.length()-b.length());
        System.out.println(Arrays.toString(words));
        int[] dp = new int[words.length];
        int pos = 0;

        for (int i=1; i<words.length; i++) {
            for (int j=pos; j<i; j++) {
                if (words[j].length()+1 == words[i].length() && isMatch(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
//            if (dp[i] != 0) {
//                pos = i;
//            }
            System.out.println(Arrays.toString(dp));
        }
        System.out.println(Arrays.toString(dp));
        return dp[words.length-1]+1;
    }
    private boolean isMatch(String a, String b) {
        int pos1 = 0;
        int pos2 = 0;
        while (pos1 < a.length() && pos2 < b.length()) {
            if (a.charAt(pos1) == b.charAt(pos2)) {
                pos1++;
                pos2++;
            } else if (pos1+1 < a.length() && a.charAt(pos1+1) == b.charAt(pos2)){
                pos1 += 2;
                pos2++;
            } else if (pos2+1 < b.length() && a.charAt(pos1) == b.charAt(pos2+1)) {
                pos1++;
                pos2 += 2;
            }
        }
        if (pos1 == a.length() && pos2 == b.length()) {
            System.out.println(a+" "+b+" true");
            return true;
        }
        return false;
    }

    private int longestStrChainDpOpt(String[] words) {
        Arrays.sort(words, (a, b)->a.length()-b.length());
        System.out.println(Arrays.toString(words));
        Map<String, Integer> notes = new HashMap<>();
        int ans = 0;  // maxChain
        for (String word: words) {
            if (!notes.containsKey(word)) {
                notes.put(word, 1);
            }
            for (int i=0; i<word.length(); i++) {
                String newWord = word.substring(0, i) +
                        word.substring(i+1);
                if (notes.containsKey(newWord)) {
                    notes.put(word, notes.get(newWord)+1);
                }
            }
            ans = Math.max(ans, notes.get(word));
        }
        return ans;
    }

    public static void main(String[] args) {
//        String a = "ab";
//        String b = "acb";
//        String[] words = new String[]{a, b};

        // String[] words = new String[]{"a","b","ba","bca","bda","bdca"};

        String[] words = new String[]{"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"};

        Q1048_LongestStringChain q1048_longestStringChain = new Q1048_LongestStringChain();

//        int ans = q1048_longestStringChain.longestStrChain(words);
//        System.out.println("dp ans:"+ans);

        int ans1 = q1048_longestStringChain.longestStrChainDpOpt(words);
        System.out.println("dp opt ans1:"+ans1);
    }
}
