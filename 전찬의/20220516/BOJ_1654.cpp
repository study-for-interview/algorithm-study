#include<bits/stdc++.h>
using namespace std;
using ll = long long;

ll K,N;
ll m[10001]{};

bool isP(ll mid) {
    ll cnt = 0;
    for(ll i=0; i<K; i++){
        cnt += (m[i]/mid);
    }
    if(cnt>=N) return true;
    else return false;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>K>>N;
    for(ll i=0; i<K; i++){
        cin>>m[i];
    }

    ll st = 1, ed = 2147483647;
    while(st<=ed) {
        ll mid = (st+ed)>>1;
        if(isP(mid)) st = mid + 1;
        else ed = mid - 1;
    }
    cout<<ed;
}