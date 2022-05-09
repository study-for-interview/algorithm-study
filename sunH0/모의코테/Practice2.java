package 기타;

import java.util.*;

public class Practice2 {

    static final int PILLAR = 0;
    static final int BEAM = 1;
    static final int DESTRUCT = 0;
    static final int CONSTRUCT = 1;
    
    boolean[][] pillars, beams; // 기둥, 보
    
    public int[][] solution(int n, int[][] build_frame) {
        int structureCount = 0;
        
        pillars = new boolean[n + 3][n + 3];
        beams = new boolean[n + 3][n + 3];
        
        for(int[] frame : build_frame){
            int x = frame[0] + 1;
            int y = frame[1] + 1;
            int structureType = frame[2];
            int commandType = frame[3];
            
            if(commandType == CONSTRUCT){
                if(structureType == PILLAR && canConstructPillar(x, y)){
                    pillars[x][y] = true;
                    structureCount++;
                }
                if(structureType == BEAM && canConstructBeam(x, y)){
                    beams[x][y] = true;
                    structureCount++;
                }
            } else if(commandType == DESTRUCT){
                if(structureType == PILLAR){
                    pillars[x][y] = false;
                } else if(structureType == BEAM){
                    beams[x][y] = false;
                }
 
                if(canDestruct(x, y, structureType, n)){
                    structureCount--;
                    continue;
                }
                
                if(structureType == PILLAR){
                    pillars[x][y] = true;
                } else if(structureType == BEAM){
                    beams[x][y] = true;
                }
            }
        }
            
            int index = 0;
            int[][] answer = new int[structureCount][3];
            
            for(int i = 1 ; i <= n + 1 ; ++i){
                for(int j = 1 ; j <= n + 1 ; ++j){
                    if(pillars[i][j]) answer[index++] = new int[]{i - 1, j - 1, PILLAR};
                    if(beams[i][j]) answer[index++] = new int[]{i - 1, j - 1, BEAM};
                }
            }
            return answer;
    }
    
    private boolean canConstructPillar(int x, int y){
        return y == 1 || pillars[x][y - 1] || beams[x][y] || beams[x - 1][y];
    }
    
    private boolean canConstructBeam(int x, int y){
        return pillars[x][y - 1] || pillars[x + 1][y - 1] || (beams[x - 1][y] && beams[x + 1][y]);
    }
    
    private boolean canDestruct(int x, int y, int structureType, int n){       
        for(int i = 1 ; i <= n + 1 ; ++i){
            for(int j = 1 ; j <= n + 1 ; ++j){
                if(pillars[i][j] && !canConstructPillar(i, j)){
                    return false;
                }
                if(beams[i][j] && !canConstructBeam(i, j)){
                    return false;
                }
            }
        }
        
        return true;
    
}


/*
   static Map<Integer, Integer> map = new TreeMap<>();
    static int N;

    public static int[][] solution(int n, int[][] build_frame) {
      
        N=n;
        int cnt=0;

        for(int[] frame: build_frame){
            int x = frame[0];
            int y = frame[1];
            int type = frame[2];
            int action = frame[3];

            if(action == 1){
                if(type==0 && checkPillar(x, y)){
                    map.put(getKey(x, y),0);
                    cnt++;
                }
                else if(type==1 && checkBeam(x, y)){
                    map.put(getKey(x, y), 1);
                    cnt++;
                }
            }
            else if(action == 0) {
                map.remove(getKey(x, y));
                cnt--;
                if(checkDelete(x, y)==false){
                    map.put(getKey(x, y), type);
                    cnt++;
                }
                
            }

        }
        
        int[][] answer = new int[cnt][3];
        int idx=0;

        for(int key : map.keySet()){
            int[] node = getNode(key);
            answer[idx][0] = node[0];
            answer[idx][1] = node[1]; 
            answer[idx++][2] = map.get(key);
            
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] record = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
        solution(5,record);
    }

    public static int getKey(int x, int y){
        
        return x * N + y;
    }

    public static int[] getNode(int key){
        
        int[] arr = new int[2];
        arr[0] = key / N;
        arr[1] = key % N;

        return arr;
    }

    public static boolean checkDelete(int x, int y){
        
        for(int key : map.keySet()){
            int type = map.get(key);
            int arr[] = getNode(key);

            if(type==0&&checkPillar(arr[0], arr[1])==false) return false;
            else if(type==1 && checkBeam(arr[0], arr[1])==false) return false;
        }
        return true;
        
    }

    public static boolean checkBeam(int x, int y) {
        if(y>0 && map.getOrDefault(getKey(x,y-1),-1)==0||map.getOrDefault(getKey(x+1, y-1),-1)==0) return true;
        else if(x>0 && map.getOrDefault(getKey(x-1,y), -1)==1 && map.getOrDefault(getKey(x+1,y), -1) == 1) return true;
        else return false;    
    }

    public static boolean checkPillar(int x, int y){

        if(y==0) return true;
        else if(map.getOrDefault(getKey(x,y-1), -1) == 0 ) return true;
        else if(x>0 && map.getOrDefault(getKey(x-1,y),-1) == 1 || map.getOrDefault(getKey(x,y),-1)==1) return true;
        else return false;

    }
*/

