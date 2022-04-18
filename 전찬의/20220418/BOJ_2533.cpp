#include<bits/stdc++.h>
using namespace std;
const int MAX = 1000000;

int N;
vector<int> adj[MAX+1]{};
vector<int> tree[MAX+1]{};
bool vi[MAX+1]{};
int dp[MAX+1][2]{};

void makeTree(int now){
    vi[now] = true;
    for(int i=0; i<adj[now].size(); i++){
        int next = adj[now][i];
        if(vi[next]) continue;
        tree[now].push_back(next);
        makeTree(next);
    }
}

void solve(int now){
    if(tree[now].size()==0) return;

    for(int i=0; i<tree[now].size(); i++){
        int next = tree[now][i];
        solve(next);
    }
    
    for(int i=0; i<tree[now].size(); i++){
        int next = tree[now][i];
        dp[now][0] += dp[next][1];
        dp[now][1] += min(dp[next][1], dp[next][0]);
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    int a,b;
    
    for(int i=0; i<N; i++){
        cin>>a>>b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }
    makeTree(1);
    
    for(int i=1; i<=N; i++) dp[i][1] = 1;
    solve(1);
    cout<<min(dp[1][0], dp[1][1]);
}   