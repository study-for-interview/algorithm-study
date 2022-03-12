def solution2(prices):
    answer = []
    for i in range(len(prices)):
        answer.append(0)
        for j in range(i+1, len(prices)):
            if prices[i] <= prices[j]:
                answer[i]+=1
            else:
                answer[i]+=1
                break
    return answer
# 효율성 159.46ms

from collections import deque

def solution(prices):
    prices = deque(prices)
    answer = []
    while prices:
        temp = prices.popleft()
        cnt=0
        for p in prices:
            cnt+=1
            if temp > p:
                break
        answer.append(cnt)
    return answer
# 효율성 60.74ms


            
prices = [1, 2, 3, 2, 3]
prices2 = [3, 2, 1, 2, 1]
prices3 = [5,4,3,2,1]
prices4 = [1,2,3,2,3,3,1]
print(solution(prices4))