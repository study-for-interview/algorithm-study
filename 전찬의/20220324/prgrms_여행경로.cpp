#include<bits/stdc++.h>
using namespace std;

int K;
vector<int> adj[10001]{};
vector<int> ord;
vector<string> ans;
map<string,int> m;
string rm[10001]{};

bool vi[10001][10001]{}, chk=false;
void dfs(int dpt, int now) {
    if(chk) return;
    if(dpt==K) {
        for(int i=0; i<ord.size(); i++)
            ans.push_back(rm[ord[i]]);
        chk = true;
        return;
    }
    
    for(int i=0; i<adj[now].size(); i++){
        int next = adj[now][i];
        if(vi[now][i]) continue;
        vi[now][i] = true;
        ord.push_back(next);
        dfs(dpt+1, next);
        if(chk) return;
        ord.pop_back();
        vi[now][i] = false;
    }
    
}

vector<string> solution(vector<vector<string>> tk) {
    int idx = 1;
    for(int i=0; i<tk.size(); i++){
        string str1 = tk[i][0];
        string str2 = tk[i][1];
        int n1, n2;
        if(m.count(str1)) n1 = m[str1];
        else {
            m[str1] = idx;
            rm[idx] = str1;
            n1 = idx;
            idx++;
        }
        if(m.count(str2)) n2 = m[str2];
        else {
            m[str2] = idx;
            rm[idx] = str2;
            n2 = idx;
            idx++;
        }
        adj[n1].push_back(n2);
    }
    
    for(int i=1; i<idx; i++){
        vector<string> tmp;
        for(int j=0; j<adj[i].size(); j++){
            tmp.push_back(rm[adj[i][j]]);
        }
        sort(tmp.begin(), tmp.end());
        adj[i].clear();
        for(int j=0; j<tmp.size(); j++){
            adj[i].push_back(m[tmp[j]]);
        }
    }
    
    K = tk.size();
    int st = m["ICN"];
    ord.push_back(st);
    dfs(0, st);

    return ans;
}