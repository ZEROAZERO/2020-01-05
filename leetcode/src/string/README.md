[TOC]



#### [520. Detect Capital](https://leetcode-cn.com/problems/detect-capital/)

Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

1. All letters in this word are capitals, like "USA".
2. All letters in this word are not capitals, like "leetcode".
3. Only the first letter in this word is capital, like "Google".

Otherwise, we define that this word doesn't use capitals in a right way.

 

**Example 1:**

```
Input: "USA"
Output: True
```

 

**Example 2:**

```
Input: "FlaG"
Output: False
```

 

**Note:** The input will be a non-empty word consisting of uppercase and lowercase latin letters.



- 思路1

  1 upCnt 记录string中大写字母的数量 Up代表大写字母

  2 

   2.1 if upCnt==1 and string[0]=Up return true //有一个大写字母且位开头

   2.2 if upCnt==len(string) return true //全是大写字母

   2.3 if upCnt==0 return true //无大小字母

   2.4 单字符 return true

  

- 思路1代码

  ```java
  public boolean detectCapitalUse(String word) {
      int len = word.length();
      int upCnt = 0;
      for (int i=0; i<word.length(); i++) {
          char ch = word.charAt(i);
          if (ch >= 'A' && ch <= 'Z') {
              upCnt++;
          }
      }
      // 全为大写字母 单个小写字母 全为小写字母
      if (upCnt == len || len==1 || upCnt==0) {
          return true;
      }
      if (upCnt==1 && word.charAt(0) >= 'A' && word.charAt(0) <= 'Z') {
          return true;
      }
      return false;
  }
  ```

-------







#### [521. Longest Uncommon Subsequence I](https://leetcode-cn.com/problems/longest-uncommon-subsequence-i/)

Given two strings `a` and `b`, find the length of the **longest uncommon subsequence** between them.

A **subsequence** of a string `s` is a string that can be obtained after deleting any number of characters from `s`. For example, `"abc"` is a subsequence of `"aebdc"` because you can delete the underlined characters in `"aebdc"` to get `"abc"`. Other subsequences of `"aebdc"` include `"aebdc"`, `"aeb"`, and `""` (empty string).

An **uncommon subsequence** between two strings is a string that is a **subsequence of one but not the other**.

Return *the length of the **longest uncommon subsequence** between `a` and `b`*. If the longest uncommon subsequence doesn't exist, return `-1`.

 

**Example 1:**

```
Input: a = "aba", b = "cdc"
Output: 3
Explanation: One longest uncommon subsequence is "aba" because "aba" is a subsequence of "aba" but not "cdc".
Note that "cdc" is also a longest uncommon subsequence.
```

**Example 2:**

```
Input: a = "aaa", b = "bbb"
Output: 3
Explanation: The longest uncommon subsequences are "aaa" and "bbb".
```

**Example 3:**

```
Input: a = "aaa", b = "aaa"
Output: -1
Explanation: Every subsequence of string a is also a subsequence of string b. Similarly, every subsequence of string b is also a subsequence of string a.
```

 

**Constraints:**

- `1 <= a.length, b.length <= 100`
- `a` and `b` consist of lower-case English letters.



- 思路1：

  1 如果两个字符串的长度不等 返回最大的字符串

  2 若长度相等，比较内容是否相等

  ​		2.1 若内容相等 return -1

  ​		2.2 若内容不等 return 任一个字符串

- 思路1代码：

  ```java
  public int findLUSLength(String a, String b) {
      if (a.equals(b)) {
          return -1;
      }
      return Math.max(a.length(), b.length());
  }
  ```



- 思路2：

  1 枚举两个字符串的所有的子序列集合，边枚举边记录每个出现的次数

  tips: 主要关注位运算对子序列的枚举

  如 "abc" 取[0, 2^n-1] 

  举例 111-abc, 110-bc 100-c 001-a... 

- 思路2代码：

  ```java
  public int findLUSLengthBruteForce(String a, String b) {
          HashMap< String, Integer > map = new HashMap < > ();
          for (String s: new String[] {a, b}) {
              // 若是abc 则1<<s.length()==111(2)
              for (int i = 0; i < (1 << s.length()); i++) {
                  String t = "";
                  // 向右移位的次数
                  for (int j = 0; j < s.length(); j++) {
                      // 这个地方是否有元素
                      if (((i >> j) & 1) != 0) { // 若有
                          t += s.charAt(j);
                      }
                  }
                  System.out.println(t);
                  if (map.containsKey(t))
                      map.put(t, map.get(t) + 1);
                  else
                      map.put(t, 1);
              }
          }
          int res = -1;
          for (String s: map.keySet()) {
              if (map.get(s) == 1)
                  res = Math.max(res, s.length());
          }
          return res;
      }
  ```


-------







#### [522. Longest Uncommon Subsequence II](https://leetcode-cn.com/problems/longest-uncommon-subsequence-ii/)

Given a list of strings, you need to find the longest uncommon subsequence among them. The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be **any** subsequence of the other strings.

A **subsequence** is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.

The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.

**Example 1:**

```
Input: "aba", "cdc", "eae"
Output: 3
```



**Note:**

1. All the given strings' lengths will not exceed 10.
2. The length of the given list will be in the range of [2, 50].

 

- 思路1：

  1. 位运算枚举所有子序列
  2. Map<String, Integer>, 查找value==1
  3. 比较所有 key 的长度，取最长

- 思路1代码：

  ```java
  public int findLUSLength(String[] strs) {
      // 1.位运算统计所有的子序列
      //  1.1 遍历 [0,len(strs)-1] 二进制 每个位置代表一个元素
      //  1.2 生成的子序列放入Map
      // 2.统计所有value为1的key
      Map<String, Integer> map = new HashMap<>();
      for (int i=0; i<strs.length; i++) {
          int len = strs[i].length();
          // 如 abc 101 表示 c0a 为了保证子序列的顺序
          for (int j=0; j<(1<<len); j++) {
              String tmpStr = "";
              for (int k=0; k<len; k++) {
                  if (((j>>k)&1) != 0) {
                      tmpStr += strs[i].charAt(k);
                  }
              }
              if (map.containsKey(tmpStr)) {
                  map.put(tmpStr, map.get(tmpStr)+1);
              } else {
                  map.put(tmpStr, 1);
              }
          }
      }
  
      int maxLen = -1;
      for (String key: map.keySet()) {
          if (map.get(key) == 1) {
              maxLen = Math.max(maxLen, key.length());
          }
      }
      return maxLen;
  }
  ```

  






-------------------------------


有道无术，术尚可学，有术无道，止于术。
-------------------------------