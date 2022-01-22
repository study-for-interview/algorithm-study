#include<bits/stdc++.h>
using namespace std;
using ll = long long;

int N;
ll dp[61][61]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    while(cin>>N) {
        if(N==0) break;
        memset(dp, 0, sizeof(dp));

        dp[0][0] = 1;
        dp[1][1] = 1;
        for(int i=2; i<=N*2; i++){
            dp[0][i] += dp[1][i-1];
            for(int j=1; j<N*2-i+1; j++){
                dp[j][i] += dp[j+1][i-1];
                dp[j][i] += dp[j-1][i-1];
            }
        }

        cout<<dp[0][N*2]<<'\n';
    }
    
}