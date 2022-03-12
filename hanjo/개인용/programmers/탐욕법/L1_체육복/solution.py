def solution(n, lost, reserve):
    # 도둑 맞았는데 여별 갖고있는 놈 미리 제거해야됨.
    for r in reserve:
        for l in lost:
            if r==l:
                lost.remove(l)
                reserve.remove(r)
    for r in reserve:
        for l in lost:
            if r+1==l or r-1==l or r==l:
                lost.remove(l)
                break
    answer = n - len(lost)            
    return answer

print(solution(5,[2, 4],[1, 3, 5]))
print(solution(5,[2, 4],[3]))
print(solution(3,[3],[1]))
print(solution(3,[3],[3]))
print(solution(5, [2,3], [3,4]))