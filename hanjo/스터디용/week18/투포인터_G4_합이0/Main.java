package week18.투포인터_G4_합이0;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] a = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(new Solution().solution(a));
    }

    public static class Solution {
    
        public long solution(int[] a){
            int n = a.length;
            Arrays.sort(a);

            long count = 0;

            for(int i=0; i<n; i++){
                int left = i+1;
                int right = n-1;
                
                while(left < right){
                    int sum = a[i] + a[left] + a[right];

                    if(sum == 0){
                        if(a[left] == a[right]){
                            int x = right - left + 1;
                            count += x*(x-1)/2;
                            break;
                        }

                        long countL = 1;
                        long countR = 1;
                        while(left+1 < right && a[left] == a[left+1]){
                            countL++;
                            left++;
                        }
                        while(left < right-1 && a[right] == a[right-1]){
                            countR++;
                            right--;
                        }
                        
                        count += countL * countR;
                    }

                    if (sum < 0){
                        left++;
                    }
                    else{
                        right--;
                    }
                }
            }

            return count;
        }
    
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/3151
 * 날짜 : 220512
 * 성공여부 : 실패 (구글링)
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : 
 * 메모리 : 16984 KB
 * 소요 시간 : 400 ms
 * ================================================================================
 * 
 * 좀 까다로운 투포인터 문제
 * 
 * 1. 배열을 정렬시켜놓고 하나씩 순회하며 숫자를 하나 고정
 * 2. 그리고 해당 숫자 뒤쪽 배열을 양쪽에서 투포인터로 검사 -> 고정숫자 + L + R = 0 되는 순간을 찾는것
 * 3. sum == 0 일때 추가 검사해줘야함
 *      -> L == R 일 경우엔 해당 길이만큼의 조합을 구해서 경우의수를 추가
 *      -> 이외의 경우엔 양쪽의 중복숫자 개수를 모두 구하고 곱해서 경우의수를 추가
 * 
 * count를 int 타입으로 주면 27%에서 틀렸다고 뜸. long 타입으로 해야함
 */