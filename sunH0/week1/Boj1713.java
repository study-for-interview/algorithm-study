package com.example.codingtest.baekjoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Boj1713 {

	static class Person implements Comparable<Person>{
		int num;
		int count;
		int time;
		public Person(int num, int total, int time) {
			super();
			this.num = num;
			this.count = total;
			this.time = time;
		}
		@Override
		public int compareTo(Person o) {
			if(this.count==o.count) {
				return this.time-o.time;
			}else if(this.count<o.count)return -1;
			else return 1;

		}

	}

	static int n,m;
	static int[] students;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		students=new int[101];
		ArrayList<Person> list=new ArrayList<>();
		for (int i = 0; i < m; i++) {
			int student=sc.nextInt();
			if(students[student]>0) {
				students[student]+=1;
				for (int j = 0; j <list.size(); j++) {
					if(list.get(j).num==student) {
						list.get(j).count+=1;
						break;
					}
				}
			}else {
				if(list.size()>=n) {
					Collections.sort(list);
					int num=list.get(0).num;
					students[num]=0;
					list.remove(0);
				}
				list.add(new Person(student,1,i));
				students[student]=1;
			}

		}

		for (int i = 0; i <101; i++) {
			if(students[i]!=0) {
				System.out.print(i+" ");
			}
		}

	}
}



