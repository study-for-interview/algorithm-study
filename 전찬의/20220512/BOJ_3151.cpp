#include<bits/stdc++.h>
using namespace std;
using ll = long long;

ll N;
ll m[10001]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    for(ll i=0; i<N; i++){
        cin>>m[i];
    }
    sort(m,m+N);

    ll ans = 0;
    for(ll i=0; i<N; i++){
        if(m[i]>0) break;
        ll st = i+1;
        ll ed = N-1;
        ll num = m[i]*-1;
        while(st<ed){
            if(m[st]+m[ed] > num) {
                ll ted = ed;
                while(m[ted]==m[ed]) ted--;
                ed = ted;
            } else if(m[st]+m[ed] < num) {
                ll tst = st;
                while(m[tst]==m[st]) tst++;
                st = tst;
            } else {
                if(m[st]==m[ed]) {
                    ll n = ed-st+1;
                    ans += (n*(n-1))/2;
                    break;
                }
                ll tst = st, ted = ed;
                while(m[ted]==m[ed]) ted--;
                while(m[tst]==m[st]) tst++;
                ans += (tst-st)*(ed-ted);
                st = tst;
                ed = ted;
            }
        }
    }
    cout<<ans;
}
