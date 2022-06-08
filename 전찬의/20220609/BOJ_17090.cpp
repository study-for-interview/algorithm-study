#include<bits/stdc++.h>
using namespace std;

int N,M;
int m[501][501]{};
int dp[501][501]{};
int dy[4] = {-1,0,1,0};
int dx[4] = {0,1,0,-1};

int solve(int y, int x){
    if(y<0 || x<0 || y>=N || x>=M) return 1;
    if(dp[y][x]!=-1) return dp[y][x];
    int &ret = dp[y][x];
    ret = 0;
    int ny = y + dy[m[y][x]];
    int nx = x + dx[m[y][x]];
    ret = solve(ny, nx);
    return ret;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    memset(dp,-1,sizeof(dp));
    cin>>N>>M;
    string str;
    for(int i=0; i<N; i++){
        cin>>str;
        for(int j=0; j<M; j++){
            char now = str[j];
            if(now=='U') {
                m[i][j] = 0;
            } else if(now=='R'){
                m[i][j] = 1;
            } else if(now=='D'){
                m[i][j] = 2;
            } else {//now=='L'
                m[i][j] = 3;
            }
        }
    }

    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            solve(i,j);
        }
    }
    int ans = 0;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if(dp[i][j]==1) ans++;
        }
    }
    cout<<ans;
}