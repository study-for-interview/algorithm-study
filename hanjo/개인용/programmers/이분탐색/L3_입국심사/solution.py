def solution(n, times):
    left = 0
    right = n*max(times)
    while left < right :
        mid = (left+right)//2
        quo = 0
        for t in times:
            quo += mid//t
        # n이 더 큰 경우 오른쪽을 살핀다
        if quo < n:
            left = mid+1
        # n이 더 작으면 왼쪽을 살핀다
        else:
            right = mid
            answer = mid
    return answer


n = 6
times = [7, 10]
print(solution(n, times))
