package kakao;

import javax.swing.event.AncestorEvent;

public class Pms_k진수에서소수개수구하기 {

    public static int solution(int n, int k) {

        // 진수구하기
        StringBuilder sb = new StringBuilder();
        while(n>0) {
            sb.append(n%k);
            n /= k;
        }

        int answer=0;


        String[] strs = sb.reverse().toString().split("0");

        for(int i=0;i<strs.length;i++){
            
            if(strs[i].equals("")) continue;
            else if(isPrime(Long.valueOf(strs[i]))) answer++;
        }
        
        
        return answer;
    }

    static boolean isPrime(long num){

        if(num==1) return false;


        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;

    }

    public static void main(String[] args) throws Exception {
        System.out.println(solution(110011,10));
    }
}

// n을 k진수 변환? -> P 조건 확인 -> P소수확인 ?