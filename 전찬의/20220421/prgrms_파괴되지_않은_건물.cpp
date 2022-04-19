// https://driip.me/65d9b58c-bf02-44bf-8fba-54d394ed21e0

#include<bits/stdc++.h>
using namespace std;

int m[1002][1002]{};

void imos(int a, int b, int c, int d, int val){
    int x1 = a;
    int x2 = c+1;
    int y1 = b;
    int y2 = d+1;
    m[x1][y1] += val;
    m[x2][y2] += val;
    m[x1][y2] -= val;
    m[x2][y1] -= val;
}

int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int N = board.size();
    int M = board[0].size();
    for(int i=0; i<skill.size(); i++){
        int type = skill[i][0];
        int r1 = skill[i][1];
        int c1 = skill[i][2];
        int r2 = skill[i][3];
        int c2 = skill[i][4];
        int degree = skill[i][5];
        if(type==1) degree *= -1;
        imos(r1,c1,r2,c2,degree);
    }
    
    for(int i=0; i<N; i++){
        for(int j=1; j<M; j++){
            m[i][j] += m[i][j-1];
        }
    }
    
    for(int j=0; j<M; j++){
        for(int i=1; i<N; i++){
            m[i][j] += m[i-1][j];
        }
    }
    
    int ans = 0;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            m[i][j] += board[i][j];
            if(m[i][j]>0) ans++;
        }
    }
    return ans;
}