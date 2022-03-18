#include<bits/stdc++.h>
using namespace std;

int N,K,W,V;
int dp[100001]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>K;
    
    for(int i=0; i<N; i++)  {
        cin>>W>>V;
        for(int j=K; j>=W; j--){
            dp[j] = max(dp[j], dp[j-W] + V);
        }
    }

    cout<<dp[K];
}