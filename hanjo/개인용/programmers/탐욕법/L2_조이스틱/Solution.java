package 탐욕법.L2_조이스틱;
// package 탐욕법_2_조이스틱;

// public class Solution {

//     // A - 65 / Z - 90
//     public static final int A = 'A';
//     public static final int Z = 'Z';

//     public static int solution(String name) {

//         int len = name.length();

//         boolean[] isCheck = new boolean[len];
//         for(int i=0; i<len; i++){
//             if(name.charAt(i) == 'A'){
//                 isCheck[i] = true;
//             }
//             isCheck[i] = false;
//         }

//         int cur = 0;
//         int direction = 0;
//         int count = 0;
//         while(true){
//             // 알파벳 변경
//             count += moveAlphabet(name.charAt(cur));
//             isCheck[cur] = true;

//             // 이동 방향 결정
//             for(int i=cur; i<len; i++){
//                 if(!isCheck[i]){

//                 }
//             }
//         }
//     }

//     public static int moveAlphabet(char target){

//         if(target > (Z + A)/2){
//             return Z - target + 1;
//         }
//         else{
//             return target - A;
//         }
//     }


//     public static void main(String[] args){
//         // 56
//         System.out.println(solution("JEROEN"));
//         // 23
//         System.out.println(solution("JAN"));

//         // 앞으로 가다가 뒤로 가는경우
//         // 7
//         System.out.println(solution("ABAAAAAAAAABB"));

//         // 뒤로 갔다가 다시 앞으로 가는경우
//         // 10
//         System.out.println(solution("BBBBAAAAAB"));
//         // 12
//         System.out.println(solution("BBBBAAAABA"));
//     }
// }
