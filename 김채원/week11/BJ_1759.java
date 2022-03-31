import java.io.*;
import java.util.*;


class BJ_1759 {
    static int L, C, vowel;
    static char[] chrType;
    static char[] combi;

    public void DFS(int depth, int s) {
        if (depth == L) {
            vowel = 0;
            for (char x : combi) {
                if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u') {
                    vowel++;
                }
            }

            int consonant = L - vowel; // 자음 갯수
            if (vowel >= 1 && consonant >= 2) {
                for (char x : combi) {
                    System.out.print(x);
                }
                System.out.println();
            }

        } else {
            for (int i = s; i < chrType.length; i++) {
                combi[depth] = chrType[i];
                DFS(depth + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BJ_1759 T = new BJ_1759();
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        combi = new char[L];
        chrType = new char[C];
        String str = br.readLine();
        String[] strr = str.split(" ");
        for (int i = 0; i < C; i++) {
            chrType[i] = strr[i].charAt(0);
        }
        Arrays.sort(chrType);
        T.DFS(0, 0);
    }
}
