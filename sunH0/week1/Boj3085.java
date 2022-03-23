package com.example.codingtest.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj3085 {
	public static char[][] boni;
	public static int N;
	public static int max = 0;

	public static void swap(int x1, int y1, int x2, int y2) {
		char tmp = boni[x1][y1];
		boni[x1][y1] = boni[x2][y2];
		boni[x2][y2] = tmp;
	}
	public static void arrCheck() {

		for(int i=0; i<N; i++) {
			int cnt = 1;
			for(int j=0; j<N-1; j++) {
				if(boni[i][j] == boni[i][j+1]) {
					cnt++;
				}else cnt = 1;

				max = Math.max(max, cnt);
			}
		}

		for(int i=0; i<N; i++) {
			int cnt = 1;
			for(int j=0; j<N-1; j++) {
				if(boni[j][i] == boni[j+1][i]) {
					cnt++;
				}else cnt = 1;
				max = Math.max(max, cnt);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		boni = new char[N][N];

		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j< boni[i].length; j++) {
				boni[i][j] = str.charAt(j);
			}
		}

		for(int i=0; i<N; i++) {
			for(int j=0; j<N-1; j++) {
				swap(i,j,i,j+1);

				arrCheck();

				swap(i,j,i,j+1);
			}
		}

		for(int i=0; i<N; i++) {
			for(int j=0; j<N-1; j++) {
				swap(j,i,j+1,i);

				arrCheck();

				swap(j,i,j+1,i);
			}
		}

		System.out.println(max);
		br.close();
	}
}
