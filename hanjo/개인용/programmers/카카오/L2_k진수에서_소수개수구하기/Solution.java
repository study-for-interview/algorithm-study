package 카카오.L2_k진수에서_소수개수구하기;

class Solution {
    public int solution(int n, int k) {

        // 진법 바꾸기
        String numStr = String.valueOf(n);
        if(k<10){
            numStr = convert(n, k);
        }

        // 소수찾기
        int count = 0;
        for(String splited : numStr.split("0")){
            if(splited.equals("")){
                continue;
            }
            long num = Long.parseLong(splited);
            if(isPrime(num)){
                count++;
            }
        }

        return count;
    }

    public String convert(int n, int k){
        StringBuilder sb = new StringBuilder();
        while(n != 0){
            int remain = n % k;
            sb.insert(0, remain);
            n = n / k;
        }
        return sb.toString();
    }

    public boolean isPrime(long num){
        if(num == 1){
            return false;
        }
        int sqrt = (int)Math.sqrt(num);
        for(int i=2; i<=sqrt; i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        // 3
        System.out.println(new Solution().solution(437674, 3));
        // 2
        System.out.println(new Solution().solution(110011, 10));
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/92335
 * 날짜 : 220415
 * 성공여부 : 성공
 * 풀이시간 : 30m
 * 
 * 시간복잡도 : 
 * 테케1 : (10.12ms, 76.4MB)
 * ================================================================================
 * 
 * 진법변환 + 소수찾기 문제
 * 그냥 말 그대로 두개만 할줄 알면 풀수있는 문제
 * 
 * <시행착오>
 * 
 * k진법으로 변환한 숫자가 매우 길 수 있다는 것을 간과하고 int type을 썼음
 * long 타입으로 바꿔주니까 잘됨
 * 
 * n을 소수판별할때 n을 2 ~ n-1 로 나눠보고 판별했는데 이러면 테케1에서 시간초과남
 * n의 제곱근+1 까지만 나눠봐도 됐음
 * 
 */