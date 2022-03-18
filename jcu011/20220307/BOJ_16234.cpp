#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;
using tpi = tuple<int,int,int>;
const int INF = 0x3f3f3f3f;

int N,L,R;
int m[51][51]{};
queue<pii> sq;
bool vi[51][51]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};

void bfs(int sty, int stx){
    vector<pii> v;
    queue<pii> q;
    v.push_back({sty,stx});
    q.push({sty,stx});
    vi[sty][stx] = true;
    int sum = m[sty][stx];
    
    while(!q.empty()){
        int cy = q.front().xx;
        int cx = q.front().yy;
        q.pop();

        for(int i=0; i<4; i++){
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=N || vi[ny][nx]) continue;
            int diff = abs(m[cy][cx] - m[ny][nx]);
            if(diff>=L && diff<=R) {
                v.push_back({ny,nx});
                q.push({ny,nx});
                vi[ny][nx] = true;
                sum += m[ny][nx];
            }
        }
    }

    int res = sum / v.size();
    for(auto cord : v){
        int y = cord.xx;
        int x = cord.yy;
        m[y][x] = res;
        sq.push({y,x});
    }
}

bool chkAlly(int cy, int cx){
    if(vi[cy][cx]) return false;
    for(int i=0; i<4; i++){
        int ny = cy + dy[i];
        int nx = cx + dx[i];
        if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
        int diff = abs(m[cy][cx] - m[ny][nx]);
        if(diff>=L && diff<=R) return true;
    }
    return false;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>L>>R;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin>>m[i][j];
            sq.push({i,j});
        }
    }
    
    int ans = 0;
    bool chk = true;
    while(chk){
        chk = false;
        memset(vi,0,sizeof(vi));
        int sz = sq.size();
        for(int i=0; i<sz; i++){
            int cy = sq.front().xx;
            int cx = sq.front().yy;
            sq.pop();
            if(chkAlly(cy,cx)){
                bfs(cy,cx);
                chk = true;
            }
        }
        if(chk) ans++;
    }
    cout<<ans;
}