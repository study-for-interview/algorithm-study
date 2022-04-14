package 탐욕법.G3_택배;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        int[][] boxes = new int[m][3];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            boxes[i][0] = Integer.parseInt(st.nextToken());
            boxes[i][1] = Integer.parseInt(st.nextToken());
            boxes[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, c, boxes));
    }

    public static class Box implements Comparable<Box>{
        public int from;
        public int to;
        public int weight;

        public Box(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Box o){
            int result = this.to - o.to;
            if(result == 0){
                result = this.from - o.from;
            }
            return result;
        }
    }

    public static int solution(int n, int c, int[][] boxes){
        // 배달정보 리스트 초기화 -> 도착 순서대로 정렬
        List<Box> boxList = new ArrayList<>();
        for(int[] box : boxes){
            boxList.add(new Box(box[0], box[1], box[2]));
        }
        Collections.sort(boxList);

        // 트럭에 실을 수 있는 마을별 박스 용량
        int[] truck = new int[n+1];
        Arrays.fill(truck, c);

        // 박스 순회
        int sum = 0;
        for(Box box : boxList){

            int minWeight = Integer.MAX_VALUE;
            for(int i=box.from; i<box.to; i++){
                minWeight = Math.min(minWeight, truck[i]);
            }

            int newWeight = box.weight;
            if(minWeight < newWeight){
                newWeight = minWeight;
            }
            sum += newWeight;

            for(int i=box.from; i<box.to; i++){
                truck[i] -= newWeight;
            }
        }
        return sum;
    }
    
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/8980
 * 날짜 : 220406
 * 성공여부 : 실패(구글링)
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : 
 * 메모리 : 21516 KB
 * 소요 시간 : 368 ms
 * ================================================================================
 * 
 * 좀 어려운 탐욕법 문제
 * 가장 탐욕스러운 방법을 잘 생각해내야함
 * 
 * 1. 박스를 도착지가 가까운 순서대로 정렬
 * 2. 트럭의 마을별 상태를 나타내는 배열 초기화
 * 3. 박스를 순회하면서 해당 배열을 업데이트하면서 가야함
 * 
 * 설명이 좀 어려워서... 참고한 링크 첨부
 * https://steady-coding.tistory.com/58
 * 
 */