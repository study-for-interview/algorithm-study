#include<bits/stdc++.h>
using namespace std;
using tpi = tuple<int,int,int>;

int N,C,M; // 2000, 10000, 10000
vector<tpi> v;
int box[2001]{};

int main() {
    ios_base::sync_with_stdio(false); 
    cin.tie(0);

    cin>>N>>C;
    cin>>M;
    int a,b,c;
    for(int i=0; i<M; i++){
        cin>>a>>b>>c;
        v.push_back({a,b,c});
    }

    sort(v.begin(), v.end(), [&](tpi a, tpi b) -> bool {
        int a1, a2, a3;
        int b1, b2, b3;
        tie(a1, a2, a3) = a;
        tie(b1, b2, b3) = b;
        if(a2==b2) return a1 < b1;
        return a2 < b2;
    });

    int ans = 0;
    for(int i=0; i<v.size(); i++){
        int a,b,c;
        tie(a,b,c) = v[i];
        int maxBox = 0;
        for(int j=a; j<b; j++){
            maxBox = max(maxBox, box[j]);
        }
        int available = C - maxBox;
        int nextc = min(available, c);
        for(int j=a; j<b; j++) box[j] += nextc;
        ans += nextc;
    }

    cout<<ans;
}

/*
size = 60
ans = 90
1: 30
2: 20
3: 60
4: 20
5: 0
6: 0

C = 60
1 2 30
3 4 40
2 5 70
1 6 40
5 7 60

*/