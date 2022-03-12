package 스위핑.G3_수상택시;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] routes = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            routes[i][0] = Integer.parseInt(st.nextToken());
            routes[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(m, routes));
    }

    public static class Route implements Comparable<Route>{
        public int x;
        public int y;


        public Route(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Route o){
            int result = this.x - o.x;
            if(result == 0){
                result = this.y - o.y;
            }
            return result;
        }

        @Override
        public String toString(){
            return "(x : " + x + " / y : " + y + " )";
        }
    }

    public static int solution(int m, int[][] routes){
        // 역방향 모으기
        ArrayList<Route> reverse = new ArrayList<>();
        for(int[] route : routes){
            if(route[0] > route[1]){
                reverse.add(new Route(route[1], route[0]));
            }
        }

        Collections.sort(reverse);
        // System.out.println(reverse);

        int last = reverse.get(0).y;
        int len = reverse.get(0).y - reverse.get(0).x;
        for(int i=1; i<reverse.size(); i++){
            if(last >= reverse.get(i).x){
                if(last < reverse.get(i).y){
                    len += reverse.get(i).y - last;
                    last = reverse.get(i).y;
                }
            }
            else{
                len += reverse.get(i).y - reverse.get(i).x;
                last = reverse.get(i).y;
            }
        }
        
        return m + len*2;
    }

    public static int solution1(int m, int[][] routes){
        // 역방향 모으기
        PriorityQueue<Route> reverse = new PriorityQueue<>();
        for(int[] route : routes){
            if(route[0] > route[1]){
                reverse.add(new Route(route[1], route[0]));
            }
        }

        // System.out.println(reverse);

        // 역방향들을 스위핑하고 길이 합
        int sum = 0;
        Route wrap = reverse.poll();
        while(!reverse.isEmpty()){

            Route next = reverse.poll();
            if(wrap.y >= next.x){
                if(wrap.y < next.y){
                    wrap.y = next.y;
                }
            }
            else{
                sum += wrap.y - wrap.x;
                wrap.x = next.x;
                wrap.y = next.y;
            }

            // System.out.println(next);
            // System.out.println(wrap);
            // System.out.println();

        }
        sum += wrap.y - wrap.x;
        
        return m + sum*2;
    }
}

/**
 * ================================================================================
 * 링크 : https://www.acmicpc.net/problem/2836
 * 성공여부 : 포기
 * 풀이시간 : 
 * 
 * 시간복잡도 : 
 * 메모리 : KB
 * 소요 시간 : ms
 * ================================================================================
 * 
 * 스위핑으로 푸는 문제.
 * 오른쪽 방향으로 가는 손님의 거리는 안세도 된다.
 * (어차피 내가 집갈때 0->M 만큼 가니까 그 안에 포함됨)
 * 
 * 따라서 역방향(왼->오) 손님의 거리만 구하면 되는데, 이 경로들을 스위핑으로 묶으면 됨.
 * 그리고 역방향으로 가면 왕복하는 것이니까 -> M + 2*(묶은 경로들의 합) 가 답이된다.
 * 
 * 근데 제출시 7%에서 틀렸다고 뜨는데 1시간동안 반례를 찾아도 뭐가 틀린지 몰라서 포기함
 * 
 */