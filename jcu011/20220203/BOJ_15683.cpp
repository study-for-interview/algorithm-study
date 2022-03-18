#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

int N,M, ans;
int m[9][9]{};
int dir[9][9]{};
bool vi[9][9]{};
vector<pii> v;
int dy[4] = {-1,0,1,0};
int dx[4] = {0,1,0,-1};

void setCctv(int cctv, int y, int x){
    vector<int> toDo;
    if(cctv==1) 
        toDo.push_back(dir[y][x]-1);
    else if(cctv==2) {
        if(dir[y][x]==1) {
            toDo.push_back(0);
            toDo.push_back(2);
        } else {
            toDo.push_back(1);
            toDo.push_back(3);
        }
    }
    else if(cctv==3) {
        for(int i=0; i<2; i++){
            int ndir = (i + dir[y][x] - 1)%4;
            toDo.push_back(ndir);
        }
    }
    else if(cctv==4) {
        int cdir = dir[y][x]-1;
        for(int i=0; i<4; i++){
            if(i==cdir) continue;
            toDo.push_back(i);
        }
    }
    else {
        for(int i=0; i<4; i++){
            toDo.push_back(i);
        }
    }

    vi[y][x] = true;
    for(int i=0; i<toDo.size(); i++){
        int cy = y;
        int cx = x;
        int cd = toDo[i];
        for(int j=1; ; j++) {
            int ny = cy + dy[cd]*j;
            int nx = cx + dx[cd]*j;
            if(ny<0 || nx<0 || ny>=N || nx>=M || m[ny][nx]==6) break;
            vi[ny][nx] = true;
        }
    }
}

int check() {
    memset(vi, 0, sizeof(vi));
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if(dir[i][j]==0) continue;
            setCctv(m[i][j], i,j);
        }
    }

    int res = 0;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if(m[i][j]==6 || vi[i][j]) continue;
            res++;
        }
    }
    return res;
}

void dfs(int dpt){
    if(dpt==v.size()) {
        ans = min(ans,check());
        return;
    }

    int cy = v[dpt].xx;
    int cx = v[dpt].yy;
    int cctv = m[cy][cx];
    int k=0;
    if(cctv==5) k=1;
    else if(cctv==2) k=2;
    else k=4;
    for(int j=1; j<=k; j++){
        dir[cy][cx] = j;
        dfs(dpt+1);
        dir[cy][cx] = 0;
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin>>m[i][j];
            if(m[i][j]>0 && m[i][j]<6) v.push_back({i,j});
        }
    }

    ans = N*M;
    dfs(0);
    
    cout<<ans;
}