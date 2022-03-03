import java.io.*;
import java.util.*;

public class Boj13335_트럭 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());

		Queue<Integer> truck = new LinkedList<Integer>();
		st = new StringTokenizer(br.readLine());

		for(int i= 0; i< n; i++) {
			truck.offer(Integer.parseInt(st.nextToken()));
		}

		int time = 0; 
		int bw=0;

		Queue<Integer> bridge = new LinkedList<Integer>();

		for(int i =0; i<l ; i++) {
			bridge.add(0);
		}

		while(!bridge.isEmpty()) {
			time++;
			bw-=bridge.poll();

			if(!truck.isEmpty()) {
				if(truck.peek()+bw<=w) {
					bw+=truck.peek();
					bridge.offer(truck.poll());
				}else {
					bridge.offer(0);
				}
			}
		}

		System.out.println(time);
	}
}