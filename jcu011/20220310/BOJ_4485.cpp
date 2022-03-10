#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using tpi = tuple<int,int,int>;

int N;
int m[126][126]{};
int dp[126][126]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};

void dijk(){
    priority_queue<tpi, vector<tpi>, greater<tpi>> pq;
    pq.push({m[0][0],0,0});
    dp[0][0] = m[0][0];

    while(!pq.empty()){
        int cd, cy, cx;
        tie(cd, cy, cx) = pq.top();
        pq.pop();

        if(cd > dp[cy][cx]) continue;
        for(int i=0; i<4; i++){
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
            int nd = cd + m[ny][nx];
            if(nd < dp[ny][nx]){
                pq.push({nd,ny,nx});
                dp[ny][nx] = nd;
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    int idx = 1;
    while(1){
        cin>>N;
        if(!N) break;
        memset(dp, 0x3f, sizeof(dp));
        for(int i=0; i<N; i++) for(int j=0; j<N; j++) cin>>m[i][j];
        dijk();
        cout<<"Problem "<<idx++<<": "<<dp[N-1][N-1]<<'\n';
    }
}