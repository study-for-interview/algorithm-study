package 탐욕법.G4_단어수학;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];
        for(int i=0; i<n; i++){
            arr[i] = br.readLine();
        }

        System.out.println(solution(arr));
    }

    public static int solution(String[] arr){
        
        // 모든 알파벳의 우선순위(가중치) 구하기
        Map<String, Integer> valueMap = new HashMap<>();
        for(String str : arr){
            String[] splited = str.split("");
            int len = str.length()-1;

            for(int i=0; i<=len; i++){
                String key = splited[len-i];
                int value = (int)Math.pow(10, i);

                if(!valueMap.containsKey(key)){
                    valueMap.put(key, 0);
                }
                valueMap.replace(key, valueMap.get(key) + value);
            }
        }

        // 우선순위대로 정렬
        List<String> keys = valueMap.entrySet().stream()
            .sorted((e1, e2) -> e2.getValue() - e1.getValue())
            .map(e -> e.getKey())
            .collect(Collectors.toList());
        
        // 정렬된 알파벳에 숫자 매핑
        Map<String, Integer> nums = new HashMap<>();
        for(int i=0; i<keys.size(); i++){
            nums.put(keys.get(i), 9-i);
        }

        // 문자열 순회하면서 알파벳-숫자 매핑 후 계산
        int sum = 0;
        for(String str : arr){
            String[] splited = str.split("");
            int len = str.length()-1;
            for(int i=0; i<=len; i++){
                int num = nums.get(splited[len-i]);
                int value = (int)Math.pow(10, i);
                sum += num*value;
            }
        }
        return sum;
    }

}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/1339
 * 날짜 : 220401
 * 성공여부 : 성공
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : 
 * 메모리 : 14504 KB
 * 소요 시간 : 140 ms
 * ================================================================================
 * 
 * 약간 복잡한 탐욕법 문제
 * 아이디어 보다는 어떻게 구현할지가 더 중요했음.
 * 
 * 아이디어를 떠올리는것은 어렵지 않음
 * A B
 * B A
 * C A
 * 이렇게 주어졌을때 첫번째 자릿수만 보면 A B C 모두 9의 후보가 된다
 * 근데 두번째 자릿수를 보면 A가 더 많이 등장하므로 9의 후보로 A가 선택되어야함
 * 
 * 고로 특정 알파벳의 가중치를 구하면된다
 * -> 특정 알파벳을 정하고
 * -> 문자열을 모두 탐색하여 알파벳이 등장하는 위치에 가중치를 주고 싹 다 더하기
 *      ex) 100의 자리에서 등장하는 경우 가중치는 100
 * 
 * 가중치를 모두 구했다면 가장 큰 가중치의 알파벳부터 9, 8, 7.... 숫자를 부여하면 끝
 * 
 */