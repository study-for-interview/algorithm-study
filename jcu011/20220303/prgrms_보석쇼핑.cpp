#include<bits/stdc++.h>
using namespace std;

map<string, int> kind;
int cnt[100001]{};

vector<int> solution(vector<string> gems) {
    int idx = 1;
    for(int i=0; i<gems.size(); i++) kind[gems[i]] = idx++;
    
    int st=0, ed=0, gNum=1, mLen=gems.size(), mSt=0;
    cnt[kind[gems[ed]]]++;
    while(st<=ed && ed<gems.size()) {
        while(gNum<kind.size() && ed+1<gems.size()) {
            int ni = kind[gems[++ed]];
            if(cnt[ni]==0) gNum++;
            cnt[ni]++;
        }
        
        int cgNum = gNum;
        while(gNum==cgNum && st<=ed){
            int ci = kind[gems[st]];
            if(cnt[ci]==1) {
                if(gNum==kind.size() && ed-st+1 < mLen) {
                    mLen = ed-st+1;
                    mSt = st;
                }
                gNum--;
            }
            cnt[ci]--;
            st++;
        }
    }
    
    vector<int> ans;
    ans.push_back(mSt+1);
    ans.push_back(mLen+mSt);
    return ans;
}