from collections import deque

def solution(priorities, location):
    # [인덱스, 우선순위]로 그룹핑 해준 후 큐로 변환
    p_list = deque([[i,p] for i, p in enumerate(priorities)])
    # 우선순위도 높은 순 정렬 후 큐로 변환
    priorities = deque(sorted(priorities, reverse=True))
    cnt = 0
    while 1:
        # 1. 맨 앞에 있는 프린터를 꺼낸다
        temp = p_list.popleft()
        # 2. 우선순위 큐의 가장 큰 것과 순위 비교.
        if priorities[0] != temp[1]:
            # 3. 해당 프린터보다 우선순위가 더 큰게 있다면 뒤로감
            p_list.append(temp)
        else:
            # 4. 해당 프린터의 우선순위가 가장 크면 우선순위 pop.
            cnt+=1
            priorities.popleft()
            # 5. 만약 그게 내가 구하고싶은 프린터라면?
            if temp[0] == location:
                break
    return cnt


priorities = [2, 1, 3, 2]
location = 2
print(solution(priorities, location))