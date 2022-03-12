from collections import Counter

def solution(N, stages):
    fail = {i : 0 for i in range(1, N+1)}
    all = len(stages)
    for stage, count in sorted(Counter(stages).items(), key = lambda x: x[0]):
        fail[stage] = count / all
        all -= count
    return [stage for stage in sorted(fail, key = lambda x: fail[x], reverse=True) if stage <= N]


test_case = [
    [5, [2, 1, 2, 6, 2, 4, 3, 3]],
    [4, [4,4,4,4,4]],
]

for N, stages in test_case:
    print(solution(N, stages))