package string;

import util.GeneratePrimeNumUtil;

import java.util.*;

/**
 49. Group Anagrams

 Given an array of strings, group anagrams together.

 Example:

 Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 Output:
 [
 ["ate","eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 Note:

 All inputs will be in lowercase.
 The order of your output does not matter.
 */
public class Q49_GroupAnagrams {
    /**
     * 1.遍历，HashMap key为字母从a~z排序的
     */
    private List<List<String>> groupAnagramsHashMap(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> res = new HashMap<>();
        for (String str: strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String key =  String.valueOf(chs);
            if (!res.containsKey(key)) {
                res.put(key, new ArrayList<>());
            }
            res.get(key).add(str);
        }
        return new ArrayList<>(res.values());
    }

    /**
     * 2.构建hash值 如aab， hash值 #2#1#0#...#
     */
    private List<List<String>> groupAnagramsHash(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> res = new HashMap<>();
        int[] count = new int[26];
        for (String str: strs) {
            //每次重置 count
            Arrays.fill(count, 0);
            for (int i=0; i<str.length(); i++) {
                count[str.charAt(i)-'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<26; i++) {
                sb.append("#");
                sb.append(count[i]);
            }
            String key = sb.toString();
            System.out.println(str+" "+key);
            if (!res.containsKey(key)) {
                res.put(key, new ArrayList<>());
            }
            res.get(key).add(str);
        }
        return new ArrayList<>(res.values());
    }

    /**
     * 质数相乘的唯一性
     * prime数组，如：aab，hash：prime[0]*prime[0]*prime[1]
     */
    private List<List<String>> groupAnagramsPrime(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<Integer, List<String>> res = new HashMap<>();
        int[] count;
        count = GeneratePrimeNumUtil.generatePrimeArr(26);
        for (String str: strs) {
            int key = 1;
            for (int i=0; i<str.length(); i++) {
                key *= count[str.charAt(i)-'a'];
            }
            System.out.println(str+" "+key);
            if (!res.containsKey(key)) {
                res.put(key, new ArrayList<>());
            }
            res.get(key).add(str);
        }
        return new ArrayList<>(res.values());
    }


    public static void main(String[] args) {
        new Q49_GroupAnagrams().groupAnagramsPrime(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
    }

}
