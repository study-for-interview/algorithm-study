#include<bits/stdc++.h>
using namespace std;

int N,K, res=0;
int m[10]{};
bool vi[10]{};

void dfs(int dpt, int sum) {
    if(dpt==N) {
        res++;
        return;
    }

    for(int i=0; i<N; i++){
        if(vi[i]) continue;
        int nsum = sum + m[i] - K;
        if(nsum<0) continue;
        vi[i] = true;
        dfs(dpt+1, nsum);
        vi[i] = false;
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>K;
    for(int i=0; i<N; i++){
        cin>>m[i];
    }
    
    dfs(0, 0);
    
    cout<<res;
}