package 카카오.L3_징검다리건너기;

public class Try1 {

    public static int solution(int[] stones, int k) {

        int len = stones.length;
        int pass = 0;

        while(true){
            if(!canPass(stones, k)){
                break;
            }
            // System.out.println(Arrays.toString(stones));
            int min = findMin(stones);
            for(int i=0; i<len; i++){
                if(stones[i] != 0){
                    stones[i] -= min;
                }
            }
            pass += min;
        }

        return pass;
    }

    public static int findMin(int[] arr){
        int min = Integer.MAX_VALUE;
        for(int num : arr){
            if(num < min && num != 0){
                min = num;
            }
        }
        return min;
    }

    public static boolean canPass(int[] stones, int k){
        int len = stones.length;
        int count = 0;
        int sum = 0;
        for(int i=0; i<len; i++){
            sum += stones[i];
            if(stones[i] == 0){
                count ++;
            } else{
                count = 0;
            }
            if(count == k){
                return false;
            }
        }
        
        return sum == 0 ? false : true;
    }

    public static void main(String[] args){
        // 3
        System.out.println(solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
        // 1
        System.out.println(solution(new int[]{1}, 2));
        // 0
        System.out.println(solution(new int[]{0}, 1));
    }

}
