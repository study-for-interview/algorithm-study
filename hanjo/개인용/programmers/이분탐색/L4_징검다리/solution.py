def solution(distance, rocks, n):
    rocks = sorted(rocks + [0, distance])
    left = rocks[0]
    right = rocks[-1]
    print(rocks)

    while left <= right :
        mid = (left+right)//2
        cnt = temp = 0
        for r in rocks:
            if r - temp < mid :
                cnt+=1
            else:
                temp = r
        if cnt > n :
            right = mid -1
        else:
            left = mid +1
            answer = mid
    return answer

distance = 25
rocks = [2, 14, 11, 21, 17]
n = 2
print(solution(distance, rocks, n))

'''
distance = 42
rocks = [10, 20, 30, 40, 41]
n = 1
print(solution(distance, rocks, n))
'''




# 그냥 막풀어서 시간초과된 답. 이분탐색으로 풀자
from itertools import combinations

def solution2(distance, rocks, n):
    combi = []
    combi.extend(combinations(rocks,n))
    min_dis = []
    for c in combi:
        del_rock = sorted(list(set(rocks)-set(c)) + [0, distance])
        dis = []
        for i, r in enumerate(del_rock):
            if i == 0 :
                continue
            dis.append(r - del_rock[i-1])
        min_dis.append(min(dis))
    return max(min_dis)