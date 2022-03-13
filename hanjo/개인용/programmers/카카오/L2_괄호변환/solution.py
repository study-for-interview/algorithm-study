def isBalance(u):
    return u.count("(") == u.count(")")

def isCorrect(w):
    cnt=0
    for x in w:
        if x=="(" :
            cnt+=1
        else:
            cnt-=1
        if(cnt < 0):
            return False 
    return True

def split_uv(w):
    # 1
    if not w :
        return ""
    u, v = "", ""
    # 2
    for i, x in enumerate(w):
        u = u+x
        if isBalance(u):
            v = w[i+1:]
            break
    # 3
    if isCorrect(u):
        v = split_uv(v)
        return u+v
    # 4
    else:
        r = "(" + split_uv(v) + ")"
        u = list(u[1:-1])
        # 뒤집기
        for i, x in enumerate(u):
            if x == "(":
                u[i] = ")"
            else:
                u[i] = "("
        u = ''.join(u)
        return r + u

def solution(p):
    answer = split_uv(p)
    return answer




p = "(()())()"
print(solution(p))

p = ")("
print(solution(p))

p = "()))((()"
print(solution(p))

p = ")()()()()()("
print(solution(p))

