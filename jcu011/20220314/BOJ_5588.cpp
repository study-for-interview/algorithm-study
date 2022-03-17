// 15m
#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

int M,N;
vector<pii> m;
vector<pii> n;

bool comp(pii a, pii b){
    if(a.xx==b.xx) return a.yy < b.yy;
    return a.xx < b.xx;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    int x,y;
    cin>>M;
    for(int i=0; i<M; i++){
        cin>>x>>y;
        m.push_back({x,y});
    }
    cin>>N;
    for(int i=0; i<N; i++){
        cin>>x>>y;
        n.push_back({x,y});
    }
    sort(m.begin(), m.end(), comp);
    sort(n.begin(), n.end(), comp);

    for(int i=0; i<n.size(); i++){
        int dx = n[i].xx - m[0].xx;
        int dy = n[i].yy - m[0].yy;
        bool chk = true;
        for(int j=1; j<m.size(); j++){
            int nx = m[j].xx + dx;
            int ny = m[j].yy + dy;
            if(nx<0 || ny<0 || nx>1000000 || nx>1000000) {chk=false; break;}
            int lbIdx = lower_bound(n.begin(), n.end(), pii(nx,ny), comp) - n.begin();
            int ubIdx = upper_bound(n.begin(), n.end(), pii(nx,ny), comp) - n.begin();
            if(lbIdx == ubIdx) {
                chk=false;
                break;
            }
        }
        if(chk) {
            cout<<dx<<' '<<dy;
            break;
        }
    }


}