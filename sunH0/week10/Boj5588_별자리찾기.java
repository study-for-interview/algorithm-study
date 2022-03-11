import java.io.*;
import java.util.*;

public class Boj5588_별자리찾기 {
    static int M,N;
    static Dot[] arrM, arrN;

    public static void main(String[] args) throws Excetion {
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
            int xx = arrM[0].x - arrN[i].x;
            int yy = arrM[0].y - arrN[i].x;

            if(search(xx, yy)){
                System.out.println(xx + " " + yy);
            }
        }

    }

    static boolean search(int xx, int yy){
        for(int m=0; m<M; m++){
            boolean check = false;

            int nX = dX + arrM[m].X;
			int nY = dY + arrM[m].Y;

            for(int n=0;n<N;n++)
			{
				if(mX == arrN[n].X && mY == arrN[n].Y)
                    check = true;
			}
                    
        }
        return check;
    }


}

class Dot{
    int x;
    int y;

    Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

