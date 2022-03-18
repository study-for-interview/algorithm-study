#include<bits/stdc++.h>
using namespace std;

int N;
int mp[4]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};
bool vi[100][100]{};
double nonSimple = 0;

void dfs(int dpt, int cy, int cx, double cp){
    if(dpt==N) return;

    for(int i=0; i<4; i++){
        int ny = cy + dy[i];
        int nx = cx + dx[i];
        double np = cp/100*mp[i];
        
        if(vi[ny][nx]) {
            nonSimple += np;
            continue;
        }

        vi[ny][nx] = true;
        dfs(dpt+1, ny, nx, np);
        vi[ny][nx] = false;
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    for(int i=0; i<4; i++) cin>>mp[i];
    
    vi[50][50] = true;
    dfs(0,50,50,1);

    cout.precision(15);
    cout<<fixed;
    cout<<1-nonSimple;
}