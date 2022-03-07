import string 
def solution(name):
    al = string.ascii_uppercase
    # 효율적인 좌우 이동거리 추출
    l_name = len(name)
    start, end = 0, 0
    lr = []
    while True:
        if l_name > end+1 and name[end+1] == 'A': 
            end += 1
        else:
            lr.append((start*2-1) + (l_name-end))
            if l_name != end+1:
                start = end+1
                end = start
            else:
                break
    # 알파벳 상하 최소 이동 추출
    ud = 0
    for n in name:
        n_idx = al.index(n)
        if n_idx > 13:
            n_idx = 26 - n_idx
        ud += n_idx
    return ud + min(lr)