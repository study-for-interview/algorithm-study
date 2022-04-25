#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

int m[5][5]{};
int vi[5][5]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};

bool bfs(int sty, int stx){
    queue<pair<int,pii>> q;
    q.push({0,{sty,stx}});
    vi[sty][stx] = true;
    
    while(!q.empty()){
        int cd = q.front().xx;
        int cy = q.front().yy.xx;
        int cx = q.front().yy.yy;
        q.pop();
        
        if(cd==2) continue;
        for(int i=0; i<4; i++){
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=5 || nx>=5) continue;
            if(m[ny][nx]<0 || vi[ny][nx]) continue;
            if(m[ny][nx]==1) return false;
            q.push({cd+1,{ny,nx}});
            vi[ny][nx] = true;
        }
    }
    return true;
}

vector<int> solution(vector<vector<string>> places) {
    vector<int> ans;
    for(int k=0; k<places.size(); k++){
        for(int i=0; i<places[k].size(); i++){
            for(int j=0; j<places[k][i].size(); j++){
                char now = places[k][i][j];
                if(now=='P') {
                    m[i][j] = 1;
                } else if(now=='O'){
                    m[i][j] = 0;
                } else {
                    m[i][j] = -1;
                }
            }
        }
        
        memset(vi,0,sizeof(vi));
        int chk = 1;
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(!vi[i][j] && m[i][j]==1) {
                    if(!bfs(i,j)) {
                        chk=0;
                        break;
                    }
                }
            }
            if(!chk) break;
        }
        ans.push_back(chk);
    }
    
    return ans;
}