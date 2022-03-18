// 23:51

#include<bits/stdc++.h>
using namespace std;

int N,M,R;
int m[101][101]{};
int t[101][101]{};

void printM(){
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cout<<m[i][j]<<' ';
        }
        cout<<'\n';
    }
}

void setM(){
    for(int i=0; i<N; i++)
        for(int j=0; j<M; j++)
            m[i][j] = t[i][j];
}

void func1(){
    for(int i=0, a=N-1; i<N; i++, a--){
        for(int j=0, b=0; j<M; j++, b++){
            t[i][j] = m[a][b];
        }
    }
    setM();
}

void func2(){
    for(int i=0, a=0; i<N; i++, a++){
        for(int j=0, b=M-1; j<M; j++, b--){
            t[i][j] = m[a][b];
        }
    }
    setM();
}

void func3(){
    for(int i=0, b=0; i<M; i++, b++){
        for(int j=0, a=N-1; j<N; j++, a--){
            t[i][j] = m[a][b];
        }
    }
    swap(N,M);
    setM();
}

void func4(){
    for(int i=0, b=M-1; i<M; i++, b--){
        for(int j=0, a=0; j<N; j++, a++){
            t[i][j] = m[a][b];
        }
    }
    swap(N,M);
    setM();
}

void func5(){
    int dy[4] = {0,0,1,1};
    int dx[4] = {0,1,1,0};
    for(int k=0; k<4; k++){
        for(int i=0; i<N/2; i++){
            for(int j=0; j<M/2; j++){
                int cy = i+dy[k]*N/2;
                int cx = j+dx[k]*M/2;
                int py = i+dy[(k+3)%4]*N/2;
                int px = j+dx[(k+3)%4]*M/2;
                t[cy][cx] = m[py][px];
            }
        }
    }
    setM();
}

void func6(){
    int dy[4] = {0,0,1,1};
    int dx[4] = {0,1,1,0};
    for(int k=0; k<4; k++){
        for(int i=0; i<N/2; i++){
            for(int j=0; j<M/2; j++){
                int cy = i+dy[k]*N/2;
                int cx = j+dx[k]*M/2;
                int ny = i+dy[(k+1)%4]*N/2;
                int nx = j+dx[(k+1)%4]*M/2;
                t[cy][cx] = m[ny][nx];
            }
        }
    }
    setM();
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M>>R;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin>>m[i][j];
        }
    }

    int r;
    while(R--){
        cin>>r;
        if(r==1) func1();
        else if(r==2) func2();
        else if(r==3) func3();
        else if(r==4) func4();
        else if(r==5) func5();
        else if(r==6) func6();
    }

    printM();
}