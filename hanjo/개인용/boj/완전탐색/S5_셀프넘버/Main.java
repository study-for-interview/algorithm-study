package 완전탐색.S5_셀프넘버;

public class Main {

    public static final int MAX = 10_000;

    public static void main(String[] args) {

        boolean[] check = new boolean[MAX+1];
    
        for(int i=1; i<=MAX; i++){
            find(i, check);
        }

        for(int i=1; i<=MAX; i++){
            if(!check[i]){
                System.out.println(i);
            }
        }
    }

    public static void find(int n, boolean[] check){
        int sum = n;
        while(n != 0){
            sum += n % 10;
            n /= 10;
        }
        if(sum > MAX){
            return;
        }
        check[sum] = true;
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/4673
 * 날짜 : 220317
 * 성공여부 : 성공
 * 풀이시간 : 30m
 * 
 * 시간복잡도 : O(10_000)
 * 메모리 : 14608 KB
 * 소요 시간 : 156 ms
 * ================================================================================
 * 
 * 걍 완탐문제
 * 
 * Try1에서 재귀를 돌면서 해당 숫자의 모든 수열을 싹다 구해서 시간이 오래걸림
 *  --> 탐색 시간복잡도 : O(MAX^MAX)
 * 
 * 근데 해당 숫자의 모든 수열을 구할필요가 없었음
 * 만약 d(n)의 첫번째 결과값이 나오면 그건 n이 생성자가 되는 수임. 즉 셀프넘버 아님 -> 체크
 * 그리고 해당 숫자는 10000보다 작으므로 어차피 뒤에서 또 나오니까.. 그 다음 수열은 생각 안해도됨.
 *  --> 탐색 시간복잡도 : O(MAX)
 * 
 */