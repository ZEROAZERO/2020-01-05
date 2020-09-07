package dynamic;

import java.util.*;

/**
 * 139. Word Break 单词拆分
 *
 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

 Note:

 The same word in the dictionary may be reused multiple times in the segmentation.
 You may assume the dictionary does not contain duplicate words.
 Example 1:

 Input: s = "leetcode", wordDict = ["leet", "code"]
 Output: true
 Explanation: Return true because "leetcode" can be segmented as "leet code".
 Example 2:

 Input: s = "applepenapple", wordDict = ["apple", "pen"]
 Output: true
 Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 Note that you are allowed to reuse a dictionary word.
 Example 3:

 Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output: false
 */
public class Q139_WordBreak {

    /**
     * 1.remember the solution is the same of any part and correlative, forever.
     * correlative adj.关联的
     *
     * divide and conquer
     *      *  dp[0,len) =  dp[0,1) && wordDict.contains(s[i,len))
     *      *             || dp[0,2) && wordDict.contains(s[2,len))
     *      *             || dp[0,3) && wordDict.contains(s[3,len))
     *      *             ...
     *      *             || dp[0,len - 1) && wordDict.contains(s[len - 1,len))
     * timeout
     */
    private boolean wordBreakDivide(String s, List<String> wordDict) {
        return divideHelper(s, new HashSet<>(wordDict), 0);
    }

    private boolean divideHelper(String s, Set<String> wordDict, int stat) {
        if (stat == s.length()) {
            return true;
        }
        for (int len=1; len<=s.length()-stat; len++) {
            String word = s.substring(stat, stat+len);
            if (wordDict.contains(word) && divideHelper(s, wordDict, stat+len)) {
                return true;
            }
            /* 错误 如toe {toe， to}
            第一次到达 to 时，包含 to， 那么进入 return，不会再次扩展
            if (wordDict.contains(word)) {
                return self(s, wordDict, stat+len);
            }
            */
        }
        return false;
    }

    /**
     * divide conquer optimization
     * recording search
     */
    private boolean wordBreakDivideOpt(String s, List<String> wordDict) {
        Map<String, Boolean> record = new HashMap<>();
        boolean ans = wordBreakHelper(s, new HashSet<>(wordDict), record);
        record.keySet().stream().forEach(key-> System.out.println(key));
        return ans;
    }

    private boolean wordBreakHelper(String s, HashSet<String> wordDict, Map<String, Boolean> record) {
        if (s.length() == 0) {
            return true;
        }
        if (record.containsKey(s)) {
            return record.get(s);
        }
        for (int i = 0; i < s.length(); i++) {
            if (wordDict.contains(s.substring(i, s.length())) && wordBreakHelper(s.substring(0, i), wordDict, record)) {
                record.put(s, true);
                return true;
            }
        }
        record.put(s, false);
        return false;
    }


    /**
     * 2.从 wordDict 入手，组合各种可能结果比较
     *  重视：有返回值的递归，一定要接收返回值
     *   分清楚求解，
     *  此问题如果遇到 true 需要返回结果
     *  timeout
     */
    private boolean wordBreakRecursion(String s, List<String> wordDict) {
        return recursionHelper(s, wordDict, "");
    }

    private boolean recursionHelper(String s, List<String> wordDict, String cur) {
        System.out.println(cur);
        if (s.equals(cur)) {
            return true;
        }
        if (cur.length() >= s.length()) {
            return false;
        }

        for (String str: wordDict) {
            if (recursionHelper(s, wordDict, cur+str)) {
                return true;
            }
        }
        return false;
        /* 在干嘛？？为了得到最终结果结束
        boolean ans = false;
        for (String str: wordDict) {
            cur += str;
            ans = self2(s, wordDict, cur);
            cur = cur.substring(0, cur.length()-str.length());
        }
        return ans;
        */
    }


    /**
     * 3.recording search
     * timeout
     */
    private boolean wordBreakRecordSearch(String s, List<String> wordDict) {
        boolean[] record = new boolean[s.length()];
        boolean ans = selfRecord(s, new HashSet<>(wordDict), 0, record);
        System.out.println(Arrays.toString(record));
        return ans;
    }

    private boolean selfRecord(String s, Set<String> wordDict, int stat, boolean[] record) {
        if (stat == s.length()) {
            return true;
        }
        if (record[stat]) {
            return true;
        }
        for (int len=1; len<=s.length()-stat; len++) {
            String word = s.substring(stat, stat+len);
            if (wordDict.contains(word) && selfRecord(s, wordDict, stat+len, record)) {
                return record[stat] = true; // 表示以 stat 位置为起点的整串是都能够和 wordDict 匹配
            }
        }
        return false;
    }

    /**
     * 4.暴力优化
     *  -1.判断 s 与 wordDict 是否存在不一致字符
     *  -2.判断 s 与 cur 从0位置开始是否有不一致字符
     *  -3.记忆化搜索
     */
    private boolean wordBreakForceOpt(String s, List<String> wordDict) {
        // -1.判断 s 与 wordDict 是否存在不一致字符
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < wordDict.size(); i++) {
            String t = wordDict.get(i);
            for (int j = 0; j < t.length(); j++) {
                set.add(t.charAt(j));
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(s.charAt(i))) {
                return false;
            }
        }
        Map<String, Boolean> record = new HashMap<>();
        boolean ans = selfForceOpt(s, wordDict, record, "");
        record.keySet().stream().forEach(key-> System.out.println(key));
        return ans;
    }

    private boolean selfForceOpt(String s, List<String> wordDict, Map<String, Boolean> record, String cur) {
        if (cur.length() > s.length()) {
            return false;
        }
        // 之前是否存过
        if(record.containsKey(cur)){
            return record.get(cur);
        }
        // -2.判断 s 与 cur 从0位置开始是否有不一致字符 逐个字符比较
        for (int i = 0; i < cur.length(); i++) {
            if (s.charAt(i) != cur.charAt(i)) {
                return false;
            }
        }
        if (s.length() == cur.length()) {
            return true;
        }

        for (String str: wordDict) {
            String tmp = cur + str;
            if (selfForceOpt(s, wordDict, record, tmp)) {
                System.out.println("key:"+cur+" tmp:"+tmp);
                record.put(cur, true); // 记录返回 true 之前的字符串，如applepenapple，key=applepen
                return true; // 表示以 stat 位置为起点的整串是都能够和 wordDict 匹配
            }
        }
        record.put(cur, false); // 记录匹配不到的字符串
        System.out.println("end:"+cur);
        return false;
    }


    /**
     * dp
     * state：dp[i] 代表从0到i的是否匹配成功 true表示匹配，false表示不匹配
     * 循环解师，i）截取len长度的字符串；ii）依次判断[0,len)，[1,len)，[2,len)->遍历
     */
    private boolean wordBreakDp(String s, List<String> wordDict) {
        int[] dp = new int[s.length()+1];
        dp[0] = 1; // 初始状态
        for (int len=1; len<=s.length(); len++) {
            for (int j=0; j<len; j++) {
                // dp[j]表示s[j+1, j+1+i]之前s[0, j]的状态
                if (dp[j]==1 && wordDict.contains(s.substring(j, len))) {
                    dp[len] = 1;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[s.length()] == 1;
    }

    public static void main(String[] args) {
        // leetcode  leet code
        // sandog  sand dog
        // applepenapple  apple pen
        // toe  toe to
        String s = "sandog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("sand");
        wordDict.add("dog");
        Q139_WordBreak q139_wordBreak = new Q139_WordBreak();
        boolean ans1 = q139_wordBreak.wordBreakDivide(s, wordDict);
        System.out.println("divide:"+ans1);
        boolean ans2 = q139_wordBreak.wordBreakRecordSearch(s, wordDict);
        System.out.println("record:"+ans2);
        boolean ans = q139_wordBreak.wordBreakDp(s, wordDict);
        System.out.println("dp:"+ans);
        boolean ans3 = q139_wordBreak.wordBreakRecursion(s, wordDict);
        System.out.println("recursion:"+ans3);
        boolean ans4 = q139_wordBreak.wordBreakForceOpt(s, wordDict);
        System.out.println("bf opt:"+ans4);
        boolean ans5 = q139_wordBreak.wordBreakDivideOpt(s, wordDict);
        System.out.println("divide opt:"+ans5);
    }
}
