import java.io.*;
import java.util.*;
 
public class Boj3190_뱀 {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] map = new int[N+2][N+2];
        Map<Integer, String> dirMap = new HashMap<>();

        for(int i = 0; i < N+2; i++) {
            map[0][i] = map[N+1][i] = map[i][0] = map[i][N+1] = 1;
        }
        
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 2;
        }
 
        int L = Integer.parseInt(br.readLine());

        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken();
            dirMap.put(x, c);
        }
 
        int dir = 1;
        int time = 0;
        Deque<Node> snake = new ArrayDeque<>();

        snake.add(new Node(1,1));

        while(true) {
            time++;
            Node head = snake.peekLast();

            int nX = head.x + dx[dir];
            int nY = head.y + dy[dir];

            if(map[nY][nX] == 1) {
                break;
            }

            if(map[nY][nX] != 2) {
                Node tail = snake.poll();
                map[tail.y][tail.x] = 0;
            }

            map[nY][nX] = 1;
            snake.addLast(new Node(nY, nX));

            if(dirMap.containsKey(time)) {
                dir = (dirMap.get(time).equals("D")) ? (dir+1) % 4 : (dir+3) % 4;
            }

        }
        
        System.out.println(time);
    }
 
    private static class Node {
        int x;
        int y;
        Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}