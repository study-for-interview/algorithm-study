package 탐욕법.L2_조이스틱;


class Answer {
    
    public int solution(String name) {
        
        int answer = 0;
        int move = name.length()-1;

        for(int i = 0; i<name.length(); i++){
            answer += upDown(name.charAt(i));

            int idx = i + 1;
            while(idx < name.length() && name.charAt(idx) == 'A'){
                idx++;
            }

            move = Math.min(move, i + name.length() - idx + Math.min(i, name.length() - idx));
        }    

        return answer + move;
    }

    public int upDown(char ch){
        return Math.min(ch-'A', 'Z'-ch+1);
    }
}
