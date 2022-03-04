from itertools import permutations

def is_prime(n):
    if n==1 or n==0:
        return False
    for i in range(2, n):
        if n%i==0:
            return False
    return True

def solution(numbers):
    num_set = set()
    for i in range(1, len(numbers)+1):
        for p in permutations(numbers, i):
            # 튜플 -> 문자열로 합침 -> 정수로 변환
            num = int("".join(p))
            if is_prime(num):
                num_set.add(num)
    return len(num_set)