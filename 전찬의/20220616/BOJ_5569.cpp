#include <bits/stdc++.h>
using namespace std;
const int MOD = 1e5;

int N,M;
int dp[102][102][4]{};
int dy[2] = {1,0};
int dx[2] = {0,1};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;

    dp[2][0][0] = 1;
    dp[1][1][1] = 1;
    dp[1][1][2] = 1;
    dp[0][2][3] = 1;

    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            int ny1 = i + dy[0];
            int nx1 = j + dx[0];
            dp[ny1][nx1][0] = (dp[ny1][nx1][0] + dp[i][j][0] + dp[i][j][2])%MOD;
            dp[ny1][nx1][2] = (dp[ny1][nx1][2] + dp[i][j][3])%MOD;
            int ny2 = i + dy[1];
            int nx2 = j + dx[1];
            dp[ny2][nx2][1] = (dp[ny2][nx2][1] + dp[i][j][0])%MOD;
            dp[ny2][nx2][3] = (dp[ny2][nx2][3] + dp[i][j][1] + dp[i][j][3])%MOD;
        }
    }
    int sum = 0;
    for(int i=0; i<4; i++) sum = (sum + dp[N-1][M-1][i])%MOD;
    cout<<sum;
}
