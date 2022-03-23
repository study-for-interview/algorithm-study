package com.example.codingtest.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1138 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] line = new int[N+1];

		for(int i = 1 ; i<= N ; i++) {
			int cnt = Integer.parseInt(st.nextToken());
			for(int j = 1; j<= N ;j ++) {
				if(cnt==0) {
					if(line[j]==0) {
						line[j] = i;
						break;
					}
					else continue;
				}
				else if(line[j]==0) {
					cnt--;
				}
			}
		}
		for(int i =1 ; i<=N ;i++) {
			System.out.print(line[i]+" ");
		}

	}

}
