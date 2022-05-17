#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;
using ll = long long;

const int MAX = 9999;
int sieve[MAX+1];
void find_prime(){
    memset(sieve, -1, sizeof(sieve));
    for(ll i=2; i*i<=MAX; ++i)
        if(sieve[i] == -1)
            for(ll j=i*i; j<=MAX; j+=i)
                if(sieve[j] == -1)
                    sieve[j] = i;
}

int N;
int vi[10000]{};

int bfs(int a, int b){
    memset(vi,0,sizeof(vi));
    queue<pii> q;
    q.push({a,0});
    vi[a] = true;

    while(!q.empty()){
        int now = q.front().xx;
        int dist = q.front().yy;
        q.pop();

        if(now==b) return dist;

        for(int i=1; i<=1000; i*=10){
            for(int k=0; k<10; k++){
                if(i==1000 && k==0) continue;
                int tmp = now/(i*10);
                int next = k*i + tmp*(i*10) + now%i;
                if(sieve[next]<0 && !vi[next]) {
                    q.push({next,dist+1});
                    vi[next] = true;
                }
            }
        }
    }

    return -1;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    find_prime();
    cin>>N;
    int a,b;

    while(N--){
        cin>>a>>b;
        int ret = bfs(a,b);
        if(ret<0) cout<<"Impossible"<<'\n';
        else cout<<ret<<'\n';
    }

}