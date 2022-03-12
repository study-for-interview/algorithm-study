package 카카오.L3_추석트래픽;

import java.util.*;
import java.text.*;

public class Try1 {

    public static class Log {
        public long start;
        public long end;

        public Log(String S, String T){
            try{
                // 종료시간 (밀리세컨드)
                this.end = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(S).getTime();
                // 시작시간
                this.start = this.end - (long)(Float.parseFloat(T))*1000+ 1;
            }
            catch(Exception e){
            }
        }
    }

    public static int solution(String[] lines) {

        ArrayList<Log> logs = new ArrayList<>();

        for(String line : lines){
            String[] splited = line.split(" ");
            String S = splited[0] + " " + splited[1];
            String T = splited[2].replace("s", "");
            logs.add(new Log(S, T));
        }
        
        int max = 0;
        for(Log cur : logs){
            int count = 0;
            for(Log next : logs){
                if(cur.start <= next.start && next.start < cur.end + 1*1000){
                    count++;
                }
            }
            max = Math.max(max, count);
        }

        return max;
    }


    public static void main(String[] args) {

        // 1
        System.out.println(solution(new String[]{
            "2016-09-15 01:00:04.001 2.0s",
            "2016-09-15 01:00:07.000 2s"
        }));

        // 2
        System.out.println(solution(new String[]{
            "2016-09-15 01:00:04.002 2.0s",
            "2016-09-15 01:00:07.000 2s"
        }));

        // 7
        System.out.println(solution(new String[]{
            "2016-09-15 20:59:57.421 0.351s",
            "2016-09-15 20:59:58.233 1.181s",
            "2016-09-15 20:59:58.299 0.8s",
            "2016-09-15 20:59:58.688 1.041s",
            "2016-09-15 20:59:59.591 1.412s",
            "2016-09-15 21:00:00.464 1.466s",
            "2016-09-15 21:00:00.741 1.581s",
            "2016-09-15 21:00:00.748 2.31s",
            "2016-09-15 21:00:00.966 0.381s",
            "2016-09-15 21:00:02.066 2.62s"
        }));
    }
}

/**
 * < 파싱 >
 * 1. 일단 빈칸 기준으로 자름
 * 2. S와 T를 만들어줌
 * 3. S - T 해서 작업 시작 시간 구함
 * 4. 작업을 클래스로 만들고 리스트에 넣고 순회
 * ---------------------
 * 1. 작업을 하나씩 순회
 * 2. 시작시간 ~ (종료시간 + 1초) 내에 존재하는 작업 개수를 세고 max 업데이트
 *      -> 어케세지? -> 시작시간이 저 범위내로 존재하는 작업을 세면됨
 */

 /**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/17676
 * 성공여부 : 실패 (테케 5개틀림)
 * 풀이시간 : 2h
 * ================================================================================
 * 
 * 문자열을 시간으로 파싱하고, 구간을 잘 설정해야 하는 문제 
 * 뭔가 처음 생각했던 풀이에 갇혀서 결국 끝까지 풀지 못했음 (다시 풀어야함)
 * 
 */