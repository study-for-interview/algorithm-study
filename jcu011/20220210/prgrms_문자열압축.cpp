#include <string>
#include <vector>

using namespace std;

int solution(string s) {
    int ans = s.size();
    for(int i=1; i<=s.size()/2; i++){
        int cnt=1, sz=i;
        for(int j=i; j<s.size(); j+=i){
            bool chk = true;
            for(int k=j; k<j+i; k++){
                if(k>=s.size() || s[k]!=s[k-i]) {
                    chk = false;
                    break;
                }
            }
            if(chk) {
                if(cnt==1) sz++;
                cnt++;
                if(cnt==10 || cnt==100 || cnt== 1000) sz++;
            }
            else {
                cnt = 1;
                if(j+i>s.size()) sz += s.size()-j;
                else sz += i;
            }
        }
        ans = min(ans, sz);
    }
    return ans;
}