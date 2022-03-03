#include<bits/stdc++.h>
using namespace std;
using tpi = tuple<int,int,int>;
using pii = pair<int,int>;

int N;
vector<pii> m;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    int a,b,c; 
    for(int i=0; i<N; i++){
        cin>>a>>b>>c;
        m.push_back({b,c});
    }
    sort(m.begin(), m.end());

    int ans = 0, curRoom = 0;
    priority_queue<int, vector<int>, greater<int>> pq;
    for(int i=0; i<N; i++){
        int st = m[i].first;
        int ed = m[i].second;
        
        while(!pq.empty()) {
            int now = pq.top();
            if(now > st) break;
            else {
                pq.pop();
                curRoom--;
            }
        }

        pq.push(ed);
        ans = max(ans, ++curRoom);
    }

    cout<<ans;
}