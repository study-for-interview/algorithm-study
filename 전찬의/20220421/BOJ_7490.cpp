#include<bits/stdc++.h>
using namespace std;

int T,N;
vector<string> ans;
string str="1";

void dfs(int dpt){
    if(dpt==N) {
        vector<int> v;
        v.push_back(1);
        for(int i=2; i<str.size(); i+=2){
            if(str[i-1]=='-') v.push_back((str[i]-'0')*-1);
            else if(str[i-1]=='+') v.push_back((str[i]-'0'));
            else {
                int k = v.back()*10;
                v.pop_back();
                if(k<0) k -= (str[i]-'0');
                else k += (str[i]-'0');
                v.push_back(k);
            }
        }
        int sum = 0;
        for(int i=0; i<v.size(); i++) sum += v[i];
        if(sum==0) ans.push_back(str);
        return;
    }

    for(int i=0; i<3; i++){
        if(i==0) str += '-';
        else if(i==1) str += '+';
        else str += ' ';
        str += (dpt+1+'0');
        dfs(dpt+1);
        str.pop_back();
        str.pop_back();
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>T;
    while(T--){
        cin>>N;
        ans.clear();
        dfs(1);
        sort(ans.begin(), ans.end());
        for(int i=0; i<ans.size(); i++){
            cout<<ans[i]<<'\n';
        }
        cout<<'\n';
    }
}