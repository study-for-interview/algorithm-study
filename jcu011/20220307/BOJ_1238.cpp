#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int, int>;
const int INF = 0x3f3f3f3f;

int N,M,X;
vector<pii> adj1[1001]{};
vector<pii> adj2[1001]{};
int dp1[1001]{};
int dp2[1001]{};

void dijk(int st, int *dp, vector<pii> *adj){
    priority_queue<pii, vector<pii>, greater<pii>> pq;
    pq.push({0, st});
    dp[st] = 0;

    while(!pq.empty()) {
        pii tmp = pq.top();
        int cd = tmp.xx;
        int cp = tmp.yy;
        pq.pop();

        if(dp[cp] < cd) continue;
        for(int i=0; i<adj[cp].size(); i++){
            int nd = adj[cp][i].yy + cd;
            int np = adj[cp][i].xx;
            if(nd < dp[np]) {
                dp[np] = nd;
                pq.push({nd,np});
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M>>X;
    int a,b,c;
    while(M--) {
        cin>>a>>b>>c;
        adj1[a].push_back({b,c});
        adj2[b].push_back({a,c});
    }

    memset(dp1, 0x3f, sizeof(dp1));
    memset(dp2, 0x3f, sizeof(dp2));
    dijk(X, dp1, adj1);
    dijk(X, dp2, adj2);

    int ans = 0;
    for(int i=1; i<=N; i++) ans = max(ans, dp1[i] + dp2[i]);
    cout<<ans;
}