#include<bits/stdc++.h>
using namespace std;

int num[26]{};

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	num[0] = 3; num[1] = 2;
	num[2] = 1; num[3] = 2;
	num[4] = 3; num[5] = 3;
	num[6] = 3; num[7] = 3;
	num[8] = 1; num[9] = 1;
	num[10] = 3; num[11] = 1;
	num[12] = 3; num[13] = 3;
	num[14] = 1; num[15] = 2;
	num[16] = 2; num[17] = 2;
	num[18] = 1; num[19] = 2;
	num[20] = 1; num[21] = 1;
	num[22] = 2; num[23] = 2;
	num[24] = 2; num[25] = 1;

	string str;
	cin>>str;

	int sum=0;
	for(int i=0; i<str.size(); i++){
		sum += num[str[i]-'A'];
	}

	if((sum%10)%2)cout<<"I'm a winner!";
	else cout<<"You're the winner?";

}