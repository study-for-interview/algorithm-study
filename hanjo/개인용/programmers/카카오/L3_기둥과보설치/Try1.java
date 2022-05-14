package 카카오.L3_기둥과보설치;

import java.util.*;

class Try1 {

    public static class Struct implements Comparable<Struct>{
        public int x;
        public int y;
        public int type;
        
        public Struct(int x, int y, int type){
            this.x = x; 
            this.y = y;
            this.type = type;
        }

        @Override
        public int compareTo(Struct o){
            if(this.x == o.x){
                return this.y - o.y;
            }
            return this.x - o.x;
        }

        @Override
        public String toString(){
            return x + "," + y + "," + type;
        }
    }

    public Set<Struct> structs;

    public int[][] solution(int n, int[][] build_frame) {

        structs = new TreeSet<>();
        
        for(int[] frame : build_frame){
            int x = frame[0];
            int y = frame[1];
            int type = frame[2];
            int cmd = frame[3];
            Struct struct = new Struct(x, y, type);

            System.out.println();
            System.out.println(structs);
            System.out.println(structs.hashCode());
            System.out.println(x + "," + y + "," + type + "," + cmd);

            // 삭제
            if(cmd == 0){
                structs.remove(struct);
                if(!isValid()){
                    System.out.println("삭제실패");
                    structs.add(struct);
                }
            }
            // 설치
            else{
                structs.add(struct);
                if(!isValid()){
                    System.out.println("설치실패");
                    structs.remove(struct);
                }

            }
        }

        int size = structs.size();
        int[][] answer = new int[size][3];
        int i=0;
        for(Struct struct : structs){
            answer[i][0] = struct.x;
            answer[i][1] = struct.y;
            answer[i][2] = struct.type;
            i++;
        }
        return answer;
    }

    public boolean isValid(){
        for(Struct struct : structs){
            // 기둥
            if(struct.type == 0){

                if(struct.x == 2 && struct.y == 2 && struct.type == 0){
                    System.out.println("ssssssssss");
                    Struct target = new Struct(2, 2, 0);
                    Struct target2 = new Struct(2, 2, 0);

                    System.out.println(target.hashCode());
                    System.out.println(target2.hashCode());


                    System.out.println(isExist(struct.x, struct.y-1, 0));
                    // Struct temp1 = new Struct(struct.x, struct.y-1, 0);
                    // System.out.println(temp1);
                    // System.out.println(temp1.hashCode());
                    // System.out.println(target.equals(temp1));
                    // System.out.println(target.equals(target2));

                    // System.out.println(isExist(struct.x-1, struct.y, 1));
                    // System.out.println((struct.x-1) + "," + (struct.y) + "," + 1);
                    // System.out.println(isExist(struct.x, struct.y, 1));
                    // System.out.println((struct.x) + "," + (struct.y) + "," + 1);

                }

                if(isExist(struct.x, struct.y-1, 0) || 
                    isExist(struct.x-1, struct.y, 1) ||
                    isExist(struct.x, struct.y, 1) ||
                    struct.y == 0
                ){
                    continue;
                }
                else{
                    return false;
                }
            }
            // 보
            else{
                if(isExist(struct.x, struct.y-1, 0) || 
                    isExist(struct.x+1, struct.y-1, 0) ||
                    (isExist(struct.x-1, struct.y, 1) && isExist(struct.x+1, struct.y, 1))
                ){
                    continue;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isExist(int x, int y, int type){
        return structs.contains(new Struct(x, y, type));
        // for(Struct struct : structs){
        //     if(struct.x == x && struct.y == y && struct.type == type){
        //         return true;
        //     }
        // }
        // return false;
    }

    public static void main(String[] args) {
        // // [[1,0,0],[1,1,1],[2,1,0],[2,2,1],[3,2,1],[4,2,1],[5,0,0],[5,1,0]]
        // System.out.println(Arrays.deepToString(new Solution().solution(
        //     5, new int[][]{{1,0,0,1}, {1,1,1,1}, {2,1,0,1}, {2,2,1,1}, {5,0,0,1}, {5,1,0,1}, {4,2,1,1}, {3,2,1,1}}
        // )));
        // [[0,0,0],[0,1,1],[1,1,1],[2,1,1],[3,1,1],[4,0,0]]
        System.out.println(Arrays.deepToString(new Try1().solution(
            5, new int[][]{{0,0,0,1}, {2,0,0,1}, {4,0,0,1}, {0,1,1,1}, {1,1,1,1}, {2,1,1,1}, {3,1,1,1}, {2,0,0,0}, {1,1,1,0}, {2,2,0,1}}
        )));
    }
}

// 해시코드까지 재정의 해줘야함
// 자바에서는 이렇게 풀지 말자