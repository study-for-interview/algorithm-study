package Search;

import java.io.*;
import java.util.*;

class Dot{
    int x;
    int y;

    Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

public class Boj5588_별자리찾기 {
    static int M,N;
    static Dot[] arrM, arrN;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
		arrM = new Dot[M];
		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			arrM[i] = new Dot(x, y);
		}

		N = sc.nextInt();
		arrN = new Dot[N];
		for (int i = 0; i < N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			arrN[i] = new Dot(x, y);
		}

        for(int i=0; i<N;i++){
            int xx = arrN[i].x -arrM[0].x;
            int yy = arrN[i].y -arrM[0].y;

            if(search(xx, yy)){
                System.out.println(xx + " " + yy);
            }
        }
        sc.close();

    }

    static boolean search(int xx, int yy){
        
        for(int i=0; i<M; i++){
            
            int nx = xx + arrM[i].x;
			int ny = yy + arrM[i].y;
            boolean flag = false;

            for(int n=0;n<N;n++)
			{
				if(nx == arrN[n].x && ny == arrN[n].y) flag = true;
			}
            if(flag==false) return false;
                    
        }
        return true;
    }


}
