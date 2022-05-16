# 테케 3, 8 실패

def extension(place):
    ext_place = ['X'*7]
    for row in place:
        ext_place.append('X' + row + 'X')
    ext_place.append('X'*7)
    return ext_place

def is_correct(ext_place):
    for x, row in enumerate(ext_place):
        for y, pos in enumerate(row):
            if pos == 'P':
                # 상하좌우
                top = ext_place[x-1][y]
                bottom = ext_place[x+1][y]
                left = ext_place[x][y-1]
                right = ext_place[x][y+1]
                
                # 상하좌우에 사람 있다면 0
                if top=='P' or bottom=='P' or left=='P' or right=='P': return 0
                # 대각선 상좌/상우/하좌/하우
                if ext_place[x-1][y-1] == 'P' and (top == 'O' or left == 'O'): return 0
                if ext_place[x-1][y+1] == 'P' and (top == 'O' or right == 'O'): return 0
                if ext_place[x+1][y-1] == 'P' and (bottom == 'O' or left == 'O'): return 0
                if ext_place[x+1][y+1] == 'P' and (bottom == 'O' or right == 'O'): return 0
    return 1

def solution(places):
    answer = []
    for place in places:
        ext_place = extension(place)            
        answer.append(is_correct(ext_place))
    return answer