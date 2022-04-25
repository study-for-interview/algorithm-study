package kakao;

import java.util.*;

public class Pms_표편집 {

    public static String solution(int n, int k, String[] cmd) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int size = n;
        int idx = k;

        for(String query : cmd){
            char key = query.charAt(0);
            switch(key){ 
                case 'U': 
                    idx-= Integer.parseInt(query.substring(2)); 
                    break; 
                case 'D': 
                    idx+= Integer.parseInt(query.substring(2)); 
                    break; 
                case 'C': 
                    stack.push(idx); 
                    size--;
                    if(idx==size) idx--;

                    break;
                case 'Z': 
                // 인덱스보다 위에 있었던 데이터라면 데이터 밀려남
                    size++;
                    if(stack.pop()<=idx) idx++;

                    break; 
                }
                
        }

        for(int i=0; i<size;i++){
            sb.append("O");
        }
        while(!stack.isEmpty()){
            sb.insert(stack.pop().intValue(), "X");
        }


        return sb.toString();

    }

    public static void main(String[] args) {
        String[] cnd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        System.out.println(solution(8,2,cnd));
    }

}


// stack, linkedlist 사용 (시간초과) -> 삭제나 추가시 인덱스 탐색 시간 때문에? -> sb에서 중간 삽입가능(insert) -> 현재 위치 인덱스만 알면된다.