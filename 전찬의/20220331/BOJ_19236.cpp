#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;
using dpi = pair<pii,pii>;

int ans = 0;
int m[4][4]{};
dpi fish[17]; // {{좌표},{방향,isDead}}
int dy[8] = {-1,-1,0,1,1,1,0,-1};
int dx[8] = {0,-1,-1,-1,0,1,1,1};

void fishMove(){
    for(int i=1; i<=16; i++){
        if(fish[i].yy.yy) continue;
        int cy = fish[i].xx.xx;
        int cx = fish[i].xx.yy;
        int cd = fish[i].yy.xx;
        int nd, ny, nx;
        for(int k=0; k<8; k++){
            nd = (cd+k)%8;
            ny = cy + dy[nd];
            nx = cx + dx[nd];
            if(ny<0 || nx<0 || ny>=4 || nx>=4 || !m[ny][nx]) continue;
            fish[i].yy.xx = nd;
            int next = m[ny][nx];
            swap(fish[i].xx, fish[next].xx);
            swap(m[cy][cx], m[ny][nx]);
            break;
        }
    }
}

void solve(int sc){
    ans = max(ans, sc);

    pii tM[4][4]{};
    dpi tFish[17];
    memcpy(tM, m, sizeof(tM));
    memcpy(tFish, fish, sizeof(tFish));
    fishMove();

    int cy = fish[0].xx.xx;
    int cx = fish[0].xx.yy;
    int cd = fish[0].yy.xx;
    int nd, ny, nx, next;
    for(int i=1; i<=3; i++){
        ny = cy + dy[cd]*i;
        nx = cx + dx[cd]*i;
        if(ny<0 || nx<0 || ny>=4 || nx>=4) continue;
        next = m[ny][nx];
        if(fish[next].yy.yy) continue;
        swap(m[cy][cx], m[ny][nx]);
        swap(fish[0].xx, fish[next].xx);
        swap(fish[0].yy, fish[next].yy);
        fish[next].yy.yy = 1;
        solve(sc+next);
        fish[next].yy.yy = 0;
        swap(fish[0].yy, fish[next].yy);
        swap(fish[0].xx, fish[next].xx);
        swap(m[cy][cx], m[ny][nx]);
    }

    memcpy(m, tM, sizeof(m));
    memcpy(fish, tFish, sizeof(fish));
}

int main() {
	ios_base::sync_with_stdio(false); 
    cin.tie(0);
    int a,b;
    for(int i=0; i<4; i++){
        for(int j=0; j<4; j++){
            cin>>a>>b;
            m[i][j] = a;
            fish[a] = {{i,j},{b-1,0}};
        }
    }

    int now = m[0][0];
    fish[0] = fish[now];
    fish[now].yy.yy = 1;
    int sc = now;
    m[0][0] = 0;
    
    solve(sc);
    cout<<ans;
}