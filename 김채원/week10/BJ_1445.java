import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1445 {

    public static class now implements Comparable<now>{
        int x;
        int y;
        int count;
        int nex;
        public now(int x, int y, int count, int nex) {
            super();
            this.x = x;
            this.y = y;
            this.count = count;
            this.nex = nex;
        }
        @Override
        public int compareTo(now o) {
            // TODO Auto-generated method stub
            if(this.count>o.count) {
                return 1;
            }
            else if(this.count==o.count) {
                return this.nex-o.nex;
            }
            return -1;
        }

    }
    static int n,m,sx,sy,fx,fy,resulta,resultb;
    static PriorityQueue<now> pq;
    static String map[];
    static boolean visit[][];
    static int dir[][]= {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        pq=new PriorityQueue<now>();
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        map=new String[n];
        visit=new boolean[n][m];
        for(int i=0;i<n;i++) {
            map[i]=br.readLine();
            for(int j=0;j<m;j++) {
                if(map[i].charAt(j)=='F') {
                    fx=i;
                    fy=j;
                }
                else if(map[i].charAt(j)=='S') {
                    sx=i;
                    sy=j;
                }
            }
        }
        pq.add(new now(sx,sy,0,0));
        bfs();
        System.out.println(resulta+" "+resultb);

    }
    private static void bfs() {
        // TODO Auto-generated method stub
        while(!pq.isEmpty()) {
            now next=pq.remove();
            if(visit[next.x][next.y]) {
                continue;
            }
            visit[next.x][next.y]=true;
            for(int i=0;i<4;i++) {
                int x1=next.x+dir[i][0];
                int y1=next.y+dir[i][1];
                if(x1<0||x1>=n||y1<0||y1>=m||visit[x1][y1]) {
                    continue;
                }
                if(x1==fx&&y1==fy) {
                    resulta=next.count;
                    resultb=next.nex;
                    return;
                }
                if(map[x1].charAt(y1)=='g') {
                    pq.add(new now(x1,y1,next.count+1,next.nex));
                }
                else {
                    boolean flag=true;
                    for(int j=0;j<4;j++) {
                        int x2=x1+dir[j][0];
                        int y2=y1+dir[j][1];
                        if(x2<0||x2>=n||y2<0||y2>=m) {
                            continue;
                        }
                        if(map[x2].charAt(y2)=='g') {
                            pq.add(new now(x1,y1,next.count,next.nex+1));
                            flag=false;
                            break;
                        }
                    }
                    if(flag)
                        pq.add(new now(x1,y1,next.count,next.nex));
                }
            }
        }
    }


}