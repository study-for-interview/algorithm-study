#include<bits/stdc++.h>
using namespace std;

int N, M, K;
string str;
char m[101][101]{};
int dp[101][101][81]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};

int solve(int dpt, int cy, int cx) {
    if(dpt==0) return 1;
    if(dp[cy][cx][dpt]!=-1) return dp[cy][cx][dpt];
    int &ret = dp[cy][cx][dpt];
    ret = 0;
    for(int i=0; i<4; i++){
        for(int k=1; k<=K; k++){
            int ny = cy + dy[i]*k;
            int nx = cx + dx[i]*k;
            if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
            if(m[ny][nx]==str[dpt-1]) {
                ret += solve(dpt-1, ny, nx);
            }
        }
    }
    return ret;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M>>K;
    string tmp;
    for(int i=0; i<N; i++){
        cin>>tmp;
        for(int j=0; j<M; j++){
            m[i][j] = tmp[j];
        }
    }
    cin>>str;

    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if(m[i][j]==str[0]) {
                dp[i][j][0] = 1;
            }
        }
    }
    
    memset(dp, -1, sizeof(dp));
    int cnt = 0;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if(m[i][j]==str[str.size()-1]) {
                cnt += solve(str.size()-1, i, j);
            }
        }
    }
    cout<<cnt;
}
