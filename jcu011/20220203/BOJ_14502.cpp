#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

int N,M, safe=0, ans=0;
int m[10][10]{};
bool vi[10][10]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};

int virus(int sty, int stx){
    queue<pii> q;
    q.push({sty,stx});
    vi[sty][stx] = true;
    int res = 1;

    while(!q.empty()){
        int cy = q.front().xx;
        int cx = q.front().yy;
        q.pop();

        for(int i=0; i<4; i++){
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
            if(vi[ny][nx] || m[ny][nx]==1) continue;
            q.push({ny,nx});
            vi[ny][nx] = true;
            res++;
        }
    }
    return res;
}

int chkSafe(){
    memset(vi, 0, sizeof(vi));
    int res = safe;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            if(m[i][j]==2 && vi[i][j]==false) {
                res -= virus(i,j);
            }
        }
    }
    return res;
}

void dfs(int dpt, int cpos) {
    if(dpt==3) {
        ans = max(ans, chkSafe());
        return;
    }

    for(int k=cpos+1; k<N*M; k++){
        int ny = k/M;
        int nx = k%M;
        if(m[ny][nx]!=0) continue;
        m[ny][nx] = 1;
        dfs(dpt+1, k);
        m[ny][nx] = 0;
    }

}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;

    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin>>m[i][j];
            if(m[i][j]!=1) safe++;
        }
    }

    dfs(0, -1);
    cout<<ans-3;
    
}