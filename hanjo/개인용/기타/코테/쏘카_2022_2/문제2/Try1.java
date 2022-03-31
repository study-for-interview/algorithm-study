package 코테.쏘카_2022_2.문제2;

class Try1 {

    public int min = Integer.MAX_VALUE;
    public int[] numbers;
    public int N;
    public int K;

    public int solution(int[] numbers, int K) {
        this.numbers = numbers;
        this.K = K;
        this.N = numbers.length;

        dfs(1);

        if(min == Integer.MAX_VALUE){
            return -1;
        }
        return min-1;
    }

    public void dfs(int window){

        if(window == N){
            return;
        }
        if(isValid()){
            min = Math.min(min, window);
            return;
        }

        // window(2칸) 요소를 번갈아 탐색 
        for(int j=window+1; j<N; j++){
            if(Math.abs(numbers[window-1]-numbers[j]) <= K){
                swap(window, j);
                dfs(window+1);
                swap(window, j);    // 백트래킹
            }
        }
        for(int j=window+1; j<N; j++){
            if(Math.abs(numbers[window]-numbers[j]) <= K){
                swap(window-1, j);
                dfs(window+1);
                swap(window-1, j);
            }
        }
    }

    public void swap(int x, int y){
        int temp = numbers[x];
        numbers[x] = numbers[y];
        numbers[y] = temp;
    }

    public boolean isValid(){
        for(int i=0; i<N-1; i++){
            if(Math.abs(numbers[i] - numbers[i+1]) > K){
                return false;
            }
        }
        return true;
    }
}

// 62.5 