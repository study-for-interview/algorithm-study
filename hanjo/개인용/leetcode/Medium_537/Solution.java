// https://leetcode.com/problems/complex-number-multiplication/

package leetcode.Medium_537;

public class Solution {

    public static int[] parsingStr(String num) {
        num = num.replace("i", "");
        String[] strArr = num.split("\\+");
        int[] intArr = new int[2];
        for (int i = 0; i < 2; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

    public static String complexNumberMultiply(String num1, String num2) {
        int[] arr1 = parsingStr(num1);
        int[] arr2 = parsingStr(num2);
        String real = Integer.toString((arr1[0] * arr2[0]) - (arr1[1] * arr2[1]));
        String imaginary = Integer.toString((arr1[0] * arr2[1]) + (arr1[1] * arr2[0]));
        return real + "+" + imaginary + "i";
    }

    public static void main(String[] args) {
        String num1 = "1+-1i";
        String num2 = "1+-1i";
        System.out.println(complexNumberMultiply(num1, num2));
    }

}
