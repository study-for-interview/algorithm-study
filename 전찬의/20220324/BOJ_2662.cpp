#include<bits/stdc++.h>
using namespace std;

int N,M;
int m[21][301]{};
int dp[21][301]{};
int ord[21][301]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;
    int n,t;
    for(int i=1; i<=N; i++){
        cin>>n;
        for(int j=1; j<=M; j++){
            cin>>m[j][n];
        }
    }

    for(int i=1; i<=M; i++){
        for(int j=1; j<=N; j++){
            for(int cost=0; cost<=j; cost++){ // 0부터....
                int next = dp[i-1][j-cost] + m[i][cost];
                if(next > dp[i][j]){
                    dp[i][j] = next;
                    ord[i][j] = cost;
                }
            }
        }
    }

    cout<<dp[M][N]<<'\n';

    int now = M;
    int cost = N;
    vector<int> ans;
    while(now > 0) {
        int amount = ord[now][cost];
        ans.push_back(amount);
        cost -= amount;
        now--;
    }
    reverse(ans.begin(), ans.end());
    for(auto val : ans) cout<<val<<' ';
}

/*
in:
5 5
1 5 4 3 2 1
2 1 5 4 3 2
3 2 1 5 4 3
4 11 9 9 9 9
5 9 9 9 9 9

out:
15
1 1 1 1 1

--------------
in:
4 2
1 5 1
2 6 5
3 7 9
4 10 15

out:
15
0 4
*/
