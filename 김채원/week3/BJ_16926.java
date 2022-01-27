import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16926.java {
    static int N,M,R;
    static int[][] arr;
    static int[] directionX = {0,1,0,-1};
    static int[] directionY = {1,0,-1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder("");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int s = Math.min(N, M) / 2; //  회전하는 사각형의 갯수

        for (int i = 0; i < R; i++) {
            rotate(s);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void rotate(int s) {
        for(int i = 0 ; i < s; i++) {
            int direction= 0 ; // 방향
            int sx = i;  // 시작점 (왼쪽 위)
            int sy = i;
            int temp = arr[sx][sy];
            while (direction < 4) {
                int nextX = sx + directionX[direction];
                int nextY = sy + directionY[direction];

                //사각형을 벗어나지 않으면 계속 진행
                if(nextX < i && nextY < i && nextX >= N-i && nextY >= M-i ) {
                    arr[sx][sy] = arr[nextX][nextY];  // a00 = a01  => a01 = a02
                    sx = nextX;
                    sy = nextY;
                }else { // 벗어나면 방향 전환
                    direction ++;
                }
            }
            arr[i+1][i] = temp; //a10 = a00
        }
    }

}