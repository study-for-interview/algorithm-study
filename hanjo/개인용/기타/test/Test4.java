package test;

import java.util.*;

public class Test4 {
    public static void main(String args[]){

        HashSet<HashSet<Integer>> temp = new HashSet<>();
        // Set.of(1);
        var hm1 = new HashSet<Integer>();
        hm1.add(1);
        var hm2 = new HashSet<Integer>();
        hm2.add(1);
        temp.add(hm1);
        temp.add(hm2);
        System.out.println(temp.size());


        HashSet<ArrayList<Integer>> temp2 = new HashSet<>();
        temp2.add(new ArrayList<>(List.of(1)));
        temp2.add(new ArrayList<>(List.of(1)));
        System.out.println(temp2.size());

        
        HashSet<HashSet<String>> temp3 = new HashSet<>();
        temp3.add(new HashSet<>(Set.of("A")));
        temp3.add(new HashSet<>(Set.of("A")));
        System.out.println(temp3.size());

        // hashset은 내부 컬렉션 데이터까지 비교한다
    }

}
