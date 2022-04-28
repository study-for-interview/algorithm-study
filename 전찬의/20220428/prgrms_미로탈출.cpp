#include<bits/stdc++.h>
using namespace std;
using tpi = tuple<int,int,int>;
const int INF = 0x3f3f3f3f;

vector<tpi> adj[1001]{};
int trapChk[1001]{};
int dp[1001][1<<10];

void dijk(int st){
    memset(dp,0x3f,sizeof(dp));
    priority_queue<tpi, vector<tpi>, greater<tpi>> pq;
    pq.push({0,st,0}); // dist, node, mask
    dp[st][0] = 0;
    
    while(!pq.empty()){
        int cd, cn, cm, ct;
        tie(cd, cn, cm) = pq.top();
        pq.pop();
        if(trapChk[cn]) ct = (cm&(1<<(trapChk[cn]-1)))>0 ? 1 : 0;
        else ct = -1;

        if(cd > dp[cn][cm]) continue;
        for(int i=0; i<adj[cn].size(); i++){
            int nn, nd, t, nt;
            tie(nn, nd, t) = adj[cn][i];
            if(trapChk[nn]) nt = (cm&(1<<(trapChk[nn]-1)))>0 ? 1 : 0;
            else nt = -1;
            int nm = cm;
            
            if(ct==-1 && nt==-1) ;
            else if(ct!=-1 && nt==-1) { // cur만 trap
                if(ct!=t) continue;
            } else if(ct==-1 && nt!=-1) { // next만 trap
                if(nt!=t) continue;
                nm = nm^(1<<(trapChk[nn]-1));
            } else { // cur, next 둘다 trap
                if(!(ct^nt) && t) continue;
                if((ct^nt) && !t) continue;
                nm = nm^(1<<(trapChk[nn]-1));
            }

            nd += cd;
            if(nd < dp[nn][nm]){
                pq.push({nd,nn,nm});
                dp[nn][nm] = nd;
            }
        }
    }
}

int solution(int n, int start, int end, vector<vector<int>> roads, vector<int> traps) {
    int bitIdx = 1;
    for(int i=0; i<traps.size(); i++) {
        trapChk[traps[i]] = bitIdx++;
    }
    for(int i=0; i<roads.size(); i++) {
        int a = roads[i][0];
        int b = roads[i][1];
        int c = roads[i][2];
        adj[a].push_back({b,c,0});
        if(trapChk[a] || trapChk[b]) adj[b].push_back({a,c,1});
    }
    
    dijk(start);
    int ans = INF;
    for(int i=0; i<(1<<traps.size()); i++){
        ans = min(ans, dp[end][i]);
    }
    return ans;
}