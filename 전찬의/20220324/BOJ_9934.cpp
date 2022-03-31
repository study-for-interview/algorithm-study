#include<bits/stdc++.h>
using namespace std;

int N=1;
int m[2000]{};
int tree[2000][2]{};

int solve(int st, int ed){
    int mid = (st+ed)/2;
    int num = m[mid];
    if(abs(ed-st)==1) return num;
    tree[num][0] = solve(st, mid);
    tree[num][1] = solve(mid+1, ed);
    return num;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    int n;
    cin>>n;
    for(int i=0; i<n; i++) N*=2;
    N--;
    for(int i=0; i<N; i++){
        cin>>m[i];
    }

    int root = solve(0,N);
    queue<int> q;
    q.push(root);
    while(true){
        queue<int> tmp;
        while(!q.empty()){
            int k = q.front();
            q.pop();

            if(tree[k][0]!=0) tmp.push(tree[k][0]);
            if(tree[k][1]!=0) tmp.push(tree[k][1]);

            cout<<k;
            if(!q.empty()) cout<<' ';
        }
        if(tmp.empty()) break;
        q = tmp;
        cout<<'\n';
    }
}