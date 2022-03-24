#include<bits/stdc++.h>
using namespace std;

int L,C;
char m[16]{};
vector<char> res;

bool isMo(char ch){
    if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') return true;
    else return false;
}

void dfs(int dpt, int now, int mo, int ja){
    if(dpt==L) {
        if(mo<1 || ja<2) return;
        for(auto ch : res) cout<<ch;
        cout<<'\n';
        return;
    }

    for(int i=now+1; i<C; i++){
        bool moChk = isMo(m[i]);
        int nMo=mo, nJa=ja;
        if(moChk) nMo+=1;
        else nJa+=1;
        res.push_back(m[i]);
        dfs(dpt+1, i, nMo, nJa);
        res.pop_back();
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>L>>C;
    for(int i=0; i<C; i++) cin>>m[i];
    sort(m, m+C);

    dfs(0, -1, 0, 0);
}