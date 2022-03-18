// 16분
#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

char m[5][5]{};
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};
bool vi[5][5]{};

bool bfs(int y, int x) {
    memset(vi, 0, sizeof(vi));
    queue<pair<int,pii>> q;
    q.push({0,{y,x}});
    vi[y][x] = true;
    
    while(!q.empty()){
        int cd = q.front().xx;
        int cy = q.front().yy.xx;
        int cx = q.front().yy.yy;
        q.pop();
        
        for(int i=0; i<4; i++){
            int nd = cd + 1;
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if(ny<0 || nx<0 || ny>=5 || nx>=5) continue;
            if(m[ny][nx]=='X'|| vi[ny][nx]) continue;
            if(m[ny][nx]=='P') return false;
            if(nd < 2) {
                q.push({nd, {ny,nx}});
            }
        }
    }
    return true;
}

bool solve(){
    for(int i=0; i<5; i++){
        for(int j=0; j<5; j++){
            if(m[i][j]=='P') {
                if(!bfs(i,j)) return false;
            }
        }
    }
    return true;
}

vector<int> solution(vector<vector<string>> places) {
    vector<int> ans;
    for(int k=0; k<places.size(); k++){
        for(int i=0; i<places[k].size(); i++){
            string str = places[k][i];
            for(int j=0; j<str.size(); j++){
                m[i][j] = str[j];
            }
        }
        ans.push_back(solve());
    }
    
    return ans;
}