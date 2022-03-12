from collections import deque
def solution(cacheSize, cities):
    time = 0
    # 빈 큐를 생성
    queue = deque([])
    for c in cities:
        c = c.upper()
        # cache hit
        if c in queue:
            time +=1
            queue.remove(c)
            queue.append(c)
        # cache miss
        else :
            time +=5
            queue.append(c)
            # 큐가 꽉 찼다면
            if len(queue) > cacheSize:
                queue.popleft()
    return time
    
cacheSize = 3
cities = ['Jeju', 'Pangyo', 'Seoul', 'NewYork', 
'LA', 'Jeju', 'Pangyo', 'Seoul', 'NewYork', 'LA']
print(solution(cacheSize, cities))
