#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

char m[5][5]{};
int tmap[5][5]{};
int ans = 0;

bool vi[5][5]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};

bool bfs(int y, int x){
    memset(vi,0,sizeof(vi));
    queue<pii> q;
    q.push({y,x});
    vi[y][x] = true;

    int cnt = 1;
    while(!q.empty()){
        int cy = q.front().xx;
        int cx = q.front().yy;
        q.pop();

        for(int k=0; k<4; k++){
            int ny = cy + dy[k];
            int nx = cx + dx[k];
            if(ny<0 || nx<0 || ny>=5 || nx>=5) continue;
            if(vi[ny][nx] || tmap[ny][nx]==0) continue;
            q.push({ny,nx});
            vi[ny][nx] = true;
            cnt++;
        }
    }
    if(cnt==7) return true;
    else return false;
}

int cc = 0;
void dfs(int dpt, int now, int ds){
    if(dpt==7) {
        if(ds<4) return;
        if(bfs(now/5, now%5)) ans++;
        return;
    }

    for(int i=now+1; i<25; i++){
        int cy = i/5;
        int cx = i%5;
        int nds = ds + (m[i/5][i%5]=='S' ? 1 : 0);
        if(6-dpt+nds < 4) continue;
        tmap[cy][cx] = 1;
        dfs(dpt+1, i, nds);
        tmap[cy][cx] = 0;
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    for(int i=0; i<5; i++){
        for(int j=0; j<5; j++){
            cin>>m[i][j];
        }
    }

    dfs(0,-1, 0);
    cout<<ans;
}