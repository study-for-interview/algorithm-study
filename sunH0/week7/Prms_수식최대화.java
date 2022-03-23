import java.util.*;

public class Prms_수식최대화 {

    static char op[] = {'+','-','*'};
    static long answer = 0;
    static boolean visit[] = new boolean[3];
    static ArrayList<Long> arrList = new ArrayList<>();
    static ArrayList<Character> opList = new ArrayList<>();
    
    public static long solution(String expression) {
        answer = 0;
        
        String num ="";
        for(int i=0; i<expression.length(); i++) {
                if(expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                    num += expression.charAt(i);
                }else {
                    arrList.add(Long.parseLong(num));
                    num = "";
                    opList.add(expression.charAt(i));
                }
        }

        arrList.add(Long.parseLong(num));
        
        dfs(0, new char[3]);
        
        return answer;
    }

    public static void dfs(int count, char p[]) {
        
            if(count == 3) {
                ArrayList<Long> arrNum = new ArrayList<>(arrList);
                ArrayList<Character> arrOp = new ArrayList<>(opList);
                
                for(int i=0; i<p.length; i++) {
                    for(int j=0; j<arrOp.size(); j++) {
                        if(p[i] == arrOp.get(j)) {
                            Long result = calc(arrNum.remove(j), arrNum.remove(j), p[i]);
                            arrNum.add(j, result);
                            arrOp.remove(j);
                            j--;
                        }
                    }
                }
                
                answer = Math.max(answer, Math.abs(arrNum.get(0)));
                return;
            }
            
            
            for(int i=0; i<3; i++) {
                if(!check[i]) {
                    check[i] = true;
                    p[count] = op[i];
                    dfs(count+1, p);
                    check[i] = false;
                }
            }
    }

    public static long calc(long num1, long num2, char op){
        if(op == '+'){
            return num1 + num2;
        }
        else if(op == '-'){
            return num1 - num2;
        }
        else{
            return num1 * num2;
        }
        
    }
    
}
