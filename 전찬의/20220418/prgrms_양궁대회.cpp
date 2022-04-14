#include<bits/stdc++.h>
using namespace std;

int N;
int m[11]{};
vector<int> v;
vector<int> mxRes;
int mxDiff = 0;
int totalSc = 0;

void dfs(int now, int cnt, int lionSc, int peachSc) {
    if(cnt > N) return;
    if(lionSc-peachSc > mxDiff) {
        mxDiff = lionSc-peachSc;
        mxRes.clear();
        mxRes.assign(v.begin(), v.end());
    }
    
    for(int i=now+1; i<=10; i++) {
        v.push_back(i);
        int nlionSc = lionSc+i;
        int npeachSc = peachSc;
        if(m[i]>1) npeachSc -= i;
        dfs(i, cnt+m[i], nlionSc, npeachSc);
        v.pop_back();
    }
}

vector<int> solution(int n, vector<int> info) {
    N = n;
    for(int i=0; i<info.size(); i++){
        if(info[i]) totalSc += 10-i;
        m[10-i] = info[i]+1;
    }
    
    dfs(0,0,0,totalSc);
    
    vector<int> ans;
    if(!mxRes.size()) {
        ans.push_back(-1);
        return ans;
    }
    ans.assign(11,0);
    int cnt = 0;
    for(auto val : mxRes){
        ans[10-val] = m[val];
        cnt += m[val];
    }
    ans[10] += (N-cnt);
    return ans;
}