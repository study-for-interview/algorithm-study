#include<bits/stdc++.h>
using namespace std;
string func1(string);
string func2(string, string);

string func2(string u, string v){
    string ret = "(";
    ret += func1(v);
    ret += ")";
    u = u.substr(1,u.size()-2);
    for(int i=0; i<u.size(); i++){
        if(u[i]=='(') u[i] = ')';
        else u[i] = '(';
    }
    return ret + u;
}

string func1(string str){
    if(str=="") return "";
    int cnt = str[0]=='(' ? 1 : -1;
    int div;
    bool chk = true;
    for(int i=1; i<str.size(); i++) {
        if(cnt<0) chk = false;
        if(cnt==0) {div = i; break;}
        if(str[i]=='(') cnt++;
        else cnt--;
    }

    string u = str.substr(0, div);
    string v = str.substr(div, str.size()-div);
    if(chk) return u + func1(v);
    else return func2(u, v);
}

string solution(string p) {
    return func1(p);
}