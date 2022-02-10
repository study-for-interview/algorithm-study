package baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());


        Queue<Integer> Q=new LinkedList<>();
        int back = 0;


        for(int i = 0;i<n;i++) {
            String cons = br.readLine();
            if(cons.contains("push")) {
                String spt[] = cons.split(" ");
                back = Integer.parseInt(spt[1]);
                Q.offer(Integer.parseInt(spt[1]));
            }else if(cons.contains("pop")) {
                if(Q.isEmpty()) bw.write(-1+"\n");
                else bw.write(Q.poll()+"\n");
            }else if(cons.contains("size")) {
                bw.write(Q.size()+"\n");
            }else if(cons.contains("empty")) {
                if(Q.isEmpty()) bw.write(1+"\n");
                else bw.write(0+"\n");
            } else if (cons.contains("front")) {
                if (Q.isEmpty()) bw.write(-1 + "\n");
                else bw.write(Q.peek() + "\n");
            } else if (cons.contains("back")) {
                System.out.println(Q.isEmpty()?-1:back);
            }
        }

        bw.flush();
        bw.close();
        br.close();

    }

}