#include<bits/stdc++.h>
using namespace std;

set<int> s;
bool vi[1000001]{};

string solution(int n, int k, vector<string> cmd) {
    for(int i=0; i<n; i++) s.insert(i);
    set<int>::iterator now = s.find(k);
    vector<int> stk;
    
    for(int i=0; i<cmd.size(); i++){
        if(cmd[i][0]=='U') {
            int x = stoi(cmd[i].substr(2));
            for(int i=0; i<x; i++) now--;
        } else if(cmd[i][0]=='D') {
            int x = stoi(cmd[i].substr(2));
            for(int i=0; i<x; i++) now++;
        } else if(cmd[i][0]=='C') {
            stk.push_back(*now);
            now = s.erase(now);
            if(now == s.end()) now--;
        } else if(cmd[i][0]=='Z') {
            s.insert(stk.back());
            stk.pop_back();
        }
    }
    
    for(auto val : stk) vi[val] = true;
    string ans = "";
    for(int i=0; i<n; i++){
        if(vi[i]) ans.push_back('X');
        else ans.push_back('O');
    }
    return ans;
}