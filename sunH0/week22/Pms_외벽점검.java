package kakao;

public class Pms_외벽점검 {

    static int[] nw;
    static boolean[] visit;
    static int answer, wl;

    public static int solution(int n, int[] weak, int[] dist) {
        answer = -1;
        wl = weak.length;
        
        nw = new int[weak.length*2-1];
        visit = new boolean[dist.length];
		int idx = 0;
        
		while(idx<nw.length) {
			if(idx<weak.length) nw[idx] = weak[idx];
			else nw[idx] = n+weak[idx-weak.length];
			idx++;
		}

        for (int i = 1; i <= dist.length; i++) {
			dfs(dist, visit, 0, i, new int[i]);
		}

        return answer;
    }

    static void dfs(int[] dist, boolean[] visit, int depth, int cnt, int[] tmp) {
        if (answer != -1) {
			return;
		}
		if (depth == cnt) {
			check(tmp);
			return;
		}

        for (int i = 0; i < dist.length; i++) {
			if (visit[i]) {
				continue;
			}
			tmp[depth] = dist[i];
			visit[i] = true;
			dfs(dist, visit, depth+1, cnt, tmp);
			visit[i] = false;
		}

    }

    static void check(int[] tmp) {
		outer : for (int i = 0; i < wl; i++) {
			int start = i;
			int f = 0;
			for (int j = i; j < i + wl; j++) {
				if (nw[j] - nw[start] > tmp[f]) {
					start = j;
					f++;
				}
				if (f == tmp.length) {
					continue outer;
				}
			}
			answer = tmp.length;
			return;
		}
	}
    
}
