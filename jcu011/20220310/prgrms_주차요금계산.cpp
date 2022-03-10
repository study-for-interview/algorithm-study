#include<bits/stdc++.h>
using namespace std;

map<int,int> s;
int car[10000]{};

vector<int> solution(vector<int> fees, vector<string> records) {
    vector<int> tans;
    for(int i=0; i<records.size(); i++){
        int h,m,n;
        char buf[50];
        sscanf(records[i].c_str(), "%d:%d %d %s", &h,&m,&n,buf);
        int time = h*60 + m + 1;
        string str = buf;
        if(str=="IN") s[n] = time;
        else {
            int dt = time - s[n];
            car[n] += dt;
            s[n] = 0;
        }
    }
    for(auto val : s) {
        if(val.second)
            car[val.first] += (1440 - val.second);
    }
    for(int i=0; i<10000; i++){
        if(car[i]) 
            tans.push_back(car[i]);
    }
    
    vector<int> ans;
    for(auto val : tans){
        if(val<=fees[0]) ans.push_back(fees[1]);
        else {
            val -= fees[0];
            int tmp = val/fees[2];
            tmp += (val%fees[2]>0 ? 1 : 0);
            int res = fees[1] + (tmp * fees[3]);
            ans.push_back(res);
        }
    }
    
    return ans;
}