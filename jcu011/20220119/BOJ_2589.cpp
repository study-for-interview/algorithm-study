#include<bits/stdc++.h>
using namespace std;
using tpi = tuple<int, int, int>;

int N,M;
char m[51][51]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};
bool vi[51][51]{};

int bfs(int y, int x){
    int ret = 0;
    memset(vi, 0, sizeof(vi));
    queue<tpi> q;
    q.push({0, y, x});
    vi[y][x] = true;

    while(!q.empty()) {
        int cd, cy, cx;
        tie(cd, cy, cx) = q.front();
        q.pop();

        ret = max(ret, cd);
        for(int i=0; i<4; i++){
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
            if(vi[ny][nx] || m[ny][nx]=='W') continue;
            q.push({cd+1, ny, nx});
            vi[ny][nx] = true;
        }
    }

    return ret;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>N>>M;
    string str;
    for(int i=0; i<N; i++){
        cin>>str;
        for(int j=0; j<M; j++){
            m[i][j] = str[j];
        }
    }
    
    int res = 0;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if(m[i][j]=='W') continue;
            res = max(res, bfs(i, j));
        }
    }
    
    cout<<res;
}