#include<bits/stdc++.h>
using namespace std;
using tpi = tuple<int,int,int>;

int N,K;
bool vi[101]{};
vector<tpi> v;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N>>K;
    int t;
    for(int i=0; i<K; i++){
        cin>>t;
        if(!vi[t]) {
            if(v.size() >= N) {
                sort(v.begin(), v.end(), [&](tpi a, tpi b) -> bool {
                    if(get<0>(a)==get<0>(b)) return get<1>(a) > get<1>(b);
                    return get<0>(a) > get<0>(b);
                });
                int stu = get<2>(v.back());
                vi[stu] = false;
                v.pop_back();
            }
            v.push_back({1, i, t});
            vi[t] = true;
        } else {
            for(int i=0; i<v.size(); i++){
                int stu = get<2>(v[i]);
                if(stu == t) {
                    get<0>(v[i])++;
                    break;
                }
            }
        }
    }
    
    sort(v.begin(), v.end(), [&](tpi a, tpi b) -> bool {
        return get<2>(a) < get<2>(b);
    });

    for(int i=0; i<v.size(); i++) cout<<get<2>(v[i])<<' ';
}
