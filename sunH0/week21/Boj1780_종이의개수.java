package 분할정복;

import java.util.*;
import java.io.*;

public class Boj1780_종이의개수 {

    static int N;
    static int[][] board;
    static int ans[] = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N][N];
    
		for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

        partition(0, 0, N);
        
        for(int i=0;i<3;i++){
            System.out.println(ans[i]);
        }
    
    }

    static void partition(int row, int col, int size){
        if(check(row, col, size)){
            ans[board[row][col]+1] += 1;
            
            return;
        }

        for(int i=row; i<row+size; i+=(size/3)){
            for(int j=col; j<col+size; j+=(size/3)){
                partition(i, j, size/3);                                
            }
        }


    }
    
    static boolean check(int row, int col, int size){
        int n = board[row][col];
        for(int i=row; i<row+size; i++) {
            for(int j=col; j<col+size; j++){
                if(board[i][j] != n) return false;
            }
        }

        return true;
    }
    
}
