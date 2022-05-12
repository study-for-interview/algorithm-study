#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using tpi = tuple<int,int,int>;

int N, M;
int par[1001]{};
vector<tpi> edges;

int find(int k) {
    if (k == par[k]) return k;
    else return par[k] = find(par[k]);
}

void merge(int a, int b) {
    a = find(a);
    b = find(b);
    par[a] = b;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>N;
    cin>>M;
    int a,b,c;
    for(int i=0; i<=N; i++) par[i] = i;
    for(int i=0; i<M; i++){
        cin>>a>>b>>c;
        edges.push_back({c,a,b});
    }
    sort(edges.begin(), edges.end());

    int sum = 0;
    for(int i=0; i<M; i++) {
        int c,a,b;
        tie(c,a,b) = edges[i];
        if(find(a)==find(b)) continue;
        merge(a,b);
        sum += c;
    }
    cout<<sum;
}