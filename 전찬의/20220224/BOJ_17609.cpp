#include<bits/stdc++.h>
using namespace std;

int N;

int solve(string str, int st, int ed, bool chk) {
    int l=st, r=ed;
    bool isDel = chk;
    while(l<=r) {
        if(str[l]==str[r]) {
            l++;
            r--;
        } else {
            if(isDel) return 2;
            if(l+1==r) return 1;
            
            isDel = true;
            if(str[l]==str[r-1] || str[l+1]==str[r]) {
                if(str[l]==str[r-1] && solve(str, l+1, r-2, isDel)==1) return 1;
                if(str[l+1]==str[r] && solve(str, l+2, r-1, isDel)==1) return 1;
            }
            
            return 2;
        }
    }
    if(isDel) return 1;
    else return 0;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    string str;
    for(int i=0; i<N; i++){
        cin>>str;
        cout<<solve(str, 0, str.size()-1, false)<<'\n';
    }

}
/*
2
xTxTxTaaTxTxT
TxTxTaaTxTxTx
*/