#include<bits/stdc++.h>
using namespace std;
using ll = long long;

vector<ll> v;

bool isPrime(ll num) {
    if(num<2LL) return false;
    for(ll i=2; i*i<=num; i++){
        if(num%i == 0) return false;
    }
    return true;
}

int solution(int n, int k) {
    vector<ll> tmp;
    tmp.push_back(0);
    while(n){
        tmp.push_back(n%k);
        n/=k;
    }
    tmp.push_back(0);
    
    int st = 0, ans = 0;
    ll num = 0;
    for(int i=tmp.size()-2; i>=0; i--) {
        if(!tmp[i]) {
            if(isPrime(num)) ans++;
            st = i;
            num = 0;
            continue;
        }
        num *= 10;
        num += tmp[i];
    }
    return ans;
}