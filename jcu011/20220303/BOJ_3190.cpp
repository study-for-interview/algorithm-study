#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pic = pair<int,char>;
using pii = pair<int,int>;

int N,K,L; 
int m[101][101]{};
int dy[4] = {0,1,0,-1};
int dx[4] = {1,0,-1,0};
vector<pic> qr;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    cin>>K;
    int a,b;
    for(int i=0; i<K; i++) {
        cin>>a>>b;
        m[a][b] = 1;
    }

    cin>>L;
    int aa;
    char bb;
    for(int i=0; i<L; i++) {
        cin>>aa;
        cin>>bb;
        qr.push_back(pic(aa,bb));
    }

    int ct=0, cdir=0, cy=1, cx=1;
    bool endGame = false;
    queue<pii> q;
    q.push({1,1});
    m[1][1] = 2;
    for(int i=0; i<=L; i++) {
        int nt, ndir;
        if(i==L) {nt = 1e9; ndir = cdir;}
        else {
            nt = qr[i].xx;
            ndir = qr[i].yy=='L' ? (cdir+3)%4 : (cdir+1)%4;
        }

        for(; ct<nt; ct++){
            int ny = cy + dy[cdir];
            int nx = cx + dx[cdir];
            if(ny<1 || nx<1 || ny>N || nx>N || m[ny][nx]==2) {endGame = true; break;}
            if(m[ny][nx]!=1) {
                m[q.front().xx][q.front().yy] = 0;
                q.pop();
            }
            cy = ny;
            cx = nx;
            q.push({ny,nx});
            m[ny][nx] = 2;
        }
        cdir = ndir;

        if(endGame) break;
    }

    cout<<ct+1;
}