#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

pii m[12] = {
    {3,1}, {0,0}, {0,1}, {0,2},
    {1,0}, {1,1}, {1,2}, {2,0},
    {2,1}, {2,2}, {3,0}, {3,2}
};
pii clh = m[10];
pii crh = m[11];

int getDist(pii st, pii ed){
    int dy = abs(st.xx - ed.xx);
    int dx = abs(st.yy - ed.yy);
    return dy + dx;
}

char getLR(int n, bool isr){
    if(n==1 || n==4 || n==7) {clh = m[n]; return 'L';}
    else if(n==3 || n==6 || n==9) {crh = m[n]; return 'R';}
    else { // 2,5,8,0
        int ld = getDist(clh, m[n]);
        int rd = getDist(crh, m[n]);
        if(ld < rd) {clh = m[n]; return 'L';}
        else if(rd < ld) {crh = m[n]; return 'R';}
        else {
            if(isr) {crh = m[n]; return 'R';}
            else {clh = m[n]; return 'L';}
        }
    }
}

string solution(vector<int> nums, string hand) {
    bool isr;
    if(hand=="right") isr = true;
    else isr = false;
    string ans = "";
    
    for(int i=0; i<nums.size(); i++){
        ans += getLR(nums[i], isr);
    }
    
    return ans;
}