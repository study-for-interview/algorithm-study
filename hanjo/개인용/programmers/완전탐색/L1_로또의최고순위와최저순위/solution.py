def convert_rank(num):
    rank = 6 - num + 1
    if rank >= 6 :
        rank = 6
    return rank

def solution(lottos, win_nums):
    match, zero = 0, 0
    for num in lottos :
        if not num :
            zero += 1
        if num in win_nums:
            match += 1
    return [convert_rank(match+zero), convert_rank(match)]


test_case = [
    [[44, 1, 0, 0, 31, 25],	[31, 10, 45, 1, 6, 19]],
    [[0, 0, 0, 0, 0, 0],	[38, 19, 20, 40, 15, 25]],
    [[45, 4, 35, 20, 3, 9],	[20, 9, 3, 45, 4, 35]]
]

for lottos, win_nums in test_case:
    print(solution(lottos, win_nums))