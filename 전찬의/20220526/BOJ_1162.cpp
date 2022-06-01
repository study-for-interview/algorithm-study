#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using ll = long long;
using pll = pair<ll,ll>;
using tpl = tuple<ll,ll,ll>;
const ll INF = 0x3f3f3f3f3f3f3f3f * 2;

ll N,M,K;
vector<pll> adj[10001]{};
ll dp[10001][21]{};

void dijk(){
    priority_queue<tpl, vector<tpl>, greater<tpl>> pq;
    pq.push({0,1,0}); // dist, node, count
    dp[1][0] = 0;

    while(!pq.empty()){
        ll cd, cn, ccnt;
        tie(cd, cn, ccnt) = pq.top();
        pq.pop();

        if(cd > dp[cn][ccnt]) continue;
        for(auto val : adj[cn]){
            ll nd = cd + val.yy;
            ll nn = val.xx;
            if(nd < dp[nn][ccnt]) {
                dp[nn][ccnt] = nd;
                pq.push({nd, nn, ccnt});
            }
            if(ccnt+1 > K) continue;
            if(cd < dp[nn][ccnt+1]){
                dp[nn][ccnt+1] = cd;
                pq.push({cd, nn, ccnt+1});
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    memset(dp,0x3f,sizeof(dp));
    cin>>N>>M>>K;
    ll a,b,c;
    for(ll i=0; i<M; i++) {
        cin>>a>>b>>c;
        adj[a].push_back({b,c});
        adj[b].push_back({a,c});
    }

    dijk();

    ll ans = INF;
    for(ll i=0; i<=K; i++){
        ans = min(ans, dp[N][i]);
    }
    cout<<ans;
}