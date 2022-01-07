#include<bits/stdc++.h>
using namespace std;

int N;
int m[11];
queue<int> q;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    for(int i=0; i<N; i++) cin>>m[i];

    for(int i=N-1; i>=0; i--){
        queue<int> tmp;
        int now = m[i];
        for(int j=0; j<now; j++){
            tmp.push(q.front());
            q.pop();
        }
        tmp.push(i+1);
        while(!q.empty()) {
            tmp.push(q.front());
            q.pop();
        }

        q = tmp;
    }

    while(!q.empty()){
        cout<<q.front()<<' ';
        q.pop();
    }
}