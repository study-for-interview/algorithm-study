#include<bits/stdc++.h>
using namespace std;

int N,M;
vector<int> m1, m2;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;
    int t;
    for(int i=0; i<N; i++) {
        cin>>t;
        if(t<0) m1.push_back(t);
        else m2.push_back(t);
    }
    sort(m1.begin(), m1.end());
    sort(m2.begin(), m2.end(), greater<int>());
    
    int ans = 0;
    int mxv = -100000;
    for(int i=0; i<m1.size(); i+=M){
        int now = abs(m1[i]);
        ans += now;
        mxv = max(mxv, now);
    }
    for(int i=0; i<m2.size(); i+=M){
        int now = abs(m2[i]);
        ans += now;
        mxv = max(mxv, now);
    }
    cout<<ans*2-mxv;
}