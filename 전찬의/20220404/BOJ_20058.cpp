#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

int N,Q;
int m[65][65]{};
int tM[65][65]{};
bool vi[65][65]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};

int bfs(int y, int x) {
    int ret = 1;
    queue<pii> q;
    q.push({y,x});
    vi[y][x] = true;

    while (!q.empty()){
        int cy = q.front().xx;
        int cx = q.front().yy;
        q.pop();

        for(int i=0; i<4; i++){
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
            if(vi[ny][nx] || !m[ny][nx]) continue;
            vi[ny][nx] = true;
            q.push({ny,nx});
            ret++;
        }
    }
    return ret;
}

void melt(){
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(m[i][j]==0) {
                tM[i][j] = m[i][j];
                continue;
            }
            int cnt = 0;
            for(int k=0; k<4; k++){
                int ny = i + dy[k];
                int nx = j + dx[k];
                if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
                if(m[ny][nx]>0) cnt++;
            }
            if(cnt<3) tM[i][j] = m[i][j]-1;
            else tM[i][j] = m[i][j];
        }
    }
    memcpy(m, tM, sizeof(m));
}

void rotateM(int a, int b, int sz){
    for(int i=0, x=b; i<sz; i++, x++){
        for(int j=0, y=a+sz-1; j<sz; j++, y--){
            tM[i][j] = m[y][x];
        }
    }
    for(int i=0, y=a; i<sz; i++, y++){
        for(int j=0, x=b; j<sz; j++, x++){
            m[y][x] = tM[i][j];
        }
    }
}

void solve(int n){
    if(n==1) {
        melt();
        return;
    }
    for(int i=0; i<N; i+=n){
        for(int j=0; j<N; j+=n){
            rotateM(i,j,n);
        }
    }
    melt();
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>N>>Q;
    N = (1<<N);
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin>>m[i][j];
        }
    }

    int t;
    for(int i=0; i<Q; i++) {
        cin>>t;
        solve((1<<t));
    }

    int ans1=0, ans2=0;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            ans1 += m[i][j];
            if(m[i][j]==0) vi[i][j]=true;
            else if(!vi[i][j]) ans2 = max(ans2, bfs(i,j));
        }
    }
    cout<<ans1<<'\n'<<ans2;
}

/*

1 2 3
4 5 6
7 8 9

*/