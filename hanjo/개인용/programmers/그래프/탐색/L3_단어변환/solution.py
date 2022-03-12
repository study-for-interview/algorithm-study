end_depth = []

def match(begin, word):
    dif=0
    L_begin = list(begin)
    L_words = list(word)
    for i in range(len(L_words)):
        if L_words[i] != L_begin[i]:
            dif += 1
    if dif == 1:
        return True
    else:
        return False

def DFS(begin, target, words, depth):
    global end_depth
    temp_words = words.copy()
    for i in range(len(words)):
        if match(begin, words[i]):
            if words[i] == target:
                end_depth.append(depth)
                return
            temp_words[i] =""
            DFS(words[i], target, temp_words, depth+1)

def solution(begin, target, words):
    answer = 0
    sign = False

    for i in range(len(words)):
        if words[i] == target:
            sign = True

    if sign == False:
        return answer
    
    DFS(begin, target, words, 1)
    answer = min(end_depth)
    return answer

begin = "hit"
target = "cog"
words = ["hot", "dot", "dog", "lot", "log", "cog"]
a = solution(begin, target, words)
print(a)

# 시행착오----------------------------------------------

def solution2(begin, target, words):
    answer = 0
    sign = False

    # words에 target이 없을때 거르기
    for i in range(len(words)):
        if words[i] == target:
            sign = True

    if sign == False:
        return answer

    answer = len(words)

    stack = []
    i = 0
    cnt =0
    while True:
        print(i)
        print(begin)
        print(words)
        print(stack)
        L_begin = list(begin)
        L_words = list(words[i])

        dif = 0
        for j in range(len(L_words)):
            if L_words[j] != L_begin[j]:
                dif += 1

        if dif == 1:    # 틀린 부분 
            if words[i] == target:
                if len(stack) < answer:
                    answer = len(stack)
                    
            before_begin = begin
            begin = words[i]
            stack.append(words[i])
            words.pop(i)
            i=0
            continue

        # 끝까지 갔는데 결과물 없을경우
        if i==len(words)-1:
            # 리얼 끝까지 갔을 때
            if begin == before_begin:
                for k in range():
                    if stack[k]==begin:
                        n = k
                    if k >= n :
                        words.append(stack[k])
                begin = stack[len(stack)-1]
            # 아직 기회가 있음
            begin = before_begin
            i=0
            continue
        i+=1
        cnt+=1

    return answer


begin = "hit"
target = "cog"
words = ["hot", "dot", "dog", "lot", "log", "cog"]
a = solution(begin, target, words)
print (a)