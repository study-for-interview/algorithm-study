#include<bits/stdc++.h>
using namespace std;

string a;
vector<char> stk;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>a;

    for(int i=0; i<a.size(); i++){
        stk.push_back(a[i]);
        if(stk.size()<4) continue;

        while(1) {
            int sz = stk.size();
            if(sz<4) break;
            if(stk[sz-1]=='P' && stk[sz-2]=='A' && stk[sz-3]=='P' && stk[sz-4]=='P') {
                stk.pop_back(); stk.pop_back(); stk.pop_back();
                continue;
            }
            break;
        }
    }

    if(stk.size()==1 && stk[0]=='P') cout<<"PPAP";
    else cout<<"NP";
}