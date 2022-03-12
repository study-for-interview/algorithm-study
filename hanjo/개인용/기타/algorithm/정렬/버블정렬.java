package algorithm.정렬;

import java.util.*;

public class 버블정렬 {

    public static int[] bubbleSort(int[] arr){

        int len = arr.length;

        for(int i=0; i<len; i++){
            for(int j=0; j<len-i-1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        
        return arr;
    }


    public static void main(String[] args){

        int[] arr = {8, 5, 6, 1, 4, 3, 9, 7, 2};
        
        System.out.println(Arrays.toString(bubbleSort(arr)));

    }
}
