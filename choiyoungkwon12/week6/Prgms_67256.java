package Programmers;

/**
 * https://programmers.co.kr/learn/courses/30/lessons/67256 2020 카카오 인턴십 키패드 누르기
 */
public class Solution19 {

    static int lHand, rHand;

    public static void main(String[] args) {
        Solution19 s = new Solution19();
        int[] numbers = new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";
        String result = s.solution(numbers, hand);
        System.out.println("result = " + result);
        if (result.equals("LRLLLRLLRRL")) {
            System.out.println("true");
        }
    }

    public static int getLength(int index, int number) {

        // 숫자 0의 경우 11로 치환
        index = (index == 0) ? 11 : index;
        number = (number == 0) ? 11 : number;

        int x = (index - 1) / 3;
        int y = (index - 1) % 3;
        int numberX = number / 3;
        int numberY = 1;

        return Math.abs(x - numberX) + Math.abs(y - numberY);
    }

    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        lHand = 10;
        rHand = 12;

        for (int number : numbers) {
            if (number == 0) {
                number = 11;
            }

            if (number % 3 == 1) { // Left
                sb.append("L");
                lHand = number;
            } else if (number % 3 == 0) {    // Right
                sb.append("R");
                rHand = number;
            } else { // Center
                int leftLength = getLength(lHand, number);
                int rightLength = getLength(rHand, number);

                if (leftLength > rightLength) {
                    sb.append("R");
                    rHand = number;
                } else if (leftLength < rightLength) {
                    sb.append("L");
                    lHand = number;
                } else {
                    boolean result = hand.equals("right");

                    sb.append(result ? "R" : "L");

                    if (result) {
                        rHand = number;
                    } else {
                        lHand = number;
                    }
                }
            }
        }

        return sb.toString();
    }
}
