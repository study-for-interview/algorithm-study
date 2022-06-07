import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static int a, b, c; // -1,0,1

    static void partition(int row, int cal, int size) {
        if (isSameColor(row,cal,size)) {
            if(arr[row][cal] == -1) a++;
            else if(arr[row][cal] == 0) b++;
            else c++;

            return;
        }

        int newSize = size / 3 ;

        partition(row,cal,newSize);
        partition(row,cal+newSize,newSize);
        partition(row,cal+2*newSize, newSize);

        partition(row+newSize,cal,newSize);
        partition(row+newSize,cal+newSize,newSize);
        partition(row+newSize,cal+2*newSize, newSize);

        partition(row+2*newSize,cal,newSize);
        partition(row+2*newSize,cal+newSize,newSize);
        partition(row+2*newSize,cal+2*newSize, newSize);
    }

    private static boolean isSameColor(int row,int cal, int size) {
        int color = arr[row][cal];
        for (int i = row; i < row + size; i++) {
            for (int j = cal; j < cal + size; j++) {
                if(color != arr[i][j]) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        a = b = c = 0;
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        partition(0,0,N);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

    }
}