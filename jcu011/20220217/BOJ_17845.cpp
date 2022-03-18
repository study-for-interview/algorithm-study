#include<bits/stdc++.h>
using namespace std;

int N,K;
int m[1001][2]{};
int dp[10001]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>K;
    for(int i=0; i<K; i++){
        for(int j=0; j<2; j++){
            cin>>m[i][j];
        }
    }

    for(int k=0; k<K; k++){
        int impt = m[k][0];
        int time = m[k][1];
        for(int n=N; n>=time; n--){
            dp[n] = max(dp[n], dp[n-time] + impt);
        }
    }

    cout<<dp[N];
}