#include<bits/stdc++.h>
using namespace std;

int getNum(string str){
    if(str=="cpp" || str=="backend" || str=="junior" || str=="chicken") return 0;
    else if(str=="java" || str=="frontend" || str=="senior" || str=="pizza") return 1;
    else return 2;
}

vector<int> m[4][3][3][3];
vector<int> solution(vector<string> info, vector<string> query) {
    for(int i=0; i<info.size(); i++){
        char lang[10];
        char occup[10];
        char career[10];
        char food[10];
        int score;
        sscanf(info[i].c_str(), "%s %s %s %s %d", lang, occup, career, food, &score);
        int a = getNum(lang);
        int b = getNum(occup);
        int c = getNum(career);
        int d = getNum(food);
        m[a][b][c][d].push_back(score);
        m[3][b][c][d].push_back(score);
        m[a][2][c][d].push_back(score);
        m[a][b][2][d].push_back(score);
        m[a][b][c][2].push_back(score);
        m[3][2][c][d].push_back(score);
        m[3][b][2][d].push_back(score);
        m[3][b][c][2].push_back(score);
        m[a][2][2][d].push_back(score);
        m[a][2][c][2].push_back(score);
        m[a][b][2][2].push_back(score);
        m[3][2][2][d].push_back(score);
        m[3][2][c][2].push_back(score);
        m[3][b][2][2].push_back(score);
        m[a][2][2][2].push_back(score);
        m[3][2][2][2].push_back(score);
    }
    
    vector<int> ans;
    for(int i=0; i<query.size(); i++){
        char lang[10];
        char occup[10];
        char career[10];
        char food[10];
        int score;
        sscanf(query[i].c_str(), "%s and %s and %s and %s %d", lang, occup, career, food, &score);
        string l=lang, o=occup, cr=career, f=food;
        int a = l=="-" ? 3 : getNum(lang);
        int b = o=="-" ? 2 : getNum(occup);
        int c = cr=="-" ? 2 : getNum(career);
        int d = f=="-" ? 2 : getNum(food);
        int cnt = 0;
        for(int j=0; j<m[a][b][c][d].size(); j++){
            int now = m[a][b][c][d][j];
            if(now >= score) cnt++;
        }
        ans.push_back(cnt);
    }
    return ans;
}