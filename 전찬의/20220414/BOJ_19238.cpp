// 플로이드 와샬 풀이

#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int, int>;
using tpi = pair<int, pii>;
const int INF = 0x3f3f3f3f;

int N, M, R, taxiPos;
int m[21][21]{};
int dp[401][401]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};
vector<pii> v;
bool vi[401]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M>>R;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin>>m[i][j];
        }
    }

    memset(dp,0x3f,sizeof(dp));
    for(int i=0; i<N*N; i++) dp[i][i] = 0;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(m[i][j]) continue;
            for(int k=0; k<4; k++){
                int ny = i + dy[k];
                int nx = j + dx[k];
                if(ny<0 || nx<0 || ny>=N || nx>=N || m[ny][nx]) continue;
                int now = i*N + j;
                int next = ny*N + nx;
                dp[now][next] = 1;
            }
        }
    }

    for(int k=0; k<N*N; k++){
        for(int i=0; i<N*N; i++){
            for(int j=0; j<N*N; j++){
                if(dp[i][k] + dp[k][j] < dp[i][j])
                    dp[i][j] = dp[i][k] + dp[k][j];
            }
        }
    }

    int a,b,c,d;
    cin>>a>>b;
    a--; b--;
    taxiPos = a*N + b;
    for (int i=0; i<M; i++) {
        cin>>a>>b>>c>>d;
        a--; b--; c--; d--;
        v.push_back({a*N+b, c*N+d});
    }

    while(true) {
        bool chk = false;
        vector<tpi> tmp;
        for(int i=0; i<v.size(); i++) {
            if(vi[i]) continue;
            chk = true;
            int stPos = v[i].xx;
            int edPos = v[i].yy;
            tmp.push_back({dp[taxiPos][stPos], {stPos, i}});
        }
        if(!chk) break;
        sort(tmp.begin(), tmp.end(), [&](tpi a, tpi b) -> bool {
            if(a.xx == b.xx) return a.yy.xx < b.yy.xx;
            return a.xx < b.xx;
        });

        int vIdx = tmp[0].yy.yy;
        vi[vIdx] = true;
        int stPos = v[vIdx].xx;
        int edPos = v[vIdx].yy;
        int need1 = tmp[0].xx;
        int need2 = dp[stPos][edPos];
        if(need1+need2 > R) {R=-1; break;}
        R += (need2-need1);
        taxiPos = edPos;
    }
    cout<<R;
}