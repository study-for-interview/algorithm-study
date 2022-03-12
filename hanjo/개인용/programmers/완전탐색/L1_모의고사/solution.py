def solution(answers):
    patterns = [[1, 2, 3, 4, 5], [2, 1, 2, 3, 2, 4, 2, 5], [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]]
    cnt_list = []
    for p in patterns:
        cnt = 0
        for i, a in enumerate(answers):
            if p[i%len(p)] == a:
                cnt += 1
        cnt_list.append(cnt)
    answer = [i+1 for i, c in enumerate(cnt_list) if c == max(cnt_list)]
    return answer



answers = [1,2,3,4,5]
print(solution(answers))
answers = [1,3,2,4,2]
print(solution(answers))