package 카카오.L2_파일명정렬;

import java.util.*;

public class Solution {

    public static String[] solution(String[] files) {
        // { origin : [head, number] }
        Map<String, String[]> parsingMap = new LinkedHashMap<>();
        for (String file : files) {
            int numStartIdx = 0;
            int numEndIdx = file.length();
            for (int i = 0; i < file.length(); i++) {
                if (Character.isDigit(file.charAt(i))) {
                    if (numStartIdx == 0) {
                        numStartIdx = i;
                    }
                } else {
                    if (numStartIdx != 0) {
                        numEndIdx = i;
                        break;
                    }
                }
            }
            parsingMap.put(file, new String[]{
                    file.substring(0, numStartIdx).toLowerCase(),
                    file.substring(numStartIdx, numEndIdx)
            });
        }

        // Map.Entry 하나씩 리스트에 저장 -> head 정렬 -> number 정렬
        List<Map.Entry<String, String[]>> entryList = new ArrayList<>(parsingMap.entrySet());
        Collections.sort(entryList, (v1, v2) -> {
                    int strCompared = v1.getValue()[0].compareTo(v2.getValue()[0]);
                    // head가 다른 경우 -> head 기준으로 정렬
                    if(strCompared != 0){
                        return strCompared;
                    }
                    // head가 같을 경우 -> number 기준으로 정렬
                    else{
                        int left = Integer.parseInt(v1.getValue()[1]);
                        int right = Integer.parseInt(v2.getValue()[1]);
                        if (left > right) return 1;
                        else if (left == right) return 0;
                        else return -1;
                    }
                }
        );

        String[] answer = new String[entryList.size()];
        for (int i = 0; i < entryList.size(); i++) {
            answer[i] = entryList.get(i).getKey();
        }
        return answer;
    }

    public static void main(String[] args) {
        List<String[]> test_case = new ArrayList<>();
        test_case.add(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});
        test_case.add(new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"});
        test_case.add(new String[]{"img000012345", "img1.png","img2","IMG02"});

        for (String[] files : test_case) {
            System.out.println(Arrays.toString(solution(files)));
        }
    }
}
