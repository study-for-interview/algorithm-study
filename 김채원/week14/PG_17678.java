import java.util.ArrayList;
import java.util.Collections;
public class Q17678 {
    static ArrayList<Integer> crewTime = new ArrayList<>();
    static int toMinute(String time){
        int minute = 0;
        String[] tmp = time.split(":");
        String tmpHour = tmp[0];
        String tmpMinute = tmp[1];
        minute += Integer.parseInt(tmpHour)*60 + Integer.parseInt(tmpMinute);
        return minute;
    }

    static String toTime(int minute){
        String hour;
        String min;
        hour = Integer.toString(minute/60);
        if(hour.length()==1){
            hour = "0"+hour;
        }
        min = Integer.toString(minute%60);
        if(min.length()==1){
            min = "0"+min;
        }
        return hour+":"+min;
    }
    public static String solution(int n, int t, int m, String[] timetable) {
        for(String time : timetable){
            if(time.equals("23:59")){
                continue;
            }
            crewTime.add(toMinute(time));
        }
        Collections.sort(crewTime);
        int index = 0;
        boolean check = false;
        int lastTime=0;
        for(int i =0; i<n; i++){
            int standardTime = 540+t*i;
            int count = 0;
            while(true){
                if(index == crewTime.size()){
                    if(i!=n-1){
                        break;
                    }
                    if(count == m){
                        check = true;
                        lastTime = crewTime.get(index-1);
                    }
                    break;

                }
                if(count == m){
                    if(i == n-1){
                        lastTime = crewTime.get(index-1);
                        check = true;
                    }
                    break;
                }
                if(crewTime.get(index)>standardTime){
                    break;
                }
                index++;
                count++;
            }
        }
        if(check){
            return toTime(lastTime-1);
        }
        return toTime(540+t*(n-1));
    }
    public static void main(String[] args){
        int n = 2;
        int t = 5;
        int m = 5;
        String[] timetable = {"08:00","09:00","09:00","09:00","09:00"};
        System.out.println(solution(n,t,m,timetable));
    }