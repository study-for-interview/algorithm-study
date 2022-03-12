def solution(operations):
    answer = []
    que = []
    for op in operations:
        splited = op.split(" ")
        c = splited[0]
        s = splited[1]
        if c == 'I':
            que.append(int(s))
        else:
            if s == '1':
                # 중복 최대값중 하나만 삭제하기
                for q in que:
                    if q == max(que):
                        que.remove(q)
                        break
            else:
                for q in que:
                    if q == min(que):
                        que.remove(q)
                        break
    if not que: # 큐가 비어있다면
        answer = [0,0]
    else:
        answer = [max(que), min(que)]
    return answer

operations = ["I 7","I 5","I -5","D -1"]
operations2 = ['I 16','D 1']
operations3 = ['I 1', 'I 2', 'I 3', 'I 4', 'I 5', 'I 6', 'I 7', 'I 8', 
'I 9', 'I 10', 'D 1', 'D -1', 'D 1', 'D -1', 'I 1', 'I 2', 'I 3', 'I 4', 
'I 5', 'I 6', 'I 7', 'I 8', 'I 9', 'I 10', 'D 1', 'D -1', 'D 1', 'D -1']

a = solution(operations3)
print(a)