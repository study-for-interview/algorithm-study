// 미완성 코드
#include<bits/stdc++.h>
using namespace std;

bool vi[30]{};

string solution(string str) {
    if(str.size()==0) return str;
    if(str.size()==1) {
        if(str[0]>='A' && str[0]<='Z') return str;
        else return "invalid";
    }
    
    int pre = str[0]>='A' && str[0]<='Z' ? 1 : 0;
    int chk = pre==1 ? 0 : 2;
    char ch1, ch2;
    if(chk==2) {
        ch2 = str[0];
        vi[ch2-'a'] = true;
    }
    string ans = "";
    if(pre==1) ans.push_back(str[0]);
    for(int i=1; i<str.size(); i++){
        if(str[i]>='A' && str[i]<='Z') {
            if(chk==0) ans.push_back(str[i]);
            else if(chk==1) {
                if(pre==0) ans.push_back(str[i]);
                else {
                    ans.push_back(' ');
                    ans.push_back(str[i]);
                    chk = 0;
                }
            } else if(chk==2) {
                ans.push_back(str[i]);
            } else {
                if(pre==0) ans.push_back(str[i]);
                else {cout<<ans<<'\n'; return "invalid";}
            }
        } else {
            if(chk==0) {
                if(vi[str[i]-'a']) return "invalid";
                if(i+2>=str.size()) {
                    ch1 = str[i];
                    vi[ch1-'a'] = true;
                    chk = 1;
                } else {
                    if((str[i+2]>='A' && str[i+2]<='Z') || (str[i]!=str[i+2])) {
                        ans.push_back(' ');
                        ch2 = str[i];
                        vi[ch2-'a'] = true;
                        chk = 2;
                    } else {
                        ch1 = str[i];
                        vi[ch1-'a'] = true;
                        chk = 1;
                    }
                }
            } else if(chk==1) {
                if(pre==0) return "invalid";
                if(ch1!=str[i]) {
                    ans.push_back(' ');
                    if(vi[str[i]-'a']) return "invalid";
                    ch2 = str[i];
                    vi[ch2-'a'] = true;
                    chk = 2;
                }
            } else if(chk==2) {
                if(pre==0) return "invalid";
                if(str[i]==ch2) {
                    ans.push_back(' ');
                    chk = 0;
                } else {
                    if(vi[str[i]-'a']) return "invalid";
                    ch1 = str[i];
                    vi[ch1-'a'] = true;
                    chk = 3;
                }
            } else {
                if(pre==0) return "invalid";
                if(str[i]==ch2) {
                    ans.push_back(' ');
                    chk = 0;
                } else {
                    if(str[i]!=ch1) return "invalid";
                }
            }
        }
        pre = str[i]>='A' && str[i]<='Z' ? 1 : 0;
    }
    if(chk==3) return "invalid";
    if(chk==2 && (ans.back()>='A'&&ans.back()<='Z')) return "invalid";
    if(ans[ans.size()-1]==' ') ans.pop_back();
    for(int i=1; i<ans.size(); i++){
        if(ans[i]==' ' && ans[i-1]==' ') ans.erase(ans.begin()+i);
    }
    return ans;
}
// "AAAaBaAbBBBBbCcBdBdBdBcCeBfBeGgGGjGjGRvRvRvRvRvR"
// 답
// "AAA B A BBBB C BBBB C BB GG G G G RRRRRR" 또는
// "AA ABA BBBB C BBBB C BB GG GGG RRRRRR"