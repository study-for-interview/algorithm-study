#include<bits/stdc++.h>
using namespace std;

int N;
string str;
int alp[30]{};

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    cin>>str;
    
    int st=0, ed=0, cn=1, ans=1;
    alp[str[0]-'a']++;
    while(st<=ed) {
        if(++ed == str.size()) break;
        
        int nAlpi = str[ed]-'a';
        alp[nAlpi]++;
        if(alp[nAlpi] == 1) cn++;

        if(cn<=N) {
            ans = max(ans, ed-st+1);
            continue;
        }

        while(cn>N) {
            int cAlpi = str[st++]-'a';
            alp[cAlpi]--;
            if(alp[cAlpi]==0) cn--;
        }
    }

    cout<<ans;
}