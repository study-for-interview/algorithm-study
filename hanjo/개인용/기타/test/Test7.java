package test;

import java.util.*;

public class Test7 {

    public static class A{
        public int a;
        public A(int a){
            this.a = a;
        }
        @Override
        public String toString(){
            return String.valueOf(a);
        }
    }
    public static void main(String[] args) {
        A[][] arr = {
            {new A(0), new A(1)},
            {new A(2), new A(3)}
        };

        
        // A[][] copy = arr.clone();
        A[][] copy = new A[arr.length][arr[0].length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        copy[0][0].a = 100;
        copy[0][1].a = 100;
        copy[1][0].a = 100;
        copy[1][1].a = 100;
        
        for(var a : arr){
            System.out.println(Arrays.toString(a));
        }
        for(var a : copy){
            System.out.println(Arrays.toString(a));
        }
    }
}
