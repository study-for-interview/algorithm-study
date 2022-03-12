def solution(citations):
    citations.sort(reverse=True)
    h = len(citations)
    for i, c in enumerate(citations):
        if c <= i:
            h = i
            break
    return h
   

print(solution([3, 0, 6, 1, 5]), 3)
print(solution([6,5,4,1,0]), 3)
print(solution([22,42]), 2)