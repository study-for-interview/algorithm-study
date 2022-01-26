#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

int N,I,M;
vector<pii> m;

int chkFish(int a, int b, int c, int d){
    int cnt = 0;
    for(int i=0; i<m.size(); i++){
        int y = m[i].xx;
        int x = m[i].yy;
        if(y>=a && y<=c && x>=b && x<=d) cnt++;
    }
    return cnt;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>I>>M;
    int a,b;
    for(int i=0; i<M; i++){
        cin>>a>>b;
        m.push_back({a,b});
    }

    int ans = 1;
    for(int i=0; i<m.size(); i++){
        int cy = m[i].xx;
        int cx = m[i].yy;
        for(int j=1; j<I/2; j++){
            int dy = j;
            int dx = I/2 - j;
            for(int y=cy-dy; y<=cy; y++){
                for(int x=cx-dx; x<=cx; x++){
                    if(y<1 || x<1 || y+dy>N || x+dx>N) continue;
                    int now = chkFish(y,x, y+dy, x+dx);
                    ans = max(ans, now);
                }
            }
        }
    }

    cout<<ans;
}