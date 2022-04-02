#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;
const int INF = 0x3f3f3f3f;

int N, M, st, ed;
int m[1001][1001]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;
    int a,b,c;
    memset(m,0x3f,sizeof(m));
    for(int i=0; i<N-1; i++){
        cin>>a>>b>>c;
        m[a][b] = c;
        m[b][a] = c;
    }

    for(int i=1; i<=N; i++) m[i][i] = 0;
    for(int k=1; k<=N; k++){
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(m[i][k] + m[k][j] < m[i][j])
                    m[i][j] = m[i][k] + m[k][j];
            }
        }
    }

    while (M--) {
        cin>>st>>ed;
        cout<<m[st][ed]<<'\n';
    }
}