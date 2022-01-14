package com.example.codingtest.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj20154 {

	static int[] arr = {3, 2, 1, 2, 3, 3, 3, 3, 1, 1, 3, 1, 3, 3, 1, 2, 2, 2, 1, 2, 1, 1, 2, 2, 2, 1};
	static int sum =0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();

		int[] numArr = new int[line.length()];

		for (int i = 0; i < numArr.length; i++) {
			numArr[i] = arr[line.charAt(i)-'A'];
			sum += numArr[i];
			sum %= 10;
		}

		if (sum % 2 == 0){
			System.out.println("You're the winner?");
		}else{
			System.out.println("I'm a winner!");
		}

	}

}
