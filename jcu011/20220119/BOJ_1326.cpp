#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;
const int INF = 0x3f3f3f3f;

int N,a,b;
int m[10000]{};
int dp[10010]{};

void dijk(int st){
    priority_queue<pii, vector<pii>, greater<pii>> pq;
    pq.push({0, st});
    dp[st] = 0;

    while(!pq.empty()){
        int cd = pq.top().xx;
        int cp = pq.top().yy;
        pq.pop();

        if(cd > dp[cp]) continue;

        for(int k=cp+m[cp]; k<N && k>=0; k+=m[cp]) {
            if(cd+1 < dp[k]) {
                pq.push({cd+1, k});
                dp[k] = cd+1;
            }
        }

        for(int k=cp-m[cp]; k<N && k>=0; k-=m[cp]) {
            if(cd+1 < dp[k]) {
                pq.push({cd+1, k});
                dp[k] = cd+1;
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    memset(dp, 0x3f, sizeof(dp));
    for(int i=0; i<N; i++) cin>>m[i];
    cin>>a>>b;

    dijk(a-1);

    if(dp[b-1]==INF) cout<<-1;
    else cout<<dp[b-1];
}