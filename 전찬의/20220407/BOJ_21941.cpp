#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using psi = pair<string,int>;

int N;
psi m[101]{};
int dp[1001]{};
string S;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    string str;
    int t,n;
    cin>>S;
    cin>>n;
    N=0;
    for(int i=0; i<n; i++){
        cin>>str;
        cin>>t;
        if(str.size() >= t) continue;
        m[N++] = {str, t};
    }
    
    dp[0] = 0;
    for(int i=1; i<=S.size(); i++){
        dp[i] = dp[i-1] + 1;
        char now = S[i-1];
        for(int j=0; j<N; j++){
            string cstr = m[j].xx;
            if(i < cstr.size()) continue;
            bool chk = true;
            for(int k=0; k<cstr.size(); k++){
                if(S[i-1-k]==cstr[cstr.size()-1-k]) continue;
                chk = false;
                break;
            }
            if(!chk) continue;
            dp[i] = max(dp[i], dp[i-cstr.size()] + m[j].yy);
        }
    }

    cout<<dp[S.size()];
}