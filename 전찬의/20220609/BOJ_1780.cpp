// 12:34

#include<bits/stdc++.h>
using namespace std;

int N;
int m[2200][2200]{};
int ans_[3]{}, *ans = ans_ + 1;

void func(int y, int x, int k){
    int chk = m[y][x];
    bool flag = false;
    for(int i=y; i<y+k; i++){
        for(int j=x; j<x+k; j++){
            if(chk != m[i][j]){
                flag = true;
                break;
            }
        }
        if(flag) break;
    }
    if(!flag) {
        ans[chk]++;
        return;
    }
    for(int i=y; i<y+k; i+=(k/3)){
        for(int j=x; j<x+k; j+=(k/3)){
            func(i,j,k/3);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin>>m[i][j];
        }
    }

    func(0,0,N);
    for(int i=0; i<3; i++) cout<<ans_[i]<<'\n';
}