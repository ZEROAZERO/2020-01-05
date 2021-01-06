[TOC]

---



#### [316. Remove Duplicate Letters](https://leetcode-cn.com/problems/remove-duplicate-letters/)

难度中等427

Given a string `s`, remove duplicate letters so that every letter appears once and only once. You must make sure your result is **the smallest in lexicographical order** among all possible results.

**Note:** This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/

 

**Example 1:**

```
Input: s = "bcabc"
Output: "abc"
```

**Example 2:**

```
Input: s = "cbacdcbc"
Output: "acdb"
```

 

**Constraints:**

- `1 <= s.length <= 104`
- `s` consists of lowercase English letters.



**解题**

- 思路1

  1. set 存储元素是否被用过
  2. stack 存储最终结果
  3. map 存储每个元素的最后出现的位置
  4. 遍历字符串 for i in [0, len(string)]
     1. 若某个元素没被使用过，则进行 4.2
     2. while 判断 栈不为空 and 栈顶元素>当前元素string[i] and 栈顶元素在后续存在：则弹出栈顶元素，从set移除该栈顶元素
     3. 完成while操作后，当前元素加入集合，加入栈中
  5. return 栈从开始遍历到尾部形成字符串结果

- 思路1代码

  ```java
  public String removeDuplicateLetters(String s) {
      // record the ending result
      Stack<Character> stack = new Stack<>();
      // record the condition that the element is exist
      Set<Character> seen = new HashSet<>();
      // record the last index of a element
      HashMap<Character, Integer> lastOccur = new HashMap<>();
      for (int i=0; i<s.length(); i++) {
          lastOccur.put(s.charAt(i), i);
      }
  
      for (int i=0; i<s.length(); i++) {
          char ch = s.charAt(i);
          if (!seen.contains(ch)) {
              // if B is exist in the seen and the last index is greater than i-index, the B element is ejected
              while (!stack.isEmpty() && ch<stack.peek() && lastOccur.get(stack.peek())>i) {
                  seen.remove(stack.pop());
              }
              seen.add(ch);
              stack.push(ch);
          }
      }
  
      StringBuilder sb = new StringBuilder();
      for (Character ch: stack) {
          sb.append(ch);
      }
      return sb.toString();
  }
  ```

  



end

------





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



**解答**

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



- 思路2

  判断不符合的方式 return false

  return true;



-----







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



**解答**

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



----







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

 

**解答**

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




----







#### [557. Reverse Words in a String III](https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/)

难度简单260

Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

**Example 1:**

```
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
```



**Note:** In the string, each word is separated by single space and there will not be any extra space in the string.



**解答**

- 思路1：

  1. 初始化begin = end = nextBegin = 0

  2. while begin < len(s):
     1. end++ 直到找到空格字符
     2. nextBegin = end + 1 // 下一次开始的位置
     3. 反转字符（1）swap方式 （2）新建字符串
     4. begin = end = nextBegin
  3. return 结果字符串

- 思路1代码：

  ```java
  public String reverseWords(String s) {
      /**
           *
           1.begin=end=nextBegin=0
           2.until begin<len(s):
              until [end++] == whitespace
              nextBegin = end + 1
              反转一小part字符串
              begin=end=nextBegin
           */
      int end = 0;
      int begin = 0;
      int nextBegin = 0;
      char whitespace = ' ';
      int len = s.length();
      StringBuilder sb = new StringBuilder();
      while (begin<len) {
          while (end<len && s.charAt(end)!=whitespace) {
              end++; //最后位置是end>len出的循环
          }
          nextBegin = end + 1; // 新开始的位置
          end--;
          while (end >= begin) {
              sb.append(s.charAt(end--));
          }
          if (nextBegin < len) {
              sb.append(' ');
          }
          end = nextBegin;
          begin = nextBegin;
      }
      return sb.toString();
  }
  ```

end

----



#### [696. Count Binary Substrings](https://leetcode-cn.com/problems/count-binary-substrings/)

难度简单321

Give a string `s`, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.

**Example 1:**

```
Input: "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
Notice that some of these substrings repeat and are counted the number of times they occur.
Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
```



**Example 2:**

```
Input: "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
```



**Note:**

`s.length` will be between 1 and 50,000.

`s` will only consist of "0" or "1" characters.



**解答**

- 思路1

  1. 倒序统计从某个位置起，该位置元素连续出现的次数（包括：自身）
  2. 若 00011 len1=3 len2=2，则出现符合条件数为2
  3. 下次起始位置，从begin+Math.min(len1, len2)开始

- 思路1代码

  ```java
  public int countBinarySubstrings(String s) {
      int len = s.length();
      Map<Integer, Integer> map = new HashMap<>();
      for (int i=len-1; i>=0; i--) {
          char ch = s.charAt(i);
          if (i+1 >= len) {
              map.put(i, 1);
              continue;
          }
          char nextCh = s.charAt(i+1);
          if (ch == nextCh) {
              map.put(i, map.get(i+1)+1);
          } else {
              map.put(i, 1);
          }
      }
      // map.keySet().forEach(key-> System.out.println(map.get(key)));
  
      int second = 0;
      int cnt = 0;
      while (second < len) {
          int len1 = map.get(second);
          if (len1+second < len) {
              second = len1 + second; // 下一个与前一个坐标字符不同的坐标
          } else {
              break;
          }
          int len2 = map.get(second);
          cnt += Math.min(len1, len2);
      }
      return cnt;
  }
  ```


end

---





#### [791. Custom Sort String](https://leetcode-cn.com/problems/custom-sort-string/)

**类似题目**：767

难度中等71

`S` and `T` are strings composed of lowercase letters. In `S`, no letter occurs more than once.

`S` was sorted in some custom order previously. We want to permute the characters of `T` so that they match the order that `S` was sorted. More specifically, if `x` occurs before `y` in `S`, then `x` should occur before `y` in the returned string.

Return any permutation of `T` (as a string) that satisfies this property.

```
Example :
Input: 
S = "cba"
T = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
```

 

**Note:**

- `S` has length at most `26`, and no character is repeated in `S`.
- `T` has length at most `200`.
- `S` and `T` consist of lowercase letters only.



**解答**

- 思路1

  1. alphabetCnt数组统计S与T的出现的字符数量，若是S，减减，若是T，加加

  2. 首先，拼接T中包含在S中出现所有字符
  3.  拼接剩余T中不在S中出现的字符

- 思路1具体代码

  ```java
  public String customSortString(String S, String T) {
      char[] tChs = T.toCharArray();
      char[] sChs = S.toCharArray();
      char[] ans = new char[tChs.length];
      int[] alphabetCnt = new int[26];
      int i = 0;  // 结果集位置
      for (char ch: sChs) {
          alphabetCnt[ch-'a']--;
      }
      for (char ch: tChs) {
          alphabetCnt[ch-'a']++;
      }
      // 填充S字符
      for (char ch: sChs) {
          while (alphabetCnt[ch-'a']-- >= 0) {
              ans[i++] = ch;
          }
      }
      // 填充S后的字符
      for (int j=0; j<alphabetCnt.length; j++) {
          while (alphabetCnt[j]-- > 0) {
              ans[i++] = (char)('a'+j);
          }
      }
      return String.valueOf(ans);
  }
  ```

end

----





#### [1003. Check If Word Is Valid After Substitutions](https://leetcode-cn.com/problems/check-if-word-is-valid-after-substitutions/)

难度中等44

Given a string `s`, determine if it is **valid**.

A string `s` is **valid** if, starting with an empty string `t = ""`, you can **transform** `t` **into** `s` after performing the following operation **any number of times**:

- Insert string `"abc"` into any position in `t`. More formally, `t` becomes `tleft + "abc" + tright`, where `t == tleft + tright`. Note that `tleft` and `tright` may be **empty**.

Return `true` *if* `s` *is a **valid** string, otherwise, return* `false`.

 

**Example 1:**

```
Input: s = "aabcbc"
Output: true
Explanation:
"" -> "abc" -> "aabcbc"
Thus, "aabcbc" is valid.
```

**Example 2:**

```
Input: s = "abcabcababcc"
Output: true
Explanation:
"" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
Thus, "abcabcababcc" is valid.
```

**Example 3:**

```
Input: s = "abccba"
Output: false
Explanation: It is impossible to get "abccba" using the operation.
```

**Example 4:**

```
Input: s = "cababc"
Output: false
Explanation: It is impossible to get "cababc" using the operation.
```

 

**Constraints:**

- `1 <= s.length <= 2 * 104`
- `s` consists of letters `'a'`, `'b'`, and `'c'`



**解答**

- 思路1：

  1. 数组模拟栈
  2. 若尺寸大于等于3，判断连续的三个元素是否为 "abc"
     1. 若是，则弹出三个元素（即：将尺寸size-=3）
     2. 若不是继续添加元素
  3. return size == 0

- 思路1代码

  ```java
  /**
       * 模拟栈
       */
  public boolean isValid(String s) {
      int sLen = s.length();
      // 存储元素从1开始
      char[] stack = new char[sLen+1];
      int size = 0;
      for (int i=0; i<sLen; i++) {
          stack[++size] = s.charAt(i);
          if (size >= 3 && stack[size]=='c' && stack[size-1]=='b' && stack[size-2]=='a') {
              size -= 3;
          }
      }
      return size == 0;
  }
  ```



end

---






-------------------------------


有道无术，术尚可学，有术无道，止于术。
-------------------------------