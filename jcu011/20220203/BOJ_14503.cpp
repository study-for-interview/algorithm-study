#include<bits/stdc++.h>
using namespace std;

int N,M, ans=0;
int cy, cx, cd;
int m[51][51]{};
bool vi[51][51]{};
int dy[4] = {-1,0,1,0};
int dx[4] = {0,1,0,-1};

bool func2(){
    int i;
    for(i=1; i<=4; i++){
        int nd = (cd+4-i)%4;
        int ny = cy + dy[nd];
        int nx = cx + dx[nd];
        if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
        if(m[ny][nx]==1 || vi[ny][nx]) continue;
        cd = nd;
        cy = ny;
        cx = nx;
        break;
    }

    if(i!=5) return true;
    
    int nd = (cd+2)%4;
    int ny = cy + dy[nd];
    int nx = cx + dx[nd];
    if(ny<0 || nx<0 || ny>=N || nx>=M || m[ny][nx]==1) return false;
    cy = ny;
    cx = nx;
    return func2();
}

bool func1(){
    vi[cy][cx] = true;
    ans++;
    return func2();
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;
    cin>>cy>>cx>>cd;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin>>m[i][j];
        }
    }

    while(func1()){}
    cout<<ans;
}