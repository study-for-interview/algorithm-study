#include<bits/stdc++.h>
using namespace std;

int N,M;
int m[501][501]{};
int dp[501][501]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};

int solve(int y, int x) {
    if(y==0 && x==0) return 1;
    if(dp[y][x]!=-1) return dp[y][x];
    int &ret = dp[y][x];
    ret = 0;
    for(int i=0; i<4; i++){
        int ny = y + dy[i];
        int nx = x + dx[i];
        if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
        if(m[ny][nx] > m[y][x]) ret += solve(ny, nx);
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    memset(dp,-1,sizeof(dp));
    cin>>N>>M;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin>>m[i][j];
        }
    }

    cout<<solve(N-1, M-1);
}
