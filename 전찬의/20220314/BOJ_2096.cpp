// 15분
#include<bits/stdc++.h>
using namespace std;

int N;
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    int a,b,c;
    int mxx=0,mxy=0,mxz=0, mnx=0,mny=0,mnz=0;
    int tx=0,ty=0,tz=0;
    for(int i=0; i<N; i++) {
        cin>>a>>b>>c;
        tx = mxx, ty = mxy, tz = mxz;
        mxx = max(tx, ty) + a;
        mxy = max({tx, ty, tz}) + b;
        mxz = max(ty, tz) + c;

        tx = mnx, ty = mny, tz = mnz;
        mnx = min(tx, ty) + a;
        mny = min({tx, ty, tz}) + b;
        mnz = min(ty, tz) + c;
    }

    cout<<max({mxx,mxy,mxz})<<' '<<min({mnx,mny,mnz});
}