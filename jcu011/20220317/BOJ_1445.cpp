// 20분
#include<bits/stdc++.h>
using namespace std;
using tpi = tuple<int,int,int>;
const int INF = 0x3f3f3f3f;

int N,M;
int sty, stx, edy, edx;
int m[50][50]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};
int dp[50][50]{};

void dijk(){
    memset(dp,0x3f,sizeof(dp));
    priority_queue<tpi, vector<tpi>, greater<tpi>> pq;
    pq.push({0,sty,stx});
    dp[sty][stx] = 0;

    while(!pq.empty()){
        int cd,cy,cx;
        tie(cd,cy,cx) = pq.top();
        pq.pop();

        if(cd > dp[cy][cx]) continue;
        for(int k=0; k<4; k++){
            int ny = cy + dy[k];
            int nx = cx + dx[k];
            if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
            int nd = cd + m[ny][nx];
            if(nd < dp[ny][nx]) {
                pq.push({nd,ny,nx});
                dp[ny][nx] = nd;
            }
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;
    string str;
    for(int i=0; i<N; i++){
        cin>>str;
        for(int j=0; j<M; j++){
            if(str[j]=='S') sty = i, stx = j;
            else if(str[j]=='F') edy = i, edx = j;
            else if(str[j]=='g') {
                m[i][j] = 2500;
                for(int k=0; k<4; k++){
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if(ny<0 || nx<0 || ny>=N || nx>=M || m[ny][nx]>1) continue;
                    m[ny][nx] = 1;
                }
            }
        }
    }
    m[sty][stx] = 0;
    m[edy][edx] = 0;

    dijk();
    cout<<dp[edy][edx]/2500<<' '<<dp[edy][edx]%2500;
}