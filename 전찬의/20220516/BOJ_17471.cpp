#include<bits/stdc++.h>
using namespace std;
const int INF = 0x3f3f3f3f;

int N,M,t;
int m[11]{};
vector<int> adj[11]{};
bool maskVi[1<<10]{};
bool vi[11]{};

int bfs(int mask) {
    memset(vi,0,sizeof(vi));
    queue<int> q;
    int cnt = 0;
    int sum = 0;
    for(int i=0; i<N; i++){
        if(mask&(1<<i)) {
            q.push(i);
            vi[i] = true;
            sum += m[i];
            cnt++;
            break;
        }
    }

    while(!q.empty()) {
        int now = q.front();
        q.pop();

        for(int i=0; i<adj[now].size(); i++){
            int next = adj[now][i];
            if(vi[next] || !(mask&(1<<next))) continue;
            q.push({next});
            vi[next] = true;
            cnt++;
            sum += m[next];
        }
    }

    if(cnt == __builtin_popcount(mask)) return sum;
    else return 0;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    for(int i=0; i<N; i++) cin>>m[i];
    for(int i=0; i<N; i++) {
        cin>>M;
        for(int j=0; j<M; j++){
            cin>>t;
            adj[i].push_back(t-1);
        }
    }

    int ans = INF;
    for(int mask=1; mask<(1<<N)-1; mask++){
        if(maskVi[mask]) continue;
        int tmask = mask;
        for(int i=0; i<N; i++) tmask ^= (1<<i);
        maskVi[mask] = true;
        maskVi[tmask] = true;
        int sum1 = bfs(mask);
        if(sum1) {
            int sum2 = bfs(tmask);
            if(sum2) {
                ans = min(ans, abs(sum1-sum2));
            }
        }
    }
    if(ans==INF) cout<<-1;
    else cout<<ans;
}