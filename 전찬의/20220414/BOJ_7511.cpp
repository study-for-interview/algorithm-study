#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
typedef pair<int,int> pii;
const int INF = 0x3f3f3f3f;

int T,N,K,M;
int parent[1000010]{};

int find(int k) {
    if (k == parent[k]) return k;
    else return parent[k] = find(parent[k]);
}

void merge(int a, int b) {
    a = find(a);
    b = find(b);
    parent[a] = b;
}

int main() {
	ios_base::sync_with_stdio(false); 
    cin.tie(0);
    cin>>T;
    for(int j=1; j<=T; j++){
        cout<<"Scenario "<<j<<":"<<'\n';
        cin>>N>>K;
        for(int i=0; i<N; i++) parent[i] = i;
        int a,b;
        for(int i=0; i<K; i++){
            cin>>a>>b;
            merge(a,b);
        }

        cin>>M;
        for(int i=0; i<M; i++){
            cin>>a>>b;
            if(find(a)==find(b)) cout<<1<<'\n';
            else cout<<0<<'\n';
        }
        cout<<'\n';
    }
    
}