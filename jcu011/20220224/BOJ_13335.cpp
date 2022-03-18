#include<bits/stdc++.h>
using namespace std;
using pii = pair<int,int>;

int N,W,L;
int m[1001]{};

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>W>>L;

    for(int i=0; i<N; i++) cin>>m[i];
    
    int idx=0, nl=0, cnt=0, time=0;
    queue<pii> q;    
    while(cnt < N){
        int qsz = q.size();
        for(int i=0; i<qsz; i++){
            int ci = q.front().first;
            int cp = q.front().second;
            q.pop();

            if(cp==W) {
                nl -= m[ci];
                cnt++;
            } else q.push({ci, cp+1});
        }

        if(nl + m[idx] <= L) {
            q.push({idx, 1});
            nl += m[idx];
            idx++;
        }
        time++;
    }

    cout<<time;
}