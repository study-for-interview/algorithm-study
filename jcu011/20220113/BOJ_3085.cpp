#include<bits/stdc++.h>
using namespace std;

int N, res=1;
char m[51][51]{};
int dy[4] = {1,0,-1,0};
int dx[4] = {0,1,0,-1};

void chkHoriz(int y) {
    int cl = 1;
    for(int j=1; j<N; j++){
        if(m[y][j]==m[y][j-1]) {
            cl++;
            res = max(res, cl);
        } else cl = 1;
    }
}

void chkVerti(int x) {
    int cl = 1;
    for(int i=1; i<N; i++){
        if(m[i][x]==m[i-1][x]) {
            cl++;
            res = max(res, cl);
        } else cl = 1;
    }
}

void change(int cy, int cx) {
    for(int i=0; i<2; i++){
        int ny = cy + dy[i];
        int nx = cx + dx[i];
        if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
        if(m[cy][cx]==m[ny][nx]) continue;
        swap(m[cy][cx], m[ny][nx]);
        chkHoriz(cy); chkVerti(cx);
        chkHoriz(ny); chkVerti(nx);
        swap(m[cy][cx], m[ny][nx]);
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    string str;
    for(int i=0; i<N; i++){
        cin>>str;
        for(int j=0; j<N; j++){
            m[i][j] = str[j];
        }
    }

    for(int i=0; i<N; i++) chkHoriz(i);
    for(int j=0; j<N; j++) chkVerti(j);

    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            change(i,j);
        }
    }

    cout<<res;
}