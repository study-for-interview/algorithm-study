import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class BOJ9207_페그솔리테어 {
    
    static char [][] map;
    static int xx=5, yy=9;
    static int minPin, move;
    
    static int [] dx = {-1,0,1,0};
    static int [] dy = {0,1,0,-1};
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        for(int d=0; d<N;d++) {
            map = new char[xx][yy];
            
            int pin=0;
            for(int i=0;i<xx;i++) {
                String str = br.readLine();
                for(int j=0;j<yy;j++) {
                    map[i][j] = str.charAt(j);
                    if(map[i][j]=='o') pin++;
                }
            }
            
            minPin = pin;
            
            for(int i=0;i<xx;i++) {
                for(int j=0;j<yy;j++) {
                    if(map[i][j]=='o') dfs(i,j,pin,0);
                }
            }
            
            br.readLine();
            
            sb.append(minPin+" "+move+"\n");
        }
        
        System.out.println(sb.toString());
 
    }
    
    public static void dfs(int x, int y , int remain, int moveCnt) {
        if(remain<=minPin) {
            minPin = remain;
            move = moveCnt;
        }
        
        for(int d=0;d<4;d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if(range(nx,ny) && map[nx][ny]=='o') {
                int nnx = nx +dx[d];
                int nny = ny +dy[d];
                
                if(range(nnx,nny) && map[nnx][nny]=='.') {
                    map[x][y]=map[nx][ny]='.';
                    map[nnx][nny]='o';
                    
                    for(int i=0;i<xx;i++) {
                        for(int j=0;j<yy;j++) {
                            if(map[i][j]=='o') dfs(i,j,remain-1,moveCnt+1);
                        }
                    }
                    
                    map[x][y]=map[nx][ny]='o';
                    map[nnx][nny]='.';
                }
            }
            
        }
    }
    
    public static boolean range(int x, int y) {
        return x>=0 && x<xx && y>=0 && y<yy;
    }
 
}
