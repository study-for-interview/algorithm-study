#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;
using tpi = pair<int,pii>;

int N,M,D, enemy=0, ans=0;
int m[17][17]{};
int tmap[17][17]{};
bool vi[17][17]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};
vector<pii> v;

pii bfs(int sty, int stx) {
    memset(vi, 0, sizeof(vi));
    queue<tpi> q;
    q.push({0,{sty,stx}});
    vi[sty][stx] = true;
    int mind=D, miny=N, minx=M;

    while(!q.empty()){
        int cd = q.front().xx;
        int cy = q.front().yy.xx;
        int cx = q.front().yy.yy;
        q.pop();

        for(int i=0; i<4; i++){
            int nd = cd + 1;
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
            if(vi[ny][nx] || nd>D) continue;
            q.push({nd,{ny,nx}});
            vi[ny][nx] = true;
            if(tmap[ny][nx]) {
                if(nd == mind) {
                    if(nx<minx) {
                        miny = ny;
                        minx = nx;
                    }
                } else if(nd < mind){
                    mind = nd;
                    miny = ny;
                    minx = nx;
                }
            }
        }
    }

    return pii(miny,minx);
}


int solve() {
    int ret=0, removed=0;
    memcpy(tmap, m, sizeof(tmap));

    while(ret+removed < enemy){
        vector<pii> target;
        for(int i=0; i<v.size(); i++)
            target.push_back(bfs(v[i].xx, v[i].yy));
        
        for(int i=0; i<target.size(); i++){
            int y = target[i].xx;
            int x = target[i].yy;
            if(tmap[y][x]) {
                tmap[y][x] = 0;
                ret++;
            }
        }

        for(int j=0; j<M; j++)
            if(tmap[N-1][j]==1) removed++;
        
        int i, cnt=0;
        for(i=N-1; i>0; i--){
            for(int j=0; j<M; j++){
                tmap[i][j] = tmap[i-1][j];
                if(tmap[i][j]) cnt++;
            }
            if(cnt+ret+removed == enemy) break;
        }
        for(int j=0; j<M; j++) tmap[i-1][j] = 0;
    }
    
    return ret;
}

void dfs(int dpt, int now) {
    if(dpt==3) {
        ans = max(ans, solve());
        return;
    }

    for(int j=now+1; j<M; j++){
        v.push_back({N,j});
        dfs(dpt+1, j);
        v.pop_back();
    }

}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M>>D;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin>>m[i][j];
            if(m[i][j]) enemy++;
        }
    }

    dfs(0,-1);
    cout<<ans;
}