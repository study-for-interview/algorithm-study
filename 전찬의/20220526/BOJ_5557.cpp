#include<bits/stdc++.h>
using namespace std;
using ll = long long;

ll N;
ll m[105]{};
ll dp[105][21]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    for(ll i=0; i<N; i++){
        cin>>m[i];
    }

    dp[0][m[0]] = 1;
    for(ll i=1; i<(N-1); i++){
        ll now = m[i];
        for(ll j=0; j<=20; j++){
            if(j-now >= 0) dp[i][j] += dp[i-1][j-now];
            if(j+now <= 20) dp[i][j] += dp[i-1][j+now];
        }
    }
    cout<<dp[N-2][m[N-1]];
}