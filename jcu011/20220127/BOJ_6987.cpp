#include<bits/stdc++.h>
using namespace std;

int m[6][3]{};
int vi[6][3]{};
void func1(int dpt1);
void func2(int dpt1, int dpt2);
bool chk = false;

void func2(int dpt1, int dpt2){
    if(chk) return;
    if(dpt2==6) {
        func1(dpt1+1);
        return;
    }

    for(int k=0; k<3; k++){
        if(vi[dpt1][k] >= m[dpt1][k] || vi[dpt2][2-k] >= m[dpt2][2-k]) continue;
        vi[dpt1][k]++;
        vi[dpt2][2-k]++;
        func2(dpt1, dpt2+1);
        vi[dpt2][2-k]--;
        vi[dpt1][k]--;
    }
}

void func1(int dpt1){
    if(chk) return;
    if(dpt1==6) {
        chk = true;
        return;
    }
    func2(dpt1, dpt1+1);
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    for(int k=0; k<4; k++){
        chk = false;
        bool inChk = true;
        memset(vi, 0, sizeof(vi));
        for(int i=0; i<6; i++){
            int cnt=0;
            for(int j=0; j<3; j++){
                cin>>m[i][j];
                cnt += m[i][j];
            }
            if(cnt!=5) inChk=false;
        }
        if(!inChk) {
            cout<<0<<' ';
            continue;
        }
        func1(0);
        cout<<chk<<' ';
    }
    
}