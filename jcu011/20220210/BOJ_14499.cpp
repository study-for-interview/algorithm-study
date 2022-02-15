#include<bits/stdc++.h>
using namespace std;

int N,M,y,x,K;
int m[21][21]{};
int dice[7]{};
int dy[5] = {0,0,0,-1,1};
int dx[5] = {0,1,-1,0,0};

void move(int dir){
    int tmp;
    if(dir==1){
        tmp = dice[1];
        dice[1] = dice[4];
        dice[4] = dice[6];
        dice[6] = dice[3];
        dice[3] = tmp;
    }
    else if(dir==2){
        tmp = dice[1];
        dice[1] = dice[3];
        dice[3] = dice[6];
        dice[6] = dice[4];
        dice[4] = tmp;
    }
    else if(dir==3){
        tmp = dice[1];
        dice[1] = dice[5];
        dice[5] = dice[6];
        dice[6] = dice[2];
        dice[2] = tmp;
    }
    else if(dir==4){
        tmp = dice[1];
        dice[1] = dice[2];
        dice[2] = dice[6];
        dice[6] = dice[5];
        dice[5] = tmp;
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M>>y>>x>>K;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin>>m[i][j];
        }
    }

    int k;
    for(int i=0; i<K; i++){
        cin>>k;
        int ny = y + dy[k];
        int nx = x + dx[k];
        if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
        y = ny;
        x = nx;
        move(k);
        if(m[y][x]==0) m[y][x] = dice[6];
        else {
            dice[6] = m[y][x]; 
            m[y][x] = 0;
        }
        cout<<dice[1]<<'\n';
    }


}