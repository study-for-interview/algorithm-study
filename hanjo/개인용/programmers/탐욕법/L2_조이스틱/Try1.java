package 탐욕법.L2_조이스틱;

public class Try1 {

    public static int solution(String name) {

        int len = name.length();

        int visitCount = 0;
        int moveCount = 0;
        int index = 0;
        int direction = 1;

        while(visitCount < len){
            moveCount += moveAlphabet(name.charAt(index));

            if(index == 0 && isBack(name)){
                index = len;
                direction = -1;
                moveCount --;
            }

            index += direction;
            visitCount++;
            moveCount ++;
        }

        return moveCount - 1;
    }

    public static boolean isBack(String name){

        if(name.length()/2 <= name.indexOf("A")){
            return true;
        }
        return false;
    }

    public static int moveAlphabet(char target){
        // A - 65 / Z - 90
        final int targetNum = (int)target;
        final int ANum = (int)'A';
        final int ZNum = (int)'Z';

        if(targetNum > (ZNum + ANum)/2){
            return ZNum - targetNum + 1;
        }
        else{
            return targetNum - ANum;
        }
    }

    public static void main(String[] args){
        // 56
        System.out.println(solution("JEROEN"));
        // 23
        System.out.println(solution("JAN"));
        // 7
        System.out.println(solution("ABAAAAAAAAABB"));
        // 10
        System.out.println(solution("BBBBAAAAAB"));
        // 12
        System.out.println(solution("BBBBAAAABA"));
    }
}
