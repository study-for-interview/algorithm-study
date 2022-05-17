#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;
using dpi = pair<pii,pii>;

int N,M,ans=0;
int m[21][21]{};
bool vi[21][21]{};
bool vi2[21][21]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};

void rotateBlock(){
    int tmp[21][21]{};
    memcpy(tmp, m, sizeof(tmp));
    for(int i=0,jj=N-1; i<N; i++,jj--){
        for(int j=0,ii=0; j<N; j++,ii++){
            m[i][j] = tmp[ii][jj];
        }
    }
}

pii bfs(int sty, int stx){
    memset(vi,0,sizeof(vi));
    queue<pii> q;
    q.push({sty,stx});
    vi[sty][stx] = true;
    vi2[sty][stx] = true;
    int cnt = 1;
    int rainbow = 0;

    while(!q.empty()){
        int cy = q.front().xx;
        int cx = q.front().yy;
        q.pop();

        for(int i=0; i<4; i++){
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=N || vi[ny][nx]) continue;
            if(m[ny][nx]==0 || m[ny][nx]==m[sty][stx]) {
                q.push({ny,nx});
                vi[ny][nx] = true;
                if(!m[ny][nx]) rainbow++;
                else vi2[ny][nx] = true;
                cnt++;
            }
        }
    }
    return pii(cnt, rainbow);
}

void makeGravity(){
    vector<int> v;
    for(int j=0; j<N; j++){
        for(int i=0; i<=N; i++){
            if(i!=N && m[i][j]>=0 && m[i][j]<=M) {
                v.push_back(m[i][j]);
                m[i][j] = 9;
            }
            if(v.size() && (i==N || m[i][j]==-1)) {
                int ti = i-1, tj = j;
                for(int k=v.size()-1; k>=0; k--){
                    m[ti--][tj] = v[k];
                }
                v.clear();
            }
        }
    }
}

bool eraseBlock(){
    vector<dpi> v;
    memset(vi2,0,sizeof(vi2));
    for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++){
            if(vi2[i][j]) continue;
            if(m[i][j]>0 && m[i][j]<=M) {
                pii tmp = bfs(i,j);
                if(tmp.xx < 2) continue;
                v.push_back({tmp, {i,j}});
            }
        }
    }
    if(v.size()==0) return false;
    sort(v.begin(), v.end(), greater<dpi>());
    int score = bfs(v[0].yy.xx, v[0].yy.yy).xx;
    ans += (score*score);
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(vi[i][j]) m[i][j] = 9;
        }
    }
    return true;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin>>m[i][j];
        }
    }

    while(eraseBlock()){
        makeGravity();
        rotateBlock();
        makeGravity();
    }
    cout<<ans;
}