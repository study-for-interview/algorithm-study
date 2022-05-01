#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

int N,M,root, maxValue=0;
vector<int> nums;
vector<int> adj[10001]{};

int K = 1;
int isP(int now, int mid) {
    int cur = nums[now];
    if(adj[now].size()==0) return cur;
    
    int k=1, a,b;
    a = adj[now][0];
    int asum = isP(a, mid);
    if(adj[now].size()==1) {
        if(cur+asum <= mid) return cur+asum;
        else {K++; return cur;}
    }
    b = adj[now][1];
    int bsum = isP(b, mid);
    
    if(cur+asum+bsum <= mid) return cur+asum+bsum;
    else {
        K++;
        if(cur+asum > mid && cur+bsum > mid) { K++; return cur; }
        if(cur+asum <= mid && cur+bsum <= mid) return cur + min(asum, bsum);
        if(cur+asum <= mid) return cur+asum;
        if(cur+bsum <= mid) return cur+bsum;
    }
}

int solution(int k, vector<int> num, vector<vector<int>> links) {
    nums = num;
    bool vi[10001]{};
    for(int i=0; i<num.size(); i++) maxValue = max(maxValue, num[i]);
    for(int i=0; i<links.size(); i++){
        int a = links[i][0];
        int b = links[i][1];
        if(a!=-1) {
            vi[a] = true;
            adj[i].push_back(a);
        }
        if(b!=-1) {
            vi[b] = true;
            adj[i].push_back(b);
        }
    }
    
    int sum = 0;
    for(int i=0; i<num.size(); i++) {
        if(!vi[i]) root = i;
        sum += num[i];
    }

    int l=maxValue, r=sum;
    while(l<=r){
        int mid = (l+r)/2;
        K = 1;
        isP(root, mid);
        if(K<=k) r = mid - 1;
        else l = mid + 1;
    }
    return l;
}