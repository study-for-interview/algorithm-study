#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;
const int INF = 0x3f3f3f3f;

int N,M, wall=0, ans=INF;
int m[51][51]{};
bool vi[51][51]{};
vector<pii> virus, isp;
int dy[4] = {-1,0,1,0};
int dx[4] = {0,1,0,-1};

int bfs(){
    int ret = 0;
    int cnt = virus.size();
    memset(vi, 0, sizeof(vi));
    queue<pair<int,pii>> q;
    for(auto v : virus) {
        q.push({0,{v.xx, v.yy}});
        vi[v.xx][v.yy] = true;
    }

    while(!q.empty()){
        int cd = q.front().xx;
        int cy = q.front().yy.xx;
        int cx = q.front().yy.yy;
        q.pop();

        ret = max(ret, cd);

        for(int i=0; i<4; i++){
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
            if(vi[ny][nx] || m[ny][nx]==1) continue;
            q.push({cd+1, {ny,nx}});
            vi[ny][nx] = true;
            cnt++;
        }
    }

    if(cnt == (N*N-wall)) return ret;
    else return INF;
}

void dfs(int dpt, int pre){
    if(dpt==M) {
        ans = min(ans, bfs());
        return;
    }

    for(int i=pre+1; i<isp.size(); i++){
        virus.push_back(isp[i]);
        dfs(dpt+1, i);
        virus.pop_back();
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;

    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin>>m[i][j];
            if(m[i][j]==1) wall++;
            if(m[i][j]==2) isp.push_back({i,j});
        }
    }

    dfs(0,-1);
    if(ans==INF) cout<<-1;
    else cout<<ans;
}