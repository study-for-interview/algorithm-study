#include<bits/stdc++.h>
using namespace std;

int T;
long long dp[1001][10];

int main() {
    ios_base::sync_with_stdio(0); 
    cin.tie(0);
    int tmp;
    
    for(int i=0; i<10; i++) dp[1][i]=1;
    for(int i=2; i<=1000; i++){
        for(int j=0; j<10; j++){
            for(int k=0; k<=j; k++){
                dp[i][j]+=dp[i-1][k];
            }
        }
    }
        
    cin>>T;
    for(int t=0; t<T; t++){
        cin>>tmp;
        long long sum=0;
        for(int i=0; i<10; i++) sum += dp[tmp][i];
        cout <<sum<<'\n';
    }

}