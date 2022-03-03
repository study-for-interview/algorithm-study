import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BJ_16472 {

    public static void main(String[] args) throws Exception{

        //고양이 투포인터
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split("");

        int s=0;//시작
        int e=0;//끝
        int max=0;//최대길이 반환
        //주어진 문자열 길이가 주어진 알파벳 종류 보다 짧다면 바로 return
        if(n>=str.length) {
            System.out.println(str.length);
            return;
        }
        Map<String,Integer> map = new HashMap<>();
        int preS = s; //s포인터만 움직였을 경우를 판단

        while(s<=e && e < str.length) {
            //s==e 이거나 s++이 되어 preS!=s일 경우에는 e가 중복으로 들어가게 됨.
            //이를 해결하기 위한 조건문
            if((s!=e || e==0) && preS==s)
                map.put(str[e], map.getOrDefault(str[e], 0) + 1);

            //알파벳 종류가 넘지 않을 경우 최대 길이 갱신
            if(map.size() <= n) {
                max = Math.max(max, e-s+1);
                e++;
                preS = s;
            }
            //넘는 다면 s++하고 해당 알파벳의 value값 -1
            else {
                int num = map.get(str[s])-1;
                if(num== 0)
                    map.remove(str[s]);
                else
                    map.put(str[s], num);
                s++;
            }
        }
        System.out.println(max);
    }

}