#include<bits/stdc++.h>
#define xx first
#define yy second
using namespace std;
using pii = pair<int,int>;

vector<int> busTime;
vector<int> crewTime;

string solution(int N, int T, int M, vector<string> timetable) {
    for(int i=0; i<timetable.size(); i++){
        int h,m, ctime=0;
        sscanf(timetable[i].c_str(),"%d:%d", &h,&m);
        ctime += (60*h + m);
        crewTime.push_back(ctime);
    }
    for(int i=0, stTime=540; i<N; i++, stTime += T){
        if(stTime>=24*60) break;
        busTime.push_back(stTime);
    }
    sort(crewTime.begin(), crewTime.end());
    
    int ansTime = 0;
    int cnt = 0;
    for(int i=0; i<busTime.size(); i++){
        int t1=0, t2=0;
        if(i) t1 = upper_bound(crewTime.begin(), crewTime.end(), busTime[i-1]) - crewTime.begin();
        t2 = upper_bound(crewTime.begin(), crewTime.end(), busTime[i]) - crewTime.begin();
        int waiting = t2 - t1;
        cnt += waiting;
        cnt -= M;
        if(cnt<0) {
            ansTime = busTime[i];
            cnt = 0;
        } else {
            ansTime = crewTime[t2-cnt-1]-1;
        }
    }
    
    string a = to_string(ansTime/60);
    string b = to_string(ansTime%60);
    if(a.size()==1) a = "0"+a;
    if(b.size()==1) b = "0"+b;
    string ans = a +":"+ b;
    return ans;
}
/*
int n=10, t=25, m=1
timetable = {"09:00", "09:10", "09:20", "09:30", "09:40", "09:50", "10:00", "10:10", "10:20", "10:30", "10:40", "10:50"};
// 답 : "10:29"

n번째 버스 지나간 후 남은 크루
1: 09:00 -> 
2: 09:25 -> 09:20
3: 09:50 -> 09:30, 09:40, 09:50
4: 10:15 -> 09:40, 09:50, 10:00, 10:10,
5: 10:40 -> 09:50, 10:00, 10:10, 10:20, 10:30, 10:40
6: 11:05 -> 10:00, 10:10, 10:20, 10:30, 10:40, 10:50
7: 11:30 -> 10:10, 10:20, 10:30, 10:40, 10:50
8: 11:55 -> 10:20, 10:30, 10:40, 10:50
9: 12:20 -> 10:30, 10:40, 10:50
10: 12:45 -> 10:40, 10:50
*/