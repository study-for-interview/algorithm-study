#include<bits/stdc++.h>
using namespace std;
const int INF = 0x3f3f3f3f;

int N;
int cl[1001][3]{};
int dp[1001][3][3]{};

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    for(int i=0; i<N; i++){
        for(int j=0;j<3; j++){
            cin>>cl[i][j];
        }
    }

    memset(dp, 0x3f, sizeof(dp));
    dp[0][0][0] = cl[0][0];
    dp[0][1][1] = cl[0][1];
    dp[0][2][2] = cl[0][2];
    for(int i=1; i<N; i++){
        for(int j=0; j<3; j++){
            for(int k=0; k<3; k++){
                int a = dp[i-1][j][(k+1)%3] + cl[i][k];
                int b = dp[i-1][j][(k+2)%3] + cl[i][k];
                dp[i][j][k] = min({dp[i][j][k], a, b});
            }
        }
    }

    N--;
    int a = min(dp[N][0][1], dp[N][0][2]);
    int b = min(dp[N][1][0], dp[N][1][2]);
    int c = min(dp[N][2][1], dp[N][2][0]);
    cout<<min({a,b,c});
}