package algorithm.정렬;

import java.util.*;

public class 선택정렬 {

    public static int[] selectionSort(int[] arr){

        int len = arr.length;

        for(int i=0; i<len; i++){
            int min = i;
            for(int j=i+1; j<len; j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        
        return arr;
    }


    public static void main(String[] args){

        int[] arr = {8, 5, 6, 1, 4, 3, 9, 7, 2};
        
        System.out.println(Arrays.toString(selectionSort(arr)));

    }
}
