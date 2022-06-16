package 구현;

import java.util.*;
import java.io.*;

public class Boj16927_배열돌리기 {
    static int N, M, R;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1}; 
    static int[][] arr;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

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

        for(int i=0; i<Math.min(N, M)/2; i++) {
            rotate(i, 2*(N-2*i) + 2*(M-2*i) -4);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) { 
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		} 
		System.out.println(sb);	


    }

    public static void rotate(int i, int len) {
        int cnt = R%len;

        for (int j = 0; j < cnt; j++) {
			int x = i;
            int y = i;
			int pre = arr[i][i];
			int tmp, idx = 0;
			
			while(idx<4){
                int xx = x + dx[idx];
                int yy = y + dy[idx];
				if (xx >= i && xx < N - i && yy >= i && yy < M - i) {
					tmp = arr[xx][yy];
					arr[xx][yy] = pre;
					pre = tmp;
                    x = xx;
					y = yy;
				} else idx++;
            }			
		}

    }
}
