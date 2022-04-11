import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
       	int answer = 0;
		PriorityQueue<Integer> q = new PriorityQueue<>();
		int T = 9*60;
		List<Integer>[] list = new List[n];
        
		for (int i = 0; i < timetable.length; i++) {
			int hh = Integer.parseInt(timetable[i].split(":")[0]);
			int mm = Integer.parseInt(timetable[i].split(":")[1]);
			int time = hh*60 + mm;
			
			q.add(time);
			
		}
		

		for (int i = 0; i < n; i++) {
			list[i] = new ArrayList<>();
            
			while(!q.isEmpty()) {
				int arrived = q.poll();

				if(arrived <= T && list[i].size() < m) {
					list[i].add(arrived);
				}
				else {
					q.add(arrived);
					break;
				}
				answer = arrived-1;
			}

			T += t;
		}

		if(list[n-1].size() < m) {
			answer = T - t;
		}

		String hh = String.format("%02d",answer/60);
		String mm = String.format("%02d",answer%60);

        return hh+":"+mm;
    }
}
