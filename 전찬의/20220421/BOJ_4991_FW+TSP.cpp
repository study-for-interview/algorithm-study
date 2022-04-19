// 플로이드 와샬 + TSP 풀이

#include<bits/stdc++.h>
using namespace std;
const int INF = 0x3f3f3f3f;

int N,M;
int m[21][21]{};
int dp[410][401]; // F-W
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};
vector<int> v;
int tsp[11][1<<11]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    while(true){
        cin>>M>>N;
        if(!M && !N) break;
        string str;
        vector<int> tmp;
        v.clear();
        memset(m,0,sizeof(m));
        for(int i=0; i<N; i++){
            cin>>str;
            for(int j=0; j<M; j++){
                if(str[j]=='.') ;
                else if(str[j]=='x') m[i][j] = 1;
                else if(str[j]=='o') v.push_back(i*M + j);
                else tmp.push_back(i*M + j);
            }
        }
        sort(tmp.begin(), tmp.end());
        for(auto pr : tmp) v.push_back(pr);

        memset(dp,0x3f,sizeof(dp));
        for(int i=0; i<N*M; i++) dp[i][i] = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(m[i][j]) continue;
                int cidx = i*M + j;
                for(int k=0; k<4; k++){
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if(ny<0 || nx<0 || ny>=N || nx>=M || m[ny][nx]) continue;
                    int nidx = ny*M + nx;
                    dp[cidx][nidx] = 1;
                }
            }
        }
        
        for(int k=0; k<N*M; k++) {
            for(int i=0; i<N*M; i++){
                for(int j=0; j<N*M; j++){
                    if(dp[i][k] + dp[k][j] < dp[i][j]){
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }
        
        memset(tsp,0x3f,sizeof(tsp));
        tsp[0][1] = 0;
        for(int mask=1; mask<(1<<11); mask++){
            if(!(mask&1)) continue;
            for(int i=1; i<v.size(); i++){
                if(!(mask&(1<<i))) continue; // pre계산위해 i번 비트가 0비트라면 넘김
                int pre = mask^(1<<i);
                for(int j=0; j<v.size(); j++){
                    // pre에서 j번째를 방문한 경우만 dp[j][pre]를 이용하여 계산(pre이고 현재j번 노드에 있을때)
                    if(!(pre&(1<<j))) continue;
                    tsp[i][mask] = min(tsp[i][mask], tsp[j][pre]+dp[v[j]][v[i]]);
                }
            }
        }

        int ans = INF;
        for(int i=1; i<v.size(); i++) ans = min(ans, tsp[i][(1<<(v.size()))-1]);
        if(ans==INF) cout<<-1<<'\n';
        else cout<<ans<<'\n';
    }
}