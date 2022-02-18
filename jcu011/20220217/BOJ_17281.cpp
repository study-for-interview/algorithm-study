#include<bits/stdc++.h>
using namespace std;

int N, ans=0;
int m[50][9]{};
bool vi[9]{};
int order[9];

int solve() {
    int sc = 0;
    int now = 0;
    for(int i=0; i<N; i++){
        int lu[3]{};
        int out = 0;
        while(out<3){
            int np = order[now];
            int next = m[i][np];
            now = (now+1)%9;
            if(next==0) out++;
            else {
                for(int k=2; k>=0; k--){
                    if(lu[k]) {
                        lu[k] = 0;
                        int nk = k+next;
                        if(nk>=3) sc++;
                        else lu[nk] = 1;
                    }
                }
                if(next==4) sc++;
                else lu[next-1] = 1;
            }
        }
    }
    return sc;
}

void dfs(int dpt) {
    if(dpt==3) dpt++;
    if(dpt==9){
        ans = max(ans, solve());
        return;
    }

    for(int i=1; i<9; i++){
        if(vi[i]) continue;
        vi[i] = true;
        order[dpt] = i;
        dfs(dpt+1);
        vi[i] = false;
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    for(int i=0; i<N; i++){
        for(int j=0; j<9; j++){
            cin>>m[i][j];
        }
    }

    dfs(0);
    cout<<ans;
}