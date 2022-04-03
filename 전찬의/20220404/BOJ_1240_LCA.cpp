#include<iostream>
#include<cmath>
#include<algorithm>
#include<vector>
#define xx first
#define yy second
#define MAX_N 1000
using namespace std;
typedef pair<int,int> pii;

int N, M, maxPower = 10; // (int)floor(log2(MAX_N));
int ac[MAX_N+1][21]{};
int dpt[MAX_N+1]{};
int dist[MAX_N+1]{};
vector<pii> adj[MAX_N+1]{};

void dfs(int now, int parent, int nd) {
    dpt[now] = dpt[parent] + 1;
    ac[now][0] = parent;
    dist[now] = nd;

    for (int i = 1; i <= maxPower; i++) 
        ac[now][i] = ac[ac[now][i-1]][i-1];

    for (auto next : adj[now]) {
        if (next.xx == parent) continue;
        dfs(next.xx, now, nd + next.yy);
    }
}

int lca(int x, int y) {
    if (dpt[x] > dpt[y]) swap(x, y);
    for (int i = maxPower; i >= 0; i--) { 
        if (dpt[y] - dpt[x] >= (1 << i)) 
            y = ac[y][i];
    }
    if (x == y)return x; 
    for (int i = maxPower; i >= 0; i--) {
        if (ac[x][i] != ac[y][i]) {
            x = ac[x][i];
            y = ac[y][i];
        }
    }
    return ac[x][0];
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;

    int x,y,c;
    for (int i = 0; i < N-1; i++) {
        cin>>x>>y>>c;
        adj[x].push_back({y,c});
        adj[y].push_back({x,c});
    }

    dfs(1, 0, 0);

    while (M--) {
        cin>>x>>y;
        int common = lca(x, y);
        cout<<dist[x] + dist[y] - dist[common]*2<<'\n';
    }
}