#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> pii;
#define xx first
#define yy second

int N, M,min_=1e9;
char m[21][21]{};
vector<pii> v; // 0: 'o'위치, 1~(v.size()-1): '*'위치
int dis[21][21]{};
bool vi1[21][21]{};
bool vi2[11]{};
int dy[4] = { -1,1,0,0 };
int dx[4] = { 0,0,-1,1};

int solve1(int sty, int stx, int edy, int edx) {
	queue<pair<int, pii>> q;
	q.push({ 0,{sty,stx} });
	vi1[sty][stx] = true;

	while (!q.empty()) {
		int cd = q.front().xx;
		int cy = q.front().yy.xx;
		int cx = q.front().yy.yy;
		q.pop();

		if (cy == edy && cx == edx) return cd;

		for (int i = 0; i < 4; i++) {
			int ny = cy + dy[i];
			int nx = cx + dx[i];
			if (ny <= 0 || nx <= 0 || ny > N || nx > M || vi1[ny][nx] || m[ny][nx] == 'x') continue;
			q.push({ cd + 1,{ny,nx} });
			vi1[ny][nx] = true;
		}
	}
	return -1;
}

void solve2(int dpt, int p, int s) {
	if (dpt+1 == v.size()) {
		min_ = min(min_, s);
	}

	for (int i = 1; i < v.size(); i++) {
		if (vi2[i]) continue;
		vi2[i] = true;
		solve2(dpt + 1, i, s+dis[p][i]);
		vi2[i] = false;
	}
}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	while (1) {
		cin >> M >> N;
		if (M == 0 && N == 0) break;		
		memset(vi2, 0, sizeof(vi2));
		memset(dis, 0, sizeof(dis));
		v.clear();
		v.resize(1);
		min_ = 1e9;

		int k = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				cin >> m[i][j];
				if (m[i][j] == 'o') { v[0].xx = i; v[0].yy = j; }
				else if (m[i][j] == '*') v.push_back({i,j});
			}
		}

		int flag = false;
		for (int i = 0; i < v.size(); i++) {
			for (int j = i + 1; j < v.size(); j++) {
				memset(vi1, 0, sizeof(vi1));
				dis[i][j] = solve1(v[i].xx, v[i].yy, v[j].xx ,v[j].yy);
				dis[j][i] = dis[i][j];
				if (dis[i][j] == -1) goto end;
			}
		}
		
		solve2(0,0,0);
		cout << min_ << '\n';
		continue;

	end: cout << -1 << '\n';
	}
}