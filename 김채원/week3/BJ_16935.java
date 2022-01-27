1693import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16935 {
    private static int[][] arr;
    private static int h;
    private static int w;
    private static int r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        arr = new int[h][w];
        for(int i=0; i<h; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<w; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String[] num = br.readLine().split(" ");
        for(int menu=0; menu<num.length; menu++) {
            switch(num[menu]) {
                case "1": //상하반전
                    upDown();
                    break;
                case "2": //좌우반전
                    leftRight();
                    break;
                case "3": //오른쪽 90도
                    arr = right();
                    break;
                case "4": //왼쪽 90도
                    arr = left();
                    break;
                case "5": //4그룹 분할 후 시계방향 한 번 회전
                    quadClockWise();
                    break;
                case "6": //4그룹 분할 후 반시계방향 회전
                    quadAntiClockWise();
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void upDown() {
        int halfh = h/2;
        for(int i=0; i<halfh; i++) {
            for(int j=0; j<w; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[h-1-i][j];
                arr[h-1-i][j] = temp;
            }
        }
    }

    private static void leftRight() {
        int halfw = w/2;
        for(int i=0; i<h; i++) {
            for(int j=0; j<halfw; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[i][w-1-j];
                arr[i][w-1-j] = temp;
            }
        }
    }

    private static int[][] right() {
        int[][] newArr = new int[w][h];

        for(int i=0; i<w; i++) {
            int[] add = new int[h];
            for(int j=0; j<h; j++) {
                add[j] = arr[h-1-j][i];
            }
            newArr[i] = add;
        }
        int temp = w;
        w = h;
        h = temp;
        return newArr;
    }

    private static int[][] left() {
        int[][] newArr = new int[w][h];

        for(int i=0; i<w; i++) {
            int[] add = new int[h];
            for(int j=0; j<h; j++) {
                add[j] = arr[j][w-1-i];
            }
            newArr[i] = add;
        }
        int temp = w;
        w = h;
        h = temp;
        return newArr;
    }

    private static void quadClockWise() {
        int halfw = w/2;
        int halfh = h/2;
        int[][] a = new int[halfh][halfw];
        int[][] b = new int[halfh][halfw];
        int[][] c = new int[halfh][halfw];
        int[][] d = new int[halfh][halfw];
        ////a,b,c,d 구역으로 나눈다 - 북서, 북동, 남서, 남동 순서
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                if(i<halfh && j<halfw)
                    a[i][j] = arr[i][j];
                else if(i<halfh && j>=halfw)
                    b[i][j-halfw] = arr[i][j];
                else if(i>=halfh && j<halfw)
                    c[i-halfh][j] = arr[i][j];
                else
                    d[i-halfh][j-halfw] = arr[i][j];
            }
        }
        ////시계방향으로 재조립한다.
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                if(i<halfh && j<halfw)
                    arr[i][j] = c[i][j];
                else if(i<halfh && j>=halfw)
                    arr[i][j] = a[i][j-halfw];
                else if(i>=halfh && j<halfw)
                    arr[i][j] = d[i-halfh][j];
                else
                    arr[i][j] = b[i-halfh][j-halfw];
            }
        }
    }

    private static void quadAntiClockWise() {
        int halfw = w/2;
        int halfh = h/2;
        int[][] a = new int[halfh][halfw];
        int[][] b = new int[halfh][halfw];
        int[][] c = new int[halfh][halfw];
        int[][] d = new int[halfh][halfw];
        ////a,b,c,d 구역으로 나눈다 - 북서, 북동, 남서, 남동 순서
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                if(i<halfh && j<halfw)
                    a[i][j] = arr[i][j];
                else if(i<halfh && j>=halfw)
                    b[i][j-halfw] = arr[i][j];
                else if(i>=halfh && j<halfw)
                    c[i-halfh][j] = arr[i][j];
                else
                    d[i-halfh][j-halfw] = arr[i][j];
            }
        }
        ////반시계방향으로 재조립한다.
        for(int i=0; i<h; i++) {
            for(int j=0; j<w; j++) {
                if(i<halfh && j<halfw)
                    arr[i][j] = b[i][j];
                else if(i<halfh && j>=halfw)
                    arr[i][j] = d[i][j-halfw];
                else if(i>=halfh && j<halfw)
                    arr[i][j] = a[i-halfh][j];
                else
                    arr[i][j] = c[i-halfh][j-halfw];
            }
        }
    }
}