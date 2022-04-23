package algorithm.정렬;


import java.util.*;

public class 퀵정렬 {

    public static int[] quickSort(int[] arr){

        recursive(arr, 0, arr.length-1);
    
        return arr;
    }

    public static void recursive(int[] arr, int left, int right){
        
        if(left < right){
            // 피봇정하고 피봇 기준으로 정렬
            int pivot = left;
            int pivotNum = arr[pivot];
            int L = left;
            int R = right;

            while(L < R){
                while(arr[L] < pivotNum){
                    L++;
                }
                while(arr[R] > pivotNum){
                    R--;
                }
                if(L < R){
                    int temp = arr[L];
                    arr[L] = arr[R];
                    arr[R] = temp;
                }
            }
            
            // 분할하여 반복
            recursive(arr, left, R-1);
            recursive(arr, R+1, right);
        }
    }

    public static void main(String[] args){

        int[] arr = {8, 5, 6, 1, 4, 3, 9, 7, 2};
        
        System.out.println(Arrays.toString(quickSort(arr)));

    }
}

// https://gmlwjd9405.github.io/2018/05/10/algorithm-quick-sort.html