#include<bits/stdc++.h>
using namespace std;

int N;
int dp[4][10001]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    
    for(int i=0; i<=10000; i++) dp[1][i] = 1;
    dp[2][2] = 1;
    for(int i=3; i<=10000; i++){
        dp[2][i] = dp[1][i-2] + dp[2][i-2];
        dp[3][i] = dp[1][i-3] + dp[2][i-3] + dp[3][i-3];
    }

    cin>>N;
    int t;
    while(N--){
        cin>>t;
        cout<<dp[3][t]+dp[2][t]+dp[1][t]<<'\n';
    }

}