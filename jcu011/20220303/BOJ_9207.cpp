#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

int T;
char m[5][9]{};
vector<pii> pin;
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};
bool vi[8]{};

int ans=0;
void dfs(int dpt) {
    for(int i=0; i<pin.size(); i++){
        if(vi[i]) continue;
        int cy = pin[i].xx;
        int cx = pin[i].yy;
        for(int k=0; k<4; k++){
            int ny = cy + dy[k];
            int nx = cx + dx[k];
            int ny2 = cy + dy[k]*2;
            int nx2 = cx + dx[k]*2;
            if(ny<0 || nx<0 || ny>=5 || nx>=9) continue;
            if(m[ny][nx]=='#' || m[ny][nx]=='.') continue;
            if(ny2<0 || nx2<0 || ny2>=5 || nx2>=9) continue;
            if(m[ny2][nx2]!='.') continue;
            
            int nexti = m[ny][nx]-'0';

            m[ny2][nx2] = m[cy][cx];
            pin[i] = {ny2, nx2};
            m[cy][cx] = '.';
            vi[nexti] = true;
            m[ny][nx] = '.';
            dfs(dpt+1);
            m[ny][nx] = '0' + nexti;
            vi[nexti] = false;
            m[cy][cx] = '0' + i;
            pin[i] = {cy,cx};
            m[ny2][nx2] = '.';
        }
    }

    ans = max(ans, dpt);
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>T;
    string str;
    while(T--){
        pin.clear();
        memset(vi, 0, sizeof(bool));
        ans = 0;

        int cnt = 0;
        for(int i=0; i<5; i++) {
            cin>>str;
            for(int j=0; j<9; j++) {
                m[i][j] = str[j];
                if(m[i][j]=='o') {
                    pin.push_back({i,j});
                    m[i][j] = '0' + cnt;
                    cnt++;
                }
            }
        }
        getline(cin, str);

        dfs(0);
        cout<<pin.size()-ans<<' '<<ans<<'\n';
    }

}
