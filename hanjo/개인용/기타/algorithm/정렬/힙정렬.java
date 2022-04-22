package algorithm.정렬;

import java.util.*;

public class 힙정렬 {

    public static int[] heapSort(int[] arr) {
        int n = arr.length;
     
        // 최대힙 만들기
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
     
        // 최대힙을 가지고 내림차순으로 배열 정렬하기
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }

        return arr;
    }

    public static void heapify(int arr[], int n, int i) {
        int p = i;
        int l = i * 2 + 1;
        int r = i * 2 + 2;
     
        if (l < n && arr[p] < arr[l]) {
            p = l;
        }
     
        if (r < n && arr[p] < arr[r]) {
            p = r;
        }
     
        if (i != p) {
            swap(arr, p, i);
            heapify(arr, n, p);
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
     
    public static void main(String[] args){

        int[] arr = {8, 5, 6, 1, 4, 3, 9, 7, 2};

        System.out.println(Arrays.toString(heapSort(arr)));

    }
}

// https://hmkim829.tistory.com/9