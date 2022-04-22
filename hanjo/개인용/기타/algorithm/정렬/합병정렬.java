package algorithm.정렬;

import java.util.*;

public class 합병정렬 {

    public static int[] mergeSort(int[] arr){
        
        recursive(arr, 0, arr.length - 1);

        return arr;
    }

    public static void recursive(int[] arr, int left, int right){
        
        if(left < right){
            int mid = (left + right)/2;

            // 분할
            recursive(arr, left, mid);
            recursive(arr, mid+1, right);

            // 정복 - 합치면서 정렬
            int[] sorted = new int[right-left+1];
            int index = 0;
            int L = left;
            int R = mid+1;
            while(L <= mid && R <= right){
                if(arr[L] < arr[R]){
                    sorted[index] = arr[L];
                    L++;
                }
                else{
                    sorted[index] = arr[R];
                    R++;
                }
                index++;
            }

            // 남아있다면 추가해줌
            for(int i=L; i<mid+1; i++){
                sorted[index] = arr[i];
                index++;
            }
            for(int i=R; i<=right; i++){
                sorted[index] = arr[i];
                index++;
            }

            // 정렬된것 원본에 반영
            for(int i=0; i<sorted.length; i++){
                arr[i+left] = sorted[i];
            }
        }
    }

    public static void main(String[] args){

        int[] arr = {8, 5, 6, 1, 4, 3, 9, 7, 2};

        System.out.println(Arrays.toString(mergeSort(arr)));

    }
}
