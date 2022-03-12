package algorithm.정렬;

import java.util.*;

public class 삽입정렬 {

    public static int[] insertionSort(int[] arr){

        int len = arr.length;

        for(int i=1; i<len; i++){
            int key = arr[i];
            int j = i- 1;
            
            while(j >= 0 && key<arr[j]){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
        
        return arr;
    }


    public static void main(String[] args){

        int[] arr = {8, 5, 6, 1, 4, 3, 9, 7, 2};
        
        System.out.println(Arrays.toString(insertionSort(arr)));

    }
}

// https://gmlwjd9405.github.io/images/algorithm-insertion-sort/insertion-sort.png