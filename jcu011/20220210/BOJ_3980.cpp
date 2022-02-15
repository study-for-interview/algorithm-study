// 6:07
#include<bits/stdc++.h>
using namespace std;

int T, ans;
int m[11][11]{};
int pos[11]{};

void dfs(int dpt){
    if(dpt == 11) {
        int sum = 0;
        for(int i=0; i<11; i++) sum += pos[i];
        ans = max(ans, sum);
        return;
    }

    for(int i=0; i<11; i++){
        if(m[dpt][i]==0 || pos[i]) continue;
        pos[i] = m[dpt][i];
        dfs(dpt+1);
        pos[i] = 0;
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>T;
    while(T--){
        for(int i=0; i<11; i++) for(int j=0; j<11; j++) cin>>m[i][j];
        ans = 0;
        dfs(0);
        cout<<ans<<'\n';
    }

}