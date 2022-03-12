import math

# 1. 우선 progresses, speeds 두 리스트를 가지고 남은 일수를 계산
# 2. 만약 현재날짜 < 남은일수 일때는 새로운 날짜에 배포해야하므로 날짜 최신화, 배포수 초기화
# 3. 아니라면 마지막 배포 날짜에 +1

def solution(progresses, speeds):
    day, answer = 0, []
    for p, s in zip(progresses, speeds):
        remain = math.ceil((100-p)/s)
        if day < remain:
            day = remain
            answer.append(1)
        else:
            answer[len(answer)-1] += 1
    return answer


test_case = [
    [[93, 30, 55], 	[1, 30, 5]],
    [[95, 90, 99, 99, 80, 99], [1, 1, 1, 1, 1, 1]],
    [[99, 99, 99] ,[1, 1,1]]
]

for progresses, speeds in test_case:
    print(solution(progresses, speeds))
