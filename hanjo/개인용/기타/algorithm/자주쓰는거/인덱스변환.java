package algorithm.자주쓰는거;

import java.util.*;

public class 인덱스변환 {

    public static void main(String[] args){
        int n = 3;
        int m = 4;
        
        // 2차원 인덱스 -> 1차원 인덱스
        List<Integer> list = new ArrayList<>();
        for(int x=0; x<n; x++){
            for(int y=0; y<m; y++){
                list.add(m*x + y);
            }
        }
        System.out.println(list);

        // 1차원 인덱스 -> 2차원 인덱스
        for(int num : list){
            int x = num / m;
            int y = num % m;
            System.out.println(x + " " + y);
        }

    }
    
}
