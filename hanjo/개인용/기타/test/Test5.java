package test;

import java.util.*;

public class Test5 {
    public static void main(String[] args) {

        System.out.println("------------ 배열 -----------------");

        int[] a = {1, 2, 3};
        int[] b = a;
        int[] c = {1, 2, 3};
        int[] d = {1, 2};

        System.out.println(a.hashCode());
        System.out.println(c.hashCode());
        
        // 배열의 equals는 a == b 랑 똑같다
        // 즉 주소값을 비교
        if(a == b){
            System.out.println("a == b");
        }
        if(a.equals(b)){
            System.out.println("a.equals(b)");
        }
        if(a.equals(c)){
            System.out.println("a.equals(c)");
        }

        if(Arrays.equals(a, c)){
            System.out.println("Arrays.equals(a, c)");
        }
        if(Arrays.equals(a, d)){
            System.out.println("Arrays.equals(a, d)");
        }

        System.out.println("------------ 컬렉션 (LinkedList) -----------------");
        
        Queue<Integer> qa = new LinkedList<>();
        qa.add(1);
        Queue<Integer> qb = new LinkedList<>();
        qb.add(1);

        System.out.println(qa.hashCode());
        System.out.println(qb.hashCode());

        if(qa == qb){
            System.out.println("qa == qb");
        }
        if(qa.equals(qb)){
            System.out.println("qa.equals(qb)");
        }

        System.out.println("------------ 컬렉션 (ArrayList) -----------------");


        ArrayList<Integer> aa = new ArrayList<>();
        aa.add(1);
        aa.add(2);

        ArrayList<Integer> ab = new ArrayList<>();
        ab.add(2);
        ab.add(1);

        System.out.println(aa.hashCode());
        System.out.println(ab.hashCode());


        // List<Queue<Integer>> temp = new ArrayList<>();
        // temp.add(qa);
        // temp.add(qb);


        // if(qa.equals(qb)){
        //     System.out.println(qa.hashCode());
        //     System.out.println(qb.hashCode());

        //     // System.out.println("qa.equals(qb))");
        // }

        System.out.println("------------ Object -----------------");


        Object o1 = new Object();
        Object o2 = new Object();

        System.out.println(o1.hashCode());
        System.out.println(o2.hashCode());
        System.out.println(o1 == o2);
        System.out.println(o1.equals(o2));

        System.out.println("------------ String literal -----------------");

        String s1 = "a";
        String s2 = "a";

        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s1 == s2);
        System.out.println(s1 == "a");
        System.out.println(s1.equals(s2));


        System.out.println("------------ String Object -----------------");

        String so1 = new String("a");
        String so2 = new String("a");

        System.out.println(so1.hashCode());
        System.out.println(so2.hashCode());
        System.out.println(so1 == so2);
        System.out.println(so1.equals(so2));



        
        // 배열은 equlas 사용시 주소값만 비교하므로 Arrays.equals()를 사용해야 내부 요소까지 비교함
        // but 컬렉션의 순서와 내용이 같다면 객체가 여러개여도 하나의 주소값을 공유한다
        // 근데 Queue에 1넣은거랑 ArrayList에 1넣은거랑 주소값이 같음. 이거 뭐지?
    
    }
}
