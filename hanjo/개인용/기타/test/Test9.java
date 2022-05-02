package test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test9 {
    
    // public static interface i1 {
    //     default void test(){};
    // }
    // public static interface i2 {
    //     default void test(){};
    // }

    // public static class c1 {
    //     void test(){};
    // }
    // public static class c2 {
    //     void test(){};
    // }

    // public static class t1 extends c1 implements i1{
        
    // }

    // public static class t2 implements i1, i2{
        
    // }

    public static void main(String[] args){
        
        List<Integer> list = List.of(1, 2, 3);

        List<Integer> target = new ArrayList<>();

        AtomicInteger a = new AtomicInteger(1);

        
        list.stream().forEach(v -> {
            // // target.add(v + a);
            // list.add(4);
            a.incrementAndGet();
        });

        System.out.println(a);
        System.out.println(target);
        System.out.println(list);
    }
}
