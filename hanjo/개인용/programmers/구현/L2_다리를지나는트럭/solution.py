def solution(bridge_length, weight, truck_weights):
    l = len(truck_weights)
    time = 1
    passed_truck, now_on, now_on_time =[], [truck_weights.pop(0)], [0]
    while len(passed_truck) != l:
        # 시간 증가
        time +=1
        for i, n in enumerate(now_on_time):
            now_on_time[i] += 1
        # 시간지나서 통과한 트럭 확인
        if now_on_time:
            if now_on_time[0] == bridge_length:
                passed_truck.append(now_on.pop(0))
                now_on_time.pop(0)
            # 추가가능하다면 올림
            if truck_weights:
                if sum(now_on) + truck_weights[0] <= weight:
                    now_on.append(truck_weights.pop(0))
                    now_on_time.append(0)
    return time


print(solution(2, 10, [7,4,5,6]), 8)
print(solution(100, 100, [10]), 101)
print(solution(100, 100, [10,10,10,10,10,10,10,10,10,10]), 110)