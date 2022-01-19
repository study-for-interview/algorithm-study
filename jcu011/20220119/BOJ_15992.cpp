#include<bits/stdc++.h>
using namespace std;
using ll = long long;
const int MOD = 1e9 + 9;

int N;
ll dp[1001][1001]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    dp[0][0] = 1;
    dp[1][1] = 1;
    dp[2][1] = 1;
    dp[2][2] = 1;
    dp[3][1] = 1;
    dp[3][2] = 2;
    dp[3][3] = 1;
    for(int i=4; i<=1000; i++){
        for(int j=2; j<=i; j++){
            dp[i][j] = (dp[i-1][j-1] + dp[i-2][j-1] + dp[i-3][j-1])%MOD;
        }
    }
    
    cin>>N;
    int a,b;
    for(int i=0; i<N; i++){
        cin>>a>>b;
        cout<<dp[a][b]<<'\n';
    }
}