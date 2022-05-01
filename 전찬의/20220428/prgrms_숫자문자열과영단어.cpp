#include<bits/stdc++.h>
using namespace std;

int getSize(int k){
    if(k==0) return 4;
    if(k==1) return 3;
    if(k==2) return 3;
    if(k==3) return 5;
    if(k==4) return 4;
    if(k==5) return 4;
    if(k==6) return 3;
    if(k==7) return 5;
    if(k==8) return 5;
    if(k==9) return 4;
}

int getNum(char ch1, char ch2){
    if(ch1=='z') return 0;
    if(ch1=='o') return 1;
    if(ch1=='t') {
        if(ch2=='w') return 2;
        else return 3;
    }
    if(ch1=='f') {
        if(ch2=='o') return 4;
        else return 5;
    }
    if(ch1=='s') {
        if(ch2=='i') return 6;
        else return 7;
    }
    if(ch1=='e') return 8;
    return 9;
}

int solution(string s) {
    int ans = 0;
    for(int i=0; i<s.size(); i++){
        char ch = s[i];
        if(ch>='0' && ch<='9') {
            ans *= 10;
            ans += (ch-'0');
            continue;
        }
        int tmp = getNum(ch, s[i+1]);
        ans *= 10;
        ans += tmp;
        i+= (getSize(tmp)-1);
    }
    return ans;
}