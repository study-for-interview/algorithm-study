import heapq
def solution(scoville, K):
    heapq.heapify(scoville)
    count = 0
    while 1:
        s1 = heapq.heappop(scoville)
        if s1 >= K:
            break
        if len(scoville) == 0:
            count = -1
            break
        s2 = heapq.heappop(scoville)
        heapq.heappush(scoville, s1 + s2*2)
        count+=1
    return count


scoville = [1, 2, 3, 9, 10, 12]
K = 7
print(solution(scoville, K))

scoville = [1,2,3]
K = 11
print(solution(scoville, K))

