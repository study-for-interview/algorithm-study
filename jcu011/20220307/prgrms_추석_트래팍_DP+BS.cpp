#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

vector<int> sdp;
vector<int> edp;
vector<int> tt;

int solution(vector<string> lines) {
    vector<pii> times;
    for(auto &line : lines){
        line.pop_back();
        char buf[25];
        int h,m,s,d;
        double _s,_d;
        sscanf(line.c_str(), "%s %d:%d:%lf %lf", buf,&h,&m,&_s,&_d);
        h *= 3600000;
        m *= 60000;
        s = (int)(_s*1000.0);
        d = (int)(_d*1000.0);
        int ed = h + m + s;
        int st = ed - d + 1;
        times.push_back({st,1});
        times.push_back({ed,-1});
    }
    sort(times.begin(), times.end(), [&](pii a, pii b) -> bool {
        if(a.xx == b.xx) return a.yy > b.yy;
        return a.xx < b.xx;
    });
    
    sdp.push_back(1);
    edp.push_back(0);
    tt.push_back(times[0].xx);
    for(int i=1; i<times.size(); i++){
        int chk = times[i].yy;
        int ns = sdp[i-1] + (chk>0 ? 1 : 0);
        int ne = edp[i-1] + (chk<0 ? 1 : 0);
        sdp.push_back(ns);
        edp.push_back(ne);
        tt.push_back(times[i].xx);
    }
        
    int ans = 0;
    for(int i=0; i<tt.size(); i++){
        int idx = lower_bound(tt.begin(), tt.end(), tt[i]+1000) - tt.begin() - 1;
        int amt = sdp[idx] - edp[i] + (times[i].yy<0 ? 1 : 0);
        ans = max(amt, ans);
    }
    
    return ans;
}