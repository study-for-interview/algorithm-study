#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;
using dpi = pair<pii,pii>;

vector<int> m;
vector<int> adj[17]{};
bool vi[17][1<<17]{};
int ans = 1;

void solve(int stMask, int stn){
    queue<dpi> q;
    q.push({{stMask,stn},{1,0}});
    vi[stn][stMask] = true;
    ans = 1;
    
    while(!q.empty()){
        int cmask = q.front().xx.xx;
        int now = q.front().xx.yy;
        int csheep = q.front().yy.xx;
        int cwolf = q.front().yy.yy;
        q.pop();
        
        ans = max(ans, csheep);
        
        for(int i=0; i<adj[now].size(); i++){
            int next = adj[now][i];
            int nmask = cmask|(1<<next);
            if(vi[next][nmask]) continue;
            int nsheep = csheep, nwolf = cwolf;
            if(!(cmask&(1<<next))) {
                if(m[next]) nwolf++;
                else nsheep++;
            }
            if(nsheep <= nwolf) continue;
            q.push({{nmask,next},{nsheep,nwolf}});
            vi[next][nmask] = true;
        }
    }
    
}

int solution(vector<int> info, vector<vector<int>> edges) {
    m = info;
    for(int i=0; i<edges.size(); i++) {
        int a = edges[i][0];
        int b = edges[i][1];
        adj[a].push_back(b);
        adj[b].push_back(a);
    }
    
    solve(1,0);
    return ans;
}