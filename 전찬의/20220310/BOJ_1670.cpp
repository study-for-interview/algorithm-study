#include <bits/stdc++.h>
using namespace std;
using ll = long long int;
const int MOD = 987654321;

int N;
ll dp[10010];

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cin>>N;
    N>>=1;

    dp[0] = 1;
    for(int i=1; i<=N; i++){
        for(int j=0; j<i; j++){
            dp[i] += (dp[j] * dp[i-1-j]) % MOD;
        }
        dp[i] %= MOD;
    }

    cout<<dp[N];
}
