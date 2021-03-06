package string;

import java.util.*;

/**
 *
 819. Most Common Word
 Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

 Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.



 Example:

 Input:
 paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 banned = ["hit"]
 Output: "ball"
 Explanation:
 "hit" occurs 3 times, but it is a banned word.
 "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 Note that words in the paragraph are not case sensitive,
 that punctuation is ignored (even if adjacent to words, such as "ball,"),
 and that "hit" isn't the answer even though it occurs more because it is banned.


 Note:

 1 <= paragraph.length <= 1000.
 0 <= banned.length <= 100.
 1 <= banned[i].length <= 10.
 The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
 paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 There are no hyphens or hyphenated words.
 Words only consist of letters, never apostrophes or other punctuation symbols.
 */
public class Q819_MostCommonWord {

    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        for (String s: banned) {
            set.add(s.toLowerCase());
        }

        String[] paraArr = paragraph.toLowerCase().split("[!?',;. ]");
        Map<String, Integer> map = new HashMap<>();
        for (String para: paraArr) {
            if (para.equals("")) {
                continue;
            }
            if (!set.contains(para)) {
                int val = map.getOrDefault(para, 0)+1;
                map.put(para, val);
            }
        }

        int max = -1;
        String ans = "";
        System.out.println(map.keySet().size());
        for (String key: map.keySet()) {
            int val = map.get(key);
            if (val > max) {
                max = val;
                ans = key;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String paragraph = "Bob. hIt, baLl";
        String[] banned = new String[] {"hit", "bob"};
        Q819_MostCommonWord q = new Q819_MostCommonWord();
        String ans = q.mostCommonWord(paragraph, banned);
        System.out.println(ans);
    }
}
