import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_7573 {
    //모눈종이 크기, 그물의 길이, 물고기의 수
    static int N,l,M,ans;
    static int[][] map;
    static ArrayList<point> fishs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  =new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            fishs.add(new point(x, y));
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 1; k < l/2; k++) {
                    //두개를 지정해서 던져주고, 그물의 길이 가로 세로 를 던져준다.
                    Search(i,j,k,l/2-k);
                }
            }
        }
        System.out.println(ans);
    }

    private static void Search(int i, int j, int lx, int ly) {
        int temp=0;
        for (int k = 0; k < M; k++) {
            if(fishs.get(i).x<=fishs.get(k).x && fishs.get(k).x<=fishs.get(i).x+lx&&
                    fishs.get(j).y<=fishs.get(k).y && fishs.get(k).y<=fishs.get(j).y+ly) {
                temp++;
            }
        }
        ans = ans>temp?ans:temp;
    }

    static class point{
        int x;
        int y;
        public point(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }
}