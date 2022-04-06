#include<bits/stdc++.h>
using namespace std;

int N;
vector<int> m;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    int t;
    for(int i=0; i<N; i++){
        cin>>t;
        m.push_back(t);
    }
    sort(m.begin(), m.end());

    priority_queue<int, vector<int>, greater<int>> pq;
    for(int i=0; i<N; i++) pq.push(m[i]);

    int sum = 0;
    while(pq.size()!=1){
        int sz = pq.size();
        bool odd = sz%2 == 1 ? true : false;
        for(int i=0; i<sz; i+=2){
            int a = pq.top(); pq.pop();
            if(odd && i==sz-1) {
                pq.push(a);
                break;
            }
            int b = pq.top(); pq.pop();
            sum += (a+b);
            pq.push(a+b);
        }
    }
    cout<<sum;
}