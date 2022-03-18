import java.io.*;
import java.util.*;

public class Boj17609_회문 {
    static int N;

    public static void main(String[] args) throws IOException {
       

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       N = Integer.parseInt(br.readLine());
       

       for(int i=0; i<N; i++) {
           String str = br.readLine();
          System.out.println(check(str,str.length()-1,0,0));   
       }
    
    }

    public static int check(String s, int r, int l, int ans){


        while(l<r){
            
            if(s.charAt(l) == s.charAt(r)){
                l++;
                r--;
            }else{
                if(ans<1){
                    int rAns = check(s,r-1,l,ans+1);
                    int lAns = check(s,r,l+1,ans+1);
                    return Math.min(rAns, lAns);                   
                }else {
                    return ++ans;                                        
                }                             
            
            }
        }
        return ans;
    }    
}
