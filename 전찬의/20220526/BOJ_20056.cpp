#include<bits/stdc++.h>
using namespace std;

struct FB{
    int R,C,M,S,D,SZ;
    FB(int R_=0, int C_=0, int M_=0, int S_=0, int D_=-1, int SZ_=0) 
    : R(R_), C(C_), M(M_), S(S_), D(D_), SZ(SZ_){}
};

int N,M,K;
FB m[50][50]{};
vector<FB> fb;
int dy[8] = {-1,-1,0,1,1,1,0,-1};
int dx[8] = {0,1,1,1,0,-1,-1,-1};
int dd[2][4] = {{0, 2, 4, 6}, {1, 3, 5, 7}};

void divideFb(){
    fb.clear();
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(m[i][j].D==-1) continue;
            if(m[i][j].SZ==1) { fb.push_back(m[i][j]); continue;}
            int nm = m[i][j].M / 5;
            int ns = m[i][j].S / m[i][j].SZ;
            if(!nm) continue;
            int ndd = m[i][j].D == 8 ? 1 : 0;
            for(int k=0; k<4; k++){
                fb.push_back(FB(i,j,nm,ns,dd[ndd][k],1));
            }
        }
    }
}

void fbMove(){
    for(int i=0; i<N; i++) for(int j=0; j<N; j++) m[i][j] = FB(i,j,0,0,-1,0);
    for(int i=0; i<fb.size(); i++){
        int ny = (fb[i].R + dy[fb[i].D]*fb[i].S + 1000*N) % N;
        int nx = (fb[i].C + dx[fb[i].D]*fb[i].S + 1000*N) % N;
        fb[i].R = ny;
        fb[i].C = nx;
        if(m[ny][nx].D==-1) m[ny][nx] = fb[i];
        else {
            m[ny][nx].M += fb[i].M;
            m[ny][nx].S += fb[i].S;
            m[ny][nx].SZ++;
            if(m[ny][nx].D==8) continue;
            if(m[ny][nx].D%2 != fb[i].D%2) m[ny][nx].D = 8;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M>>K;
    int a,b,c,d,e;
    for(int i=0; i<M; i++){
        cin>>a>>b>>c>>d>>e;
        fb.push_back(FB(a-1, b-1, c, d, e, 1));
    }
    
    while(K--){
        fbMove();
        divideFb();
    }

    int ans = 0;
    for(auto val : fb){
        ans += val.M;
    }
    cout<<ans;
}