package test;

import java.util.*;

public class Test3 {
    
    public static void main(String args[]){
        ArrayList<List<Integer>> graph = new ArrayList<>(0);
        System.out.println(graph);

        ArrayList<Integer> maxOfDepth = new ArrayList<>(6);
        for(int i=1; i<=5; i++){
            maxOfDepth.set(i, 0);
        }
        System.out.println(maxOfDepth);

    }
}

// 초기화 테스트
