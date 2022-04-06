#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;
using tpi = tuple<int,int,int>;

int N, y, x, sz=2, eaten=0;
int m[21][21]{};
int dy[4] = {-1,0,0,1};
int dx[4] = {0,-1,1,0};
bool vi[21][21]{};

int bfs(int sty, int stx) {
    vector<pii> fish;
    int fishDist = 500;
    memset(vi,0,sizeof(vi));
    queue<tpi> q;
    q.push({0, sty, stx});
    vi[sty][stx] = true;

    while(!q.empty()){
        int cd, cy, cx;
        tie(cd, cy, cx) = q.front();
        q.pop();

        for(int i=0; i<4; i++){
            int nd = cd + 1;
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
            if(vi[ny][nx] || m[ny][nx] > sz || nd>fishDist) continue;
            if(m[ny][nx]>0 && sz>m[ny][nx]){
                fishDist = nd;
                fish.push_back({ny,nx});
            }
            q.push({nd, ny, nx});
            vi[ny][nx] = true;
        }
    }

    if(fish.size()==0) return 0;
    else {
        sort(fish.begin(), fish.end(), [&](pii a, pii b) -> bool {
            if(a.xx == b.xx) return a.yy < b.yy;
            else return a.xx < b.xx;
        });
        int ny = fish[0].xx;
        int nx = fish[0].yy;
        m[ny][nx] = 0;
        y = ny;
        x = nx;
        if(++eaten==sz) {
            eaten = 0;
            sz++;
        }
        return fishDist;
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
            cin>>m[i][j];
            if(m[i][j]==9) {
                y = i;
                x = j;
                m[i][j] = 0;
            }
        }
    }

    int ans = 0;
    while(1) {
        int chk = bfs(y,x);
        if(!chk) break;
        ans += chk;
    }
    cout<<ans;
}
