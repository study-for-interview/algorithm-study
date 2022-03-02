#include<bits/stdc++.h>
using namespace std;
using tpi = tuple<int, int, int>;

int N,M,K, ans=1e9;
int m[51][51]{};
vector<tpi> v;

void func(int r, int c, int s) {
    for(int k=1; k<=s; k++) {
        int cy = r-k;
        int cx = c-k;
        int tmp = m[cy][cx];

        for(int i=0; i<k*2; i++) {m[cy][cx] = m[cy+1][cx]; cy++;}
        for(int j=0; j<k*2; j++) {m[cy][cx] = m[cy][cx+1]; cx++;}
        for(int i=k*2; i>0; i--) {m[cy][cx] = m[cy-1][cx]; cy--;}
        for(int j=k*2; j>0; j--) {m[cy][cx] = m[cy][cx-1]; cx--;}
        m[cy][cx+1] = tmp;
    }
}

int calMin() {
    int ret = 1e9;
    for(int i=0; i<N; i++){
        int sum = 0;
        for(int j=0; j<M; j++) sum += m[i][j];
        ret = min(ret, sum);
    }
    return ret;
}

bool vi[6]{};
void dfs(int dpt) {
    if(dpt == K) {
        ans = min(ans, calMin());
        return;
    }

    int tm[51][51]{};
    for(int i=0; i<v.size(); i++){
        if(vi[i]) continue;
        memcpy(tm, m, sizeof(tm));
        int a,b,c;
        tie(a,b,c) = v[i];
        func(a,b,c);
        vi[i] = true;
        dfs(dpt+1);
        vi[i] = false;
        memcpy(m, tm, sizeof(m));
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M>>K;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin>>m[i][j];
        }
    }
    
    for(int i=0; i<K; i++){
        int a,b,c;
        cin>>a>>b>>c;
        v.push_back({a-1, b-1, c});
    }

    dfs(0);
    cout<<ans;
}