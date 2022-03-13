package 구현.S2_괄호의값;
// package 자료구조_S2_괄호의값;

// import java.io.*;

// public class Try {

//     private static String str;

//     public static boolean isValid(String str) {
//         int count1 = 0, count2 = 0;

//         for (String s : str.split("")) {
//             if (s.equals("(")) {
//                 count1++;
//             } else if (s.equals(")")) {
//                 count1--;
//             }
//             if (s.equals("[")) {
//                 count2++;
//             } else if (s.equals("]")) {
//                 count2--;
//             }
//         }
//         if(count1 == 0 && count2 == 0)
//             return true;
//         else
//             return false;
//     }

//     public static String[] split(String str, String regex){
//         String[] splited = str.split(regex);
//         if(splited.length != 1){
//             splited[0] = splited[0].substring(1, splited[0].length() - 1);
//             int lastIdx = splited.length - 1;
//             splited[lastIdx] = splited[lastIdx].substring(0, splited[lastIdx].length() - 2);
//             return splited;
//         }
//         else{
//             return new String[]{};
//         }
//     }

//     public static int reculsive(String str) {
//         int result = 0;

//         if (isValid(str)) {
//             for (String splited : split(str, ")(")) {
//                 result += 2 * reculsive(splited);
//             }
//             for (String splited : split(str, "[]")) {
//                 result += 3 * reculsive(splited);
//             }
//         } 
//         else {
//             return 0;
//         }
//         return 0;
//     }

//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//         str = br.readLine();

//         // reculsive(str);

//         System.out.println(isValid(str));
//     }

// }
