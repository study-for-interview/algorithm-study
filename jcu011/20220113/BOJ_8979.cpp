#include<bits/stdc++.h>
using namespace std;

struct Nation{
	int n,a,b,c;
};

int N,K;
vector<Nation> v;

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cin>>N>>K;
	int n,a,b,c;
	int idx = 0;
	for(int i=0; i<N; i++){
		cin>>n>>a>>b>>c;
		v.push_back({n,a,b,c});
		if(n==K) idx = i;
	}

	int ans = 0;
	Nation now = v[idx];
	for(int i=0; i<N; i++){
		Nation next = v[i];
		if(next.a > now.a) {ans++; continue;}
		else if(next.a==now.a){
			if(next.b > now.b) {ans++; continue;}
			else if(next.b==now.b){
				if(next.c > now.c) {ans++; continue;}
			}
		}
	}

	cout<<ans+1;
}