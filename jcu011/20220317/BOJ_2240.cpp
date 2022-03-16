// 25분
#include<bits/stdc++.h>
using namespace std;

int T,W;
int dp[1001][32][2]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>T>>W;

    int np;
    cin>>np;
    dp[1][0][0] = (np==1 ? 1 : 0);
    dp[1][1][1] = (np==1 ? 0 : 1);
    for(int i=2; i<=T; i++){
        cin>>np;
        for(int j=0; j<=W; j++){
            for(int k=0; k<2; k++){
                dp[i][j][k] = max(dp[i][j][k], dp[i-1][j][k] + (np==k+1 ? 1 : 0));
                if(j!=0) dp[i][j][k] = max(dp[i][j][k], dp[i-1][j-1][!k] + (np==k+1 ? 1 : 0));
            }
        }
    }

    int ans = 0;
    for(int i=0; i<=W; i++){
        for(int j=0; j<2; j++){
            ans = max(ans, dp[T][i][j]);
        }
    }
    cout<<ans;
}