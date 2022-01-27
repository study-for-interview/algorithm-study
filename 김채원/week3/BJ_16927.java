import java.util.*;
public class BJ_16927 {

    static int N;
    static int M;
    static int rotate;
    static int[][] array;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        rotate = sc.nextInt();
        array = new int[N][M];

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                array[i][j] = sc.nextInt();
            }
        }

        int rowStart = 0;
        int rowEnd = N-1;

        int colStart = 0;
        int colEnd = M-1;
        while(true) {
            int size = (rowEnd-rowStart+1)*2 + (colEnd-colStart+1)*2 -4;
            change(rowStart,rowEnd,colStart,colEnd,rotate%size);
            rowStart+=1;
            rowEnd-=1;
            colStart+=1;
            colEnd-=1;
            if(rowStart>rowEnd || colStart>colEnd) break;
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void change(int rowStart, int rowEnd, int colStart, int colEnd, int cnt) {
        for(int k=0;k<cnt;k++) {
            int temp = array[rowStart][colStart];

            for(int j=colStart;j<colEnd;j++) {
                array[rowStart][j] = array[rowStart][j+1];
            }

            for(int i=rowStart;i<rowEnd;i++) {
                array[i][colEnd] = array[i+1][colEnd];
            }

            for(int j=colEnd;j>colStart;j--) {
                array[rowEnd][j] = array[rowEnd][j-1];
            }

            for(int i=rowEnd;i>rowStart;i--) {
                array[i][colStart] = array[i-1][colStart];
            }
            array[rowStart+1][colStart] = temp;
        }
    }

}