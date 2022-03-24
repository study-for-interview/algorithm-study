#include<bits/stdc++.h>
using namespace std;

int X,N;
vector<int> m;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    while(cin>>X){
        X *= 10000000;
        m.clear();
        cin>>N;
        int tmp;
        for(int i=0; i<N; i++) {
            cin>>tmp;
            m.push_back(tmp);
        }
        sort(m.begin(), m.end());

        bool chk = false;
        for(int i=N-1; i>0; i--){
            if(m[i] >= X) continue;
            if(m[i] < X/2) break;
            int now = X - m[i];
            int idx = lower_bound(m.begin(), m.begin()+i-1, now) - m.begin();
            if(m[idx]==now){
                chk = true;
                cout<<"yes "<<now<<" "<<m[i]<<'\n';
                break;
            }
        }
        if(!chk) cout<<"danger"<<'\n';
    }

}

