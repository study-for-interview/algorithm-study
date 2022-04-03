package BOJ;

import java.io.BufferedReader; import java.io.IOException; import java.io.InputStreamReader;

public class Boj16120_PPAP {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
    public static void main(String[] args) throws IOException { 
        if (isPpapStr()) { 
            System.out.println("PPAP"); 
        } else { System.out.print("NP"); 
    } 
} 
    private static boolean isPpapStr() throws IOException {
        boolean isPpap = false; 
        String str = br.readLine();
        int strLen = str.length(); 
        int cnt = 0; 

        if(str.charAt(strLen-1) == 'P'){ 
            isPpap = true; 
            for (int i = 0; i < strLen; i++) { 
                char curCh = str.charAt(i); 
                if (curCh == 'P') { 
                    cnt++; 
                } else if (curCh == 'A') { 
                    if (str.charAt(i + 1) != 'P' || cnt < 2) { 
                        isPpap = false; 
                        break; 
                    } 
                    cnt -= 2; 
                } 
            } 
            if (cnt != 1) isPpap = false;

        } 
        return isPpap; 
    }

}
