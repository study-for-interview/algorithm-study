#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

int T,K, p,n;
vector<pii> v;
int dp[10001]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>T>>K;
    for(int i=0; i<K; i++){
        cin>>p>>n;
        v.push_back({p,n});
    }

    dp[0] = 1;
    for(int i=0; i<K; i++){
        int cp = v[i].xx;
        int cn = v[i].yy;
        for(int t=T-cp; t>=0; t--){
            if(dp[t]==0) continue;
            for(int j=1; j<=cn; j++){
                int nt = t + cp*j;
                if(nt > T) break;
                dp[nt] += dp[t];
            }
        }
    }

    cout<<dp[T];
}