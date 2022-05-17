#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

int N, st, ed;
string m[1001]{};
int dp[1001]{};

void dijk() {
    memset(dp,0x3f,sizeof(dp));
    priority_queue<pii> pq;
    pq.push({0,st});
    dp[st] = 0;

    while(!pq.empty()){
        int cd = pq.top().xx;
        int cp = pq.top().yy;
        string now = m[cp];
        pq.pop();

        if(cd > dp[cp]) continue;

        for(int i=1; i<=N; i++){
            if(cp==i) continue;
            string next = m[i];
            int sum = 0;
            for(int k=0; k<next.size(); k++){
                int a = now[k]-'0';
                int b = next[k]-'0';
                sum += ((a-b)*(a-b));
            }
            int nd = sum + cd;
            if(nd < dp[i]){
                pq.push({nd, i});
                dp[i] = nd;
            }
        }
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    string str;
    for(int i=1; i<=N; i++){
        cin>>m[i];
    }
    cin>>st>>ed;
    dijk();
    cout<<dp[ed];
}