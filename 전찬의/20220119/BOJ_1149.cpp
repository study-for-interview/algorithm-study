#include<bits/stdc++.h>
using namespace std;
const int INF = 1e9;

int N;
int cost[1002][3]{};
int dp[1002][3]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    for(int i=1; i<=N; i++){
        for(int j=0; j<3; j++){
            cin>>cost[i][j];
        }
    }

    dp[1][0] = cost[1][0];
    dp[1][1] = cost[1][1];
    dp[1][2] = cost[1][2];
    for(int i=2; i<=N; i++){
        for(int j=0; j<3; j++){
            int n1 = (j+1)%3;
            int n2 = (j+2)%3;
            int t1 = dp[i-1][n1] + cost[i][j];
            int t2 = dp[i-1][n2] + cost[i][j];
            dp[i][j] = min(t1, t2);
        }
    }

    int res = INF;
    for(int i=0; i<3; i++){
        res = min(res, dp[N][i]);
    }
    cout<<res;
}