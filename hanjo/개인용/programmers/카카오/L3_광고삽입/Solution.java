package 카카오.L3_광고삽입;

class Solution {

    public String solution(String play_time, String adv_time, String[] logs) {

        // 배열 초기화
        int playTime = secOf(play_time);
        long[] views = new long[playTime+1];
        
        // 출입 현황 초기화
        for(String log : logs){
            String[] splited = log.split("-");
            int start = secOf(splited[0]);
            int end = secOf(splited[1]);
            views[start] += 1;
            views[end] -= 1;
        }

        // 청자 수 업데이트
        for(int i=1; i<playTime; i++){
            views[i] += views[i-1];
        }
        // 누적합 업데이트
        for(int i=1; i<playTime; i++){
            views[i] += views[i-1];
        }

        // 최적 광고 시간 완탐
        int advTime = secOf(adv_time);
        long max = views[advTime];
        int maxTime = 0;
        for(int t=advTime+1; t < playTime; t++){
            long sum = views[t] - views[t-advTime];
            if(sum > max){
                max = sum;
                maxTime = t-advTime+1;
            }
        }

        return strOf(maxTime);
    }

    public int secOf(String time){
        String[] splited = time.split(":");
        int h = Integer.parseInt(splited[0])*60*60;
        int m = Integer.parseInt(splited[1])*60;
        int s = Integer.parseInt(splited[2]);
        return h + m + s;
    }

    public String strOf(int time){
        String h = formatting(time/(60*60));
        time %= 60*60;
        String m = formatting(time/60);
        time %= 60;
        String s = formatting(time);

        StringBuilder sb = new StringBuilder();
        sb.append(h).append(":").append(m).append(":").append(s);
        return sb.toString();
    }

    public String formatting(int num){
        String str = String.valueOf(num);
        if(str.length() == 1){
            str = "0" + str;
        }
        return str;
    }

    public static void main(String[] args) {
        //"01:30:59"
        System.out.println(new Solution().solution("02:03:55", "00:14:15", 
            new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"}
        ));
        //"01:00:00"
        System.out.println(new Solution().solution("99:59:59", "25:00:00", 
            new String[]{"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"}
        ));
        //"00:00:00"
        System.out.println(new Solution().solution("50:00:00", "50:00:00", 
            new String[]{"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}
        ));
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/72414?language=java
 * 날짜 : 220504
 * 성공여부 : 실패 (구글링)
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : 
 * 테케12 : (251.38ms, 287MB)
 * ================================================================================
 * 
 * 누적합 문제
 * 
 * 1. 모든 재생시간을 1초단위 배열로 나누기
 * 2. 구간정보를 파싱 -> 특정 구간의 정보를 업데이트
 *      -> 시작시간에 +1, 끝 시간에 -1 해줌 (파괴되지않은건물 문제의 미니맥스?와 비슷)
 *      -> (여기서 구간 시작 ~ 끝을 순회해서 일일이 배열에 +1 해줘도 됨)
 * 3. 그 배열을 가지고 누적합 시전 -> 1초단위 시간에 있던 시청자수를 알 수 있게됨
 * 4. 여기서 한번더 누적합 -> 모든 시간에서의 누적 시청자수를 구함
 *      -> 특정 구간에서의 누적 시청자수를 손쉽게 구하기 위함
 * 5. 광고시간만큼 배열을 슬라이딩하며 순회, 최대 누적시청자수가 최초등장하는 시간 알 수 있음!
 * 
 * <시행착오>
 * 인덱스 조건이 매우 까다로웠음
 * 마지막에 슬라이딩 순회에서 1 차이로 계속 답이 안떴음.. (테케 9,31)
 * max 값을 맨처음에 초기화해줬어야 했음
 * 
 */