#include<iostream>
#include<algorithm>
using namespace std;

int N;
int v[2001]{};
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cin>>N;
    for(int i=0; i<N; i++) cin>>v[i];
    sort(v,v+N);

    int cnt=0;
    for(int i=0; i<N; i++){
        int l=0, r=N-1;
        while(l<r){
            int csum = v[l] + v[r];
            if(csum < v[i]) l++;
            else if(csum > v[i]) r--;
            else {
                if(i!=l && i!=r) {cnt++; break;}
                if(i==l) l++;
                else if(i==r) r--;
            }
        }
    }
    cout<<cnt;
}