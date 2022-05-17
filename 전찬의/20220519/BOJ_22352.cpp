// 5:55
#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

int N,M;
int m[31][31]{};
int m2[31][31]{};
bool vi[31][31]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};

void bfs(int sty, int stx){
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
            if(ny<0 || nx<0 || ny>=N || nx>=M || vi[ny][nx] || m[ny][nx]!=m[sty][stx]) continue;
            q.push({ny,nx});
            vi[ny][nx] = true;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;
    for(int i=0; i<N; i++) {
        for(int j=0; j<M; j++){
            cin>>m[i][j];
        }
    }
    
    for(int i=0; i<N; i++) {
        for(int j=0; j<M; j++){
            cin>>m2[i][j];
        }
    }

    bool chk = false;
    bool ret = true;
    int vac = 0;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if(!vi[i][j] && (m[i][j]!=m2[i][j])) {
                if(chk) ret = false;
                bfs(i,j);
                chk = true;
                vac = m2[i][j];
            }
        }
    }
    if(!ret) cout<<"NO";
    else {
        if(!vac) {
            cout<<"YES";
            return 0;
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(vi[i][j]) m[i][j] = vac;
                if(m[i][j] != m2[i][j]) {
                    cout<<"NO";
                    return 0;
                }
            }
        }
        cout<<"YES";
    }
}