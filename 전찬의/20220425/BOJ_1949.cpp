#include<bits/stdc++.h>
using namespace std;

int N,M;
int m[10001]{};
int dp[10001][2]{};
vector<int> adj[10001]{};

void makeTree(int now, int parent){
    for(int i=0; i<adj[now].size(); i++){
        int next = adj[now][i];
        if(next==parent) continue;
        makeTree(next, now);
    }

    dp[now][1] = m[now];
    for(int i=0; i<adj[now].size(); i++){
        int next = adj[now][i];
        if(next==parent) continue;
        dp[now][0] += max(dp[next][0], dp[next][1]);
        dp[now][1] += dp[next][0];
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    int a,b;
    cin>>N;
    for(int i=1; i<=N; i++) cin>>m[i];
    for(int i=0; i<N-1; i++) {
        cin>>a>>b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    makeTree(1,0);
    cout<<max(dp[1][0],dp[1][1]);
}