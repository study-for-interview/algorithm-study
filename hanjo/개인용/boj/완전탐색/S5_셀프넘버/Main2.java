package 완전탐색.S5_셀프넘버;

public class Main2 {

    public static final int MAX = 10_000;

    public static void main(String[] args) {

        boolean[] check = new boolean[MAX+1];
    
        for(int i=1; i<=MAX; i++){
            find2(i, check);
        }

        for(int i=1; i<=MAX; i++){
            if(!check[i]){
                System.out.println(i);
            }
        }
    }

    // 768 ms
    public static void find(int n, boolean[] check){
        int numLength = String.valueOf(n).length();
        int sum = n;
        for(int i=numLength; i >= 0; i--){
            int unit = n / (int)Math.pow(10, i);
            n -= unit * (int)Math.pow(10, i);
            sum += unit;
        }
        if(sum > MAX){
            return;
        }
        check[sum] = true;
        find(sum, check);
    }

    // 764 ms
    public static void find2(int n, boolean[] check){
        int sum = n;
        while(n != 0){
            sum += n % 10;
            n /= 10;
        }
        if(sum > MAX){
            return;
        }
        check[sum] = true;
        find(sum, check);
    }
}