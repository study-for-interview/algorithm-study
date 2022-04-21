package 비트마스킹;

import java.util.*;
import java.io.*;

public class Boj4991_로봇청소기 {


	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static char[][] map; // 지도 정보
	static int memo[][]; // 메모할 배열
	static int steps[][]; // 거리 저장
	static final int MAX = 987654321; // 최댓값
	static int N, M; // 가로 세로 크기  
	static LinkedList<Point> dusts = new LinkedList<>();
	static Queue<Point> q = new LinkedList<>();

	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (true) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0) { // 종료 조건
				System.out.println(sb.toString());
				return;
			}
			
			map = new char[M][N];
			dusts.clear();
			// 입력
			int idx = 1;
			for (int i = 0; i < M; i++) {
				String tmp = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = tmp.charAt(j);
					if (map[i][j] == 'o') { // 시작위치는 0번째에 저장
						dusts.add(0, new Point(i,j));
						map[i][j] = 'A';
					} else if (map[i][j] == '*') {
						dusts.add(new Point(i, j));
						map[i][j] = (char) (idx++ + 65);
					}
				}
			}
			steps = new int[dusts.size()][dusts.size()];
			initSteps(); // 각 거리 미리 계산함
			memo = new int[dusts.size()][1 << dusts.size()];
			for (int i = 0; i < memo.length; i++) {
				Arrays.fill(memo[i], -1);
			}

			int result = tsp(0, 1);
			sb.append(result == MAX ? -1 : result).append("\n");

		}
	}

	private static void initSteps() {
		for (int i = 0; i < dusts.size(); i++) {
			getDists(i);
		}
	}

	private static int tsp(int curr, int state) {
		if ((1 << dusts.size()) - 1 == state) { // 다 방문했으면
			return 0;
		}
		if (memo[curr][state] >= 0) // 저장된 값이 있으면
			return memo[curr][state];

		memo[curr][state] = MAX;
		for (int i = 0; i < dusts.size(); i++) {
			if ((state & (1 << i)) == 0 && steps[curr][i] > 0) {
				memo[curr][state] = Math.min(memo[curr][state], tsp(i, state | (1 << i)) + steps[curr][i]);
			}
		}
		return memo[curr][state];
	}

	private static void getDists(int loc) {
		// dust의 i번째에서 다른 알파벳까지 거리 계산
		q.clear();
		Point start = new Point(dusts.get(loc).x, dusts.get(loc).y);
		q.add(start);
		boolean[][] chk = new boolean[M][N];
		chk[start.x][start.y] = true;
		int cnt = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point curr = q.poll();
				if (map[curr.x][curr.y] - 65 >= 0 && map[curr.x][curr.y] - 65 <= 11) { // 더러운 곳이 10개를 넘지 않으므로 + 시작위치 = 11
					steps[loc][map[curr.x][curr.y] - 65] = cnt;
					steps[map[curr.x][curr.y] - 65][loc] = cnt;
				}
				for (int j = 0; j < 4; j++) {
					int nx = curr.x + dx[j];
					int ny = curr.y + dy[j];
					if (nx > -1 && ny > -1 && nx < M && ny < N && map[nx][ny] != 'x' && !chk[nx][ny]) {
						chk[nx][ny] = true;
						q.add(new Point(nx, ny));
					}
				}
			}
			cnt++;
		}
	}

}

//bfs