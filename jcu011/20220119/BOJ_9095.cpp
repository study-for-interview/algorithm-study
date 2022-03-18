#include<bits/stdc++.h>
using namespace std;

int N;
int dp[12]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    dp[0] = 1;
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;
    for(int i=4; i<=12; i++){
        dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
    }
    
    cin>>N;
    int tmp;
    for(int i=0; i<N; i++){
        cin>>tmp;
        cout<<dp[tmp]<<'\n';
    }

}