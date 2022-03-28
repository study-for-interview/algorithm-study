#include<bits/stdc++.h>
#define aa first
#define bb second
using namespace std;
typedef pair<int,int> pii;

int N,M,min_=1e9;
int m[50][50]{};
vector<pii> h;
vector<pii> c;
vector<pii> st;

void selectC(int cur){
    if(st.size() == M){
        int sum_=0;
        for(auto val1 : h){
            int mdis=1e9;
            for(auto val2 : st){
                int t = abs(val1.aa-val2.aa)+abs(val1.bb-val2.bb);
                mdis = min(t,mdis);
            }
            sum_+=mdis;
        }
        if(sum_<min_) min_ = sum_;
    }

    for(int i=cur+1; i<c.size(); i++){
        st.push_back(c[i]);
        selectC(i);
        st.pop_back();
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>M;

    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            cin>>m[i][j];
            if(m[i][j]==1) h.push_back({i,j});
            if(m[i][j]==2) c.push_back({i,j});
        }
    }

    selectC(-1);
    cout<<min_;
}