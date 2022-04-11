package 코테.오늘의집_2022_1.문제2;

import java.util.*;

public class Solution {

    public static class Furniture implements Comparable<Furniture>{
        public int longer;
        public int shorter;
        
        public Furniture(int a, int b){
            this.longer = Integer.max(a, b);
            this.shorter = Integer.min(a, b);
        }

        @Override
        public int compareTo(Furniture o){
            if(o.longer == this.longer){
                return o.shorter - this.shorter;
            }
            return o.longer - this.longer;
        }
    }

    public int soltuion(int[][] beds, int[][] tables, int cost){

        int min = Integer.MAX_VALUE;

        for(int[] bed : beds){
            for(int[] table : tables){
                List<Furniture> sorted = new ArrayList<>();
                sorted.add(new Furniture(bed[0], bed[1]));
                sorted.add(new Furniture(table[0], table[1]));
                Collections.sort(sorted);

                int areaCost = cost * getMinArea(sorted);
                int furnitureCost = bed[2] + table[2];
                min = Math.min(min, furnitureCost + areaCost);
            }
        }
        return min;
    }

    public int getMinArea(List<Furniture> sorted){
        Furniture f1 = sorted.get(0);
        Furniture f2 = sorted.get(1);

        int len1 = f1.longer;
        int len2 = f1.shorter + f2.shorter;
        
        if(f1.shorter >= f2.longer){
            len1 = f1.longer + f2.shorter;
            len2 = f1.shorter;
        }
        return len1*len2;
    }


    public static void main(String[] args){
        // 420000
        System.out.println(new Solution().soltuion(
            new int[][]{
                {4, 1, 200000}
            },
            new int[][]{
                {2, 3, 100000}
            },
            10000
        ));
        // 80070
        System.out.println(new Solution().soltuion(
            new int[][]{
                {2, 3, 40},
                {2, 5, 20}
            },
            new int[][]{
                {1, 1, 30}
            },
            10000
        ));
        // 50120
        System.out.println(new Solution().soltuion(
            new int[][]{
                {2, 3, 40000},
                {2, 5, 20000}
            },
            new int[][]{
                {1, 1, 30000}
            },
            10
        ));
    }
    
}
