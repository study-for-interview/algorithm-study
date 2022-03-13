package test;

import java.io.*;
// import java.util.Arrays;
import java.util.regex.*;

public class Test2 {
    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // String str = br.readLine();

        // str = str.replaceAll("\\(\\)", "(+1)");
        // System.out.println(str);
        // str = str.replaceAll("\\[\\]", "[+1]");
        // System.out.println(str);

        // Pattern pattern = Pattern.compile("(\\[\\+[1-9]+\\])|(\\(\\+[1-9]+\\))");
        // Matcher matcher = pattern.matcher(str);

        // while(matcher.find()){
        //     System.out.println(matcher.group());
        //     System.out.println(matcher.start());
        //     System.out.println(matcher.end());
        //     System.out.println();
        // }


        // System.out.println(matcher.start());
        // System.out.println(matcher.end());
        // System.out.println(matcher.group());
        


        // StringBuilder sb = new StringBuilder(str);
        // sb.replace(matcher.start(), matcher.end(), "?");

        // System.out.println(sb.toString());
        String s = "[444545][1234545]";
        Matcher m = Pattern.compile("([0-9]+)").matcher(s);

        while(m.find()){
            System.out.println(m.group());
          }
    }
}

/**
 * 정규표현식
 * https://girawhale.tistory.com/77
 * https://blog.naver.com/zzang9ha/222013925468
 * https://coding-factory.tistory.com/529
 */