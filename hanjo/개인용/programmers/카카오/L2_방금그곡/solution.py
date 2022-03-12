from datetime import datetime
from collections import defaultdict

def change_scale(scale):
    scale = scale.replace('C#', 'c')
    scale = scale.replace('D#', 'd')
    scale = scale.replace('F#', 'f')
    scale = scale.replace('G#', 'g')
    scale = scale.replace('A#', 'a')
    return scale

def solution(m, musicinfos):
    # 시간 : [제목] 담을 딕셔너리
    title = defaultdict(lambda : [])
    for info in musicinfos:
        info = info.split(',')
        # 1. 시간 차이(초)부터 구하기
        t1 = datetime.strptime(info[0], '%M:%S')
        t2 = datetime.strptime(info[1], '%M:%S')
        time = (t2-t1).seconds

        # 2. X# << 이런거 문자 하나로 변환
        scale = change_scale(info[3])
        m = change_scale(m)

        # 3. 몫(반복횟수)과 나머지(추가 반복) 구하기.
        rem = time % len(scale)
        quo = int(time/len(scale))

        # 4. 몫 만큼 악보 곱
        exp_scale = scale*quo

        # 5. 나머지만큼 악보 추가
        for i in range(rem):
            exp_scale += scale[i]

        # 6. 확장 악보에 찾는 음절 존재하면 딕셔너리에 추가
        if m in exp_scale:
            title[time].append(info[2]) 

    if not title:
        return '(None)'
    else:   # 가장 큰 시간에서 제일 먼저 나온 제목 반환
        return title[max(title.keys())][0]


m = 'ABCDEFG'	
musicinfos = ['12:00,12:14,HELLO,CDEFGAB',
 '13:00,13:05,WORLD,ABCDEF']
print(solution(m, musicinfos))

m = 'ABC'
musicinfos =['12:00,12:14,HELLO,C#DEFGAB',
 '13:00,13:05,WORLD,ABCDEF']
print(solution(m, musicinfos))

m = 'CC#BCC#BCC#BCC#B'
musicinfos =['03:00,03:30,FOO,CC#B',
 '04:00,04:08,BAR,CC#BCC#BCC#B']
print(solution(m, musicinfos))

