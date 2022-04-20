#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;
using dpi = pair<pii,pii>;
const int INF = 0x3f3f3f3f;

int N,M, ans, idx;
int m[20][20]{};
int vi[20][20][1<<11]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1,};

void bfs(int sty, int stx) {
    queue<dpi> q;
    q.push({{0,1},{sty,stx}});
    vi[sty][stx][1<<0] = true;

    while(!q.empty()){
        int cd = q.front().xx.xx;
        int cm = q.front().xx.yy;
        int cy = q.front().yy.xx;
        int cx = q.front().yy.yy;
        q.pop();

        if(cm==(1<<idx)-1) ans = min(ans, cd);
        for(int i=0; i<4; i++){
            int nd = cd + 1;
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=M || m[ny][nx]==-1) continue;
            int nm = cm|(1<<m[ny][nx]);
            if(vi[ny][nx][nm]) continue;
            q.push({{nd,nm},{ny,nx}});
            vi[ny][nx][nm] = true;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    while(true){
        cin>>M>>N;
        if(!M && !N) break;

        memset(m,0,sizeof(m));
        memset(vi,0,sizeof(vi));
        ans = INF;
        idx = 1;

        int sty, stx;
        string str;
        for(int i=0; i<N; i++){
            cin>>str;
            for(int j=0; j<M; j++) {
                if(str[j]=='.') m[i][j] = 0;
                else if(str[j]=='*') m[i][j] = idx++;
                else if(str[j]=='x') m[i][j] = -1;
                else if(str[j]=='o') sty=i, stx=j;
            }
        }

        bfs(sty,stx);
        if(ans==INF) cout<<-1<<'\n';
        else cout<<ans<<'\n';
    }
}