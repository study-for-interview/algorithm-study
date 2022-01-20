package com.example.codingtest.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9879 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int rank = 1;

		int[][] arr = new int[n + 1][3];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int idx = Integer.parseInt(st.nextToken());

			arr[idx][0] = Integer.parseInt(st.nextToken());
			arr[idx][1] = Integer.parseInt(st.nextToken());
			arr[idx][2] = Integer.parseInt(st.nextToken());
		}

		for(int i=1; i<=n; i++) {
			if(arr[i][0] > arr[k][0]) {
				rank++;
			}
			else if(arr[i][0] == arr[k][0] && arr[i][1] > arr[k][1]) {
				rank++;
			}
			else if(arr[i][0] == arr[k][0] && arr[i][1] == arr[k][1] && arr[i][2] > arr[k][2]) {
				rank++;
			}
		}
		System.out.println(rank);
		br.close();

	}

}
