# 효율성 초과됨. -> 다시풀어야됨
# from itertools import permutations
# def solution(numbers):
#     str_per_list=[]
#     per = list(permutations(numbers, len(numbers)))
#     for p in per:
#         str_per = "".join([str(_) for _ in p])
#         str_per_list.append(int(str_per))
#     answer = max(str_per_list)
#     return answer


# def solution(numbers):
#     numbers = list(map(str, numbers))
#     numbers.sort(key=lambda x: x*3, reverse=True)
#     return str(int(''.join(numbers)))


# 1. 우선 
from functools import cmp_to_key
def solution(numbers):
    numbers = map(str,numbers)
    numbers = sorted(numbers, key=cmp_to_key(lambda x,y: int(x+y)-int(y+x)), reverse=True)
    return str(int(''.join(numbers)))

numbers = [6, 10, 2]
print(solution(numbers))
numbers = [3, 30, 34, 5, 9]
print(solution(numbers))
numbers = [0, 0, 0, 0]
print(solution(numbers))