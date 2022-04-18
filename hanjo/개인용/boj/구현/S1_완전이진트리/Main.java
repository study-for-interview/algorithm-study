package 구현.S1_완전이진트리;

import java.io.*;
import java.util.*;

public class Main {

    public static int[] tree;
    public static StringBuilder[] levelTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());   // 트리 높이
        levelTree = new StringBuilder[k];
        for(int i=0; i<k; i++){
            levelTree[i] = new StringBuilder();
        }

        String[] line = br.readLine().split(" ");
        tree = new int[line.length];
        for(int i=0; i<line.length; i++){
            tree[i] = Integer.parseInt(line[i]);
        }

        // solution
        reculsive(0, tree.length-1, 0);
        for(StringBuilder nums : levelTree){
            System.out.println(nums.toString());
        }
    }

    // 전위순회
    public static void reculsive(int left, int right, int level){
        if(left == right){
            levelTree[level].append(tree[left]).append(" ");
            return;
        }
        int mid = (left+right)/2;
        levelTree[level].append(tree[mid]).append(" ");
        reculsive(left, mid-1, level+1);
        reculsive(mid+1, right, level+1);
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/9934
 * 날짜 : 220318
 * 성공여부 : 실패 (구글링함)
 * 풀이시간 : 1h
 * 
 * 시간복잡도 : 
 * 메모리 : 14352 KB
 * 소요 시간 : 128 ms
 * ================================================================================
 * 
 * 포화이진트리의 특징을 사용하고 + 전위순회(재귀)를 활용하는? 문제
 * 
 * 일단 문제에서 완전이진트리라고 써놨는데 사실상 포화이진트리라고 부르는게 맞을 것 같음
 * 2^k-1 조건 없었으면 완전이진트리인데, 그랬으면 더 어려웠을 것 같다
 * 
 * 1. 일단 포화이진트리를 중위순회한 결과 배열이 주어짐
 * 2. 그럼 이 배열을 보고 트리의 구조를 알아내야 하는데.. 포화이진트리라서 배열 중간값이 해당 레벨의 루트값이 됨
 * 3. 즉 중간값을 기준으로 배열 반띵하면 좌 우 트리로 쪼갤 수 있음.
 * 4. 그럼 또 좌 트리에서 쪼개고 해당 레벨 루트넣고... 우 트리에서 쪼개고.. 무한반복
 * 5. 이렇게 재귀 돌려서 레벨당 등장하는 루트들을 저장해주면 끝
 * 
 */