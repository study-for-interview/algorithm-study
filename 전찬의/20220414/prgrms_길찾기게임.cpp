#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

int root;
vector<pii> node;
int tree[10001][2]{};
vector<int> preNode;
vector<int> postNode;

void preOrder(int node){
    if(node<0) return;
    preNode.push_back(node+1);
    preOrder(tree[node][0]);
    preOrder(tree[node][1]);
}
void postOrder(int node){
    if(node<0) return;
    postOrder(tree[node][0]);
    postOrder(tree[node][1]);
    postNode.push_back(node+1);
}

vector<vector<int>> solution(vector<vector<int>> info) {
    memset(tree,-1,sizeof(tree));
    for(int i=0; i<info.size(); i++){
        node.push_back({info[i][1], i});
    }
    sort(node.begin(), node.end(), greater<pii>());
    root = node[0].yy;
    for(int i=1; i<node.size(); i++){
        int cidx = node[i].yy;
        int cx = info[cidx][0];
        
        int nidx = root;
        while(true){
            int nx = info[nidx][0];
            if(cx < nx) {
                if(tree[nidx][0]==-1) {
                    tree[nidx][0] = cidx;
                    break;
                } else nidx = tree[nidx][0];
            } else {
                if(tree[nidx][1]==-1) {
                    tree[nidx][1] = cidx;
                    break;
                } else nidx = tree[nidx][1];
            }
        }
    }
    
    preOrder(root);
    postOrder(root);
    vector<vector<int>> ans(2,vector<int>(info.size(),0));
    for(int i=0; i<info.size(); i++){
        ans[0][i] = preNode[i];
        ans[1][i] = postNode[i];
    }
    return ans;
}