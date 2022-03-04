import heapq
from collections import deque

def solution(jobs):
    tasks = deque(sorted([(x[1], x[0]) for x in jobs], key=lambda x: (x[1], x[0])))
    q = []
    heapq.heappush(q, tasks.popleft())
    current_time, total_response_time = 0, 0
    while len(q) > 0:
        dur, arr = heapq.heappop(q)
        current_time = max(current_time + dur, arr + dur)
        total_response_time += current_time - arr
        while len(tasks) > 0 and tasks[0][1] <= current_time:
            heapq.heappush(q, tasks.popleft())
        if len(tasks) > 0 and len(q) == 0:
            heapq.heappush(q, tasks.popleft())
    return total_response_time // len(jobs)


print(solution([[0, 10], [2, 10], [9, 10], [15, 2]]), 14)
print(solution([[0, 10], [2, 12], [9, 19], [15, 17]]), 25)
print(solution([[0, 3], [1, 9], [2, 6]]), 9)
print(solution([[0, 1]]), 1)
print(solution([[1000, 1000]]), 1000)
print(solution([[0, 1], [0, 1], [0, 1]]), 2)
print(solution([[0, 1], [0, 1], [0, 1], [0, 1]]), 2)
print(solution([[0, 1], [1000, 1000]]), 500)
print(solution([[100, 100], [1000, 1000]]), 550)
print(solution([[10, 10], [30, 10], [50, 2], [51, 2]]), 6)
print(solution([[0, 3], [1, 9], [2, 6] , [30, 3]]), 7)
print(solution([[0, 3], [1, 9], [2, 6]]), 9)
print(solution([[0, 10], [4, 10], [5, 11], [15, 2]]), 15)
print(solution([[0, 3], [1, 9], [2, 6], [4, 3]]), 9)
print(solution([[0, 1], [1, 2], [500, 6]]), 3)
print(solution([[0, 3], [1, 9], [500, 6]]), 6)
print(solution([[0, 1], [0, 1], [1, 0]]), 1)
print(solution([[0, 3], [4, 3], [8, 3]]), 3)
print(solution([[0, 3], [4, 3], [10, 3]]), 3)
print(solution([[0, 5], [6, 2], [6, 1]]), 3)
print(solution([[0, 5], [6, 1], [6, 2]]), 3)
print(solution([[0, 5], [2, 2], [5, 3]]), 5)
print(solution([[0, 5], [2, 2], [4, 2]]), 5)
print(solution([[0, 3], [0, 1], [4, 7]]), 4)
print(solution([[0, 2], [3, 6], [3, 1]]), 3)
print(solution([[0, 5], [1, 2], [5, 5]]), 6)
print(solution([[0, 9], [0, 4], [0, 5], [0, 7], [0, 3]]), 13)
print(solution([[24, 10], [28, 39], [43, 20], [37, 5], [47, 22], [20, 47], [15, 2], [15, 34], [35, 43], [26, 1]]), 72)
print(solution([[1, 9], [1, 4], [1, 5], [1, 7], [1, 3]]), 13)