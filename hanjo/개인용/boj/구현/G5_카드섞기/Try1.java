package 구현.G5_카드섞기;

import java.io.*;
import java.util.*;


public class Try1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] cards = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            cards[i] = Integer.parseInt(st.nextToken());
        }

        solution(cards);
    }

    public static void solution(int[] cards){

        int n = cards.length;
        int maxK = (int)(Math.log(n) / Math.log(2));

        // StringBuilder sb = new StringBuilder();

        for(int i=1; i<=maxK; i++){
            for(int j=1; j<=maxK; j++){
                if(i!=j){
                    if(i!=1 && j!=2){
                        continue;
                    }
                    LinkedList<Integer> temp = copy(cards);
                    reverse(temp, i);
                    reverse(temp, j);
                    if(isSorted(temp)){
                        System.out.println(i + " " + j);
                    }
                }
            }
        }
    }

    public static LinkedList<Integer> copy(int[] arr){
        LinkedList<Integer> list = new LinkedList<>();
        for(int num : arr){
            list.add(num);
        }
        return list;
    }

    public static void reverse(LinkedList<Integer> temp, int k){
        for(int i=0; i<=k; i++){
            System.out.println(k);
            System.out.println(temp);

            int splitSize = (int)Math.pow(2, i);
            int insertIdx = (int)Math.pow(2, i+1);
            insertIdx = insertIdx >= temp.size() ? temp.size() - splitSize : insertIdx - splitSize;

            ArrayList<Integer> toBack = new ArrayList<>();
            for(int j=0; j < splitSize; j++){
                toBack.add(temp.removeFirst());
            }

            System.out.println(temp);
            System.out.println(toBack);


            for(int j=0; j < toBack.size(); j++){
                temp.add(insertIdx+j, toBack.get(j));
            }

            System.out.println(temp);
            System.out.println();

        }
    }

    public static boolean isSorted(LinkedList<Integer> temp){
        for(int i=0; i<temp.size(); i++){
            if(i+1 != temp.get(i)){
                return false;
            }
        }
        return true;
    }
}


/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/21315
 * 성공여부 : 포기
 * 풀이시간 : 
 * 
 * 시간복잡도 : 
 * 메모리 : KB
 * 소요 시간 : ms
 * ================================================================================
 * 
 * 구현이 매우 복잡하고... 반례를 찾지 못해서 잠시 묻어둔다.
 * 
 * 나는 주어진 카드를 역방향 시뮬레이션해서 정렬된 카드로 만드는 과정을 구현했는데
 * 정방향으로 구현하는게 훨씬 편할것이다..
 * 
 * 배열 요소 비교할때 .equals 써도됨
 * 
 */ 