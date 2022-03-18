#include<bits/stdc++.h>
using namespace std;
const int MOD = 10000;

int N,K;
int m[101]{};
int dp[101][4][4]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    
    cin>>N>>K;
    for(int i=0; i<K; i++){
        int a,b;
        cin>>a>>b;
        m[a] = b;
    }
    for(int i=1; i<=3; i++){
        if(m[2]!=0 && m[2]!=i) continue;
        for(int j=1; j<=3; j++){
            if(m[1]!=0 && m[1]!=j) continue;
            dp[2][i][j] = 1;
        }
    }
    
    for(int n=3; n<=N; n++){
        int chk = m[n];
        if(chk==1 || chk==0){
            dp[n][1][1] = (dp[n-1][1][2] + dp[n-1][1][3])%MOD;
            dp[n][1][2] = (dp[n-1][2][1] + dp[n-1][2][2] + dp[n-1][2][3])%MOD;
            dp[n][1][3] = (dp[n-1][3][1] + dp[n-1][3][2] + dp[n-1][3][3])%MOD;
        }
        if(chk==2 || chk==0){
            dp[n][2][1] = (dp[n-1][1][1] + dp[n-1][1][2] + dp[n-1][1][3])%MOD;
            dp[n][2][2] = (dp[n-1][2][1] + dp[n-1][2][3])%MOD;
            dp[n][2][3] = (dp[n-1][3][1] + dp[n-1][3][2] + dp[n-1][3][3])%MOD;
        }
        if(chk==3 || chk==0){
            dp[n][3][1] = (dp[n-1][1][1] + dp[n-1][1][2] + dp[n-1][1][3])%MOD;
            dp[n][3][2] = (dp[n-1][2][1] + dp[n-1][2][2] + dp[n-1][2][3])%MOD;
            dp[n][3][3] = (dp[n-1][3][1] + dp[n-1][3][2])%MOD;
        }
    }
    
    int ans = 0;
    for(int i=1; i<=3; i++){
        if(m[N]!=0 && m[N]!=i) continue;
        for(int j=1; j<=3; j++){
            if(m[N-1]!=0 && m[N-1]!=j) continue;
            ans = (ans + dp[N][i][j])%MOD;
        }
    }
    cout<<ans;
}

