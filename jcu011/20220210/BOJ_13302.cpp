#include<bits/stdc++.h>
using namespace std;

int N,M;
int dp[101][50]{};
int vi[101]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    
    int t;
    cin>>N>>M;
    for(int i=0; i<M; i++) {
        cin>>t;
        vi[t] = true;
    }

    memset(dp, 0x3f, sizeof(dp));
    dp[0][0] = 0;
    for(int i=1; i<=N; i++){
        for(int j=0; j<50; j++){
            if(i-5>=0 && j-2>=0) dp[i][j] = min(dp[i][j], dp[i-5][j-2]+37000);
            if(i-3>=0 && j-1>=0) dp[i][j] = min(dp[i][j], dp[i-3][j-1]+25000);
            if(vi[i]) {
                dp[i][j] = min(dp[i][j], dp[i-1][j]);
                continue;
            }
            if(j-3>=0) dp[i][j-3] = min(dp[i][j-3], dp[i-1][j]);
            dp[i][j] = min(dp[i][j], dp[i-1][j]+10000);
        }
    }

    int ans = 0x3f3f3f3f;
    for(int j=0; j<5; j++) ans = min(ans, dp[N][j]);
    cout<<ans;
}