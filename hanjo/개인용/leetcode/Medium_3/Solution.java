package org.prgrms.kdt;

import java.util.ArrayList;

public class Solution {
    public static int lengthOfLongestSubstring(String s) {

        int strLen = s.length();
        String[] strArr = s.split("");
        ArrayList<String> subStringList = new ArrayList<>();

        for (int i = 0; i < strLen; i++) {
            String tempSubStr = strArr[i];
            for (int j = i + 1; j < strLen; j++) {
                // 증복발견시
                if (tempSubStr.contains(strArr[j])){
                    break;
                }
                tempSubStr += strArr[j];
            }
            subStringList.add(tempSubStr);
        }

        int max = 0;
        for (String subString : subStringList) {
            if (subString.length() > max) {
                max = subString.length();
            }
        }

        return max;
    }

    public static void main(String[] args) {

        String s = " ";
        System.out.println(lengthOfLongestSubstring(s));

    }
}
