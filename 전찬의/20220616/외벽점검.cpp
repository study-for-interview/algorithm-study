// https://programmers.co.kr/learn/courses/30/lessons/60062
#include<bits/stdc++.h>
using namespace std;
const int INF = 0x3f3f3f3f;

int N,M,K, ans=INF;
int chkMask[16][9]{};
vector<int> stk;

void dfs2(int dpt, int mask, int now){
    if(dpt==M || dpt==K || dpt+1 >= ans) return;
    
    for(int i=now+1; i<M; i++){
        int nmask = mask | chkMask[i][stk[dpt]];
        if(nmask==((1<<M)-1)) {
            ans = min(ans, dpt+1);
            return;
        }
        dfs2(dpt+1, nmask, i);
    }
}

bool vi[9]{};
void dfs1(int dpt) {
    if(dpt==K) dfs2(0,0,-1);
    
    for(int i=0; i<K; i++){
        if(vi[i]) continue;
        stk.push_back(i);
        vi[i] = true;
        dfs1(dpt+1);
        vi[i] = false;
        stk.pop_back();
    }
}

int solution(int n, vector<int> weak, vector<int> dist) {
    N = n; // 외벽길이
    M = weak.size(); // 취약점 개수
    K = dist.size(); // 친구들의 수
    
    for(int i=0; i<M; i++){
        for(int j=0; j<K; j++){
            if(dist[j]>=N-1) {
                chkMask[i][j] = (1<<M)-1;
                continue;
            }

            int nextr = weak[i] + dist[j];
            int lb = lower_bound(weak.begin(), weak.end(), nextr) - weak.begin();
            int ub = upper_bound(weak.begin(), weak.end(), nextr) - weak.begin();
            int idx = lb==ub ? lb-1 : lb;
            chkMask[i][j] |= ((1<<(idx-i+1))-1)<<i;
            
            if(nextr/N) nextr %= N;
            else continue;
            
            lb = lower_bound(weak.begin(), weak.end(), nextr) - weak.begin();
            ub = upper_bound(weak.begin(), weak.end(), nextr) - weak.begin();
            idx = lb==ub ? lb-1 : lb;
            chkMask[i][j] |= ((1<<(idx+1))-1);
        }
    }
    
    dfs1(0);

    // for(int i=0; i<M; i++){
    //     for(int j=0; j<K; j++){
    //         cout<<'('<<i<<','<<j<<") : ";
    //         for (int k = 5; k>=0; k--) {
    //             cout<<(chkMask[i][j]>>k & 1);
    //         }
    //         cout<<'\n';
    //     }
    // }
    
    if(ans==INF) return -1;
    else return ans;
}