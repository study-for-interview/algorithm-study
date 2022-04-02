#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;
const int INF = 0x3f3f3f3f;

int N, M, st, ed;
vector<pii> adj[1001]{};
bool vi[1001]{};

int dfs(int now, int dist){
    if(now==ed) {
        return dist;
    }

    for(int i=0; i<adj[now].size(); i++){
        int next = adj[now][i].xx;
        int nd = dist + adj[now][i].yy;
        if(vi[next]) continue;
        vi[next] = true;
        int ret = dfs(next, nd);
        vi[next] = false;
        if(ret) return ret;
    }
    return 0;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;
    int a,b,c;
    for(int i=0; i<N-1; i++){
        cin>>a>>b>>c;
        adj[a].push_back({b,c});
        adj[b].push_back({a,c});
    }

    while (M--) {
        cin>>st>>ed;
        vi[st] = true;
        cout<<dfs(st, 0)<<'\n';
        vi[st] = false;
    }
    
}
/*
2 1 2
4 3 2
1 4 3

     1
 (2)  (3)
2        4
       (2)
      3

*/