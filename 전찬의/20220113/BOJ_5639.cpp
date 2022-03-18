#include <bits/stdc++.h>
using namespace std;

vector<int> v;

void order(int st, int ed) {
    if(st>=ed) return;
    int mid = upper_bound(v.begin()+st+1, v.begin()+ed, v[st]) - v.begin();
    order(st+1, mid);
    order(mid, ed);
    cout<<v[st]<<'\n';
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
    int n;
    while(cin>>n){
        v.push_back(n);
    }

    order(0, v.size());
}