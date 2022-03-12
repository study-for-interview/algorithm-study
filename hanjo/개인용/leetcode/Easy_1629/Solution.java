// https://leetcode.com/problems/slowest-key/

package leetcode.Easy_1629;

import java.util.*;

class Solution {
    public static char slowestKey(int[] releaseTimes, String keysPressed) {
        Map<Integer, Integer> durationMap = new HashMap<>();
        durationMap.put(0, releaseTimes[0]);
        for (int i = 1; i < releaseTimes.length; i++) {
            durationMap.put(i, releaseTimes[i] - releaseTimes[i - 1]);
        }

        Integer maxDuration = Collections.max(durationMap.values());
        char[] charKeys = keysPressed.toCharArray();
        List<Character> charList = new ArrayList<>();
        for (Map.Entry duration : durationMap.entrySet()) {
            if (maxDuration == duration.getValue()) {
                charList.add(charKeys[(int)duration.getKey()]);
            }
        }
        return Collections.max(charList);
    }

    public static void main(String[] args) {
        int[] releaseTimes = {12, 23, 36, 46, 62};
        String keysPressed = "spuda";
        System.out.println(slowestKey(releaseTimes, keysPressed));

        int[] releaseTimes2 = {9, 29, 49, 50};
        keysPressed = "cbcd";
        System.out.println(slowestKey(releaseTimes2, keysPressed));

    }
}