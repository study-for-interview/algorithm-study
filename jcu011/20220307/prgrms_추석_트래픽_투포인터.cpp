#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

int solution(vector<string> lines) {
    vector<pair<int,int>> times;
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
    sort(times.begin(), times.end());
    
    int ans=0, cnt = 0, ed=0;
    for(int st=0; st<times.size(); st++){
        while(ed<times.size()){
            if(times[ed].xx - times[st].xx < 1000){
                if(times[ed].yy==1) cnt++;
                ed++;
            } else break;
        }
        ans = max(ans, cnt);
        if(times[st].yy==-1) cnt--;
    }
    
    return ans;
}