#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

int N,M;
int m[301][301]{};
int m2[301][301]{};
bool vi[301][301]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};

void melt(){
    memcpy(m2,m,sizeof(m2));
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if(m[i][j]) continue;
            for(int k=0; k<4; k++){
                int ny = i + dy[k];
                int nx = j + dx[k];
                if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
                if(m[ny][nx] && m2[ny][nx]) m2[ny][nx]--;
            }
        }
    }
    memcpy(m,m2,sizeof(m));
}

int bfs(){
    int sty=-1, stx=-1;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if(m[i][j]) {
                sty=i, stx=j;
                break;
            }
        }
        if(sty>=0) break;
    }
    if(sty<0) return 0;

    memset(vi,0,sizeof(vi));
    queue<pii> q;
    q.push({sty,stx});
    vi[sty][stx] = true;

    while(!q.empty()) {
        int cy = q.front().xx;
        int cx = q.front().yy;
        q.pop();

        for(int i=0; i<4; i++){
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=M || vi[ny][nx] || !m[ny][nx]) continue;
            q.push({ny,nx});
            vi[ny][nx] = true;
        }
    }

    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if(m[i][j] && !vi[i][j]) {
                return 2;
            }
        }
    }
    return 1;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin>>m[i][j];
        }
    }
    
    int ans = 0;
    while(true){
        int ret = bfs();
        if(ret==0) {
            cout<<0;
            break;
        } else if(ret==1) {
            melt();
        } else { // ret==2
            cout<<ans;
            break;
        }
        ans++;
    }


}