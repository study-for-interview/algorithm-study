// package 코테.sk_2022.문제1;

// import java.util.*;

// public class Solution {

//     public static class Coin{
//         public int num;
//         public int cost;
        
//         public Coin(int num, int cost){
//             this.num = num;
//             this.cost = cost;
//         }
//     }

//     public static final int[] nums = {1,5,10,50,100,500};

//     public static int solution(int money, int[] costs){
//         // 동전 리스트 초기화
//         int n = costs.length;
//         ArrayList<Coin> coins = new ArrayList<>();
//         for(int i=0; i<n; i++){
//             coins.add(new Coin(nums[i], costs[i]));
//         }
//         // dp 배열 초기화
//         int[][] dp = new int[n+1][money+1];
        
//     }

//     public static void main(String[] args){
//         System.out.println(solution(4578, new int[]{1, 4, 99, 35, 50, 1000}));
//     }
    
// }
