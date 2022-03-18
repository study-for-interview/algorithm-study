#include<bits/stdc++.h>
using namespace std;

int N,K;
int m[101][4]{};
int dp[101][100001]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>K;
    for(int i=1; i<=N; i++) {
        cin>>m[i][0]>>m[i][1]>>m[i][2]>>m[i][3];
    }
    
    dp[1][m[1][0]] = m[1][1];
    dp[1][m[1][2]] = max(dp[1][m[1][2]], m[1][3]);
    for(int i=2; i<=N; i++) {
        for(int j=0; j<2; j++){
            int time = m[i][0+j*2];
            int fund = m[i][1+j*2];
            for(int k=time; k<=K; k++){
                if(dp[i-1][k-time]==0) continue;
                dp[i][k] = max(dp[i][k], dp[i-1][k-time]+fund);
            }
        }
    }
    
    int ans = 0;
    for(int k=0; k<=K; k++) ans = max(ans, dp[N][k]);
    cout<<ans;
}