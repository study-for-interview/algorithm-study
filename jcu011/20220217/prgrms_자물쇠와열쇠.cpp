#include<bits/stdc++.h>
using namespace std;

int ksz, lsz, hole=0;
int m[61][61]{};
int tkey[21][21]{};

bool solve() {
    for(int i=0; i<ksz+lsz; i++){
        for(int j=0; j<ksz+lsz; j++){
            bool chk = true;
            int cnt = 0;
            
            for(int y=0; y<ksz; y++){
                for(int x=0; x<ksz; x++){
                    int ny = i+y;
                    int nx = j+x;
                    if(ny<ksz || nx<ksz || ny>=ksz+lsz || nx>=ksz+lsz) continue;
                    if(tkey[y][x] && m[ny][nx]){
                        chk = false;
                        break;
                    } else if(tkey[y][x] && !m[ny][nx]) cnt++;
                }
                if(!chk) break;
            }
            if(chk && hole==cnt) return true;
        }
    }
    return false;
}

void rotateKey() {
    int tmp[21][21];
    memcpy(tmp, tkey, sizeof(tmp));
    for(int i=0, jj=0; i<ksz; i++, jj++){
        for(int j=0, ii=ksz-1; j<ksz; j++,ii--){
            tkey[i][j] = tmp[ii][jj];
        }
    }
}

bool solution(vector<vector<int>> key, vector<vector<int>> lock) {
    ksz = key.size();
    lsz = lock.size();
    for(int i=0; i<lsz; i++){
        for(int j=0; j<lsz; j++){
            m[ksz+i][ksz+j] = lock[i][j];
            if(lock[i][j]==0) hole++;
        }
    }
    
    for(int i=0; i<ksz; i++){
        for(int j=0; j<ksz; j++){
            tkey[i][j] = key[i][j];
        }
    }
    
    for(int k=0; k<4; k++){
        if(solve()) return true;
        rotateKey();
    }
    return false;
}