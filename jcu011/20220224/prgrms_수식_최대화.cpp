#include<bits/stdc++.h>
using namespace std;
using ll = long long;

ll M=0, ans = 0;
int opCnt[3]{};
vector<ll> nums;
vector<int> ops;
vector<int> ord;

int getOperNum(char ch){
    if(ch=='+') return 0;
    else if(ch=='-') return 1;
    else if(ch=='*') return 2;
    else return -1;
}

ll calculate(int op, ll x, ll y){
    if(op==0) return x+y;
    else if(op==1) return x-y;
    else return x*y;
}

ll solve() {
    int tOpCnt[3]{};
    memcpy(tOpCnt, opCnt, sizeof(tOpCnt));
    vector<ll> tNums = nums;
    vector<int> tOps = ops;
    
    int ordIdx = 0;
    while(tOps.size()) {
        if(tOpCnt[ord[ordIdx]]==0) ordIdx++;
        int cOrd = ord[ordIdx];
        int sz = tOps.size();
        for(int i=0; i<sz; i++){
            int next = tOps[i];
            if(cOrd != next) continue;
            tNums[i] = calculate(next, tNums[i], tNums[i+1]);
            tOps.erase(tOps.begin()+i);
            tNums.erase(tNums.begin()+i+1);
            tOpCnt[next]--;
            break;
        }
    }
    return abs(tNums.back());
}

bool vi[3]{};
void dfs(int dpt) {
    if(dpt == M) {
        ans = max(ans, solve());
        return;
    }
    
    for(int i=0; i<3; i++){
        if(vi[i] || opCnt[i]==0) continue;
        vi[i] = true;
        ord.push_back(i);
        dfs(dpt+1);
        ord.pop_back();
        vi[i] = false;
    }
}

ll solution(string exp) {
    int now = 0;
    for(int i=0; i<exp.size(); i++){
        int next = getOperNum(exp[i]);
        if(next < 0) continue;
        opCnt[next]++;
        nums.push_back(stoll(exp.substr(now, i-now)));
        ops.push_back(next);
        now = i+1;
    }
    nums.push_back(stoll(exp.substr(now, exp.size()-now)));
    for(int i=0; i<3; i++) if(opCnt[i]) M++;
    
    dfs(0);
    return ans;
}