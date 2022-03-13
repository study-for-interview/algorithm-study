# https://brownbears.tistory.com/468 진법 참고..

def numeral_system(number, base):
    notation = '0123456789ABCDEF'
    q, r = divmod(number, base)
    n = notation[r]
    return numeral_system(q, base) + n if q else n

def solution(n, t, m, p):
    result = ''
    i = cnt = 0
    while t != len(result):
        st = numeral_system(i,n)
        for s in st:
            if p == cnt%m+1:
                result += s
                if t==len(result):
                    break
            cnt+=1
        i+=1
    return result


print(solution(2, 4, 2, 1))
print(solution(8, 4, 2, 1))
print(solution(16, 16, 2, 1))
print(solution(16, 16, 2, 2))


# 2 8 10 16 진법만 생각해서 틀린 솔루션 
def solution2(n, t, m, p):
    i = 0
    cnt = 0
    result = ''
    while t != len(result):
        if n==2:
            st = bin(i)[2:]
        elif n==8:
            st = oct(i)[2:].upper()
        elif n==10:
            st = str(i)
        elif n==16:
            st = hex(i)[2:].upper()

        for s in st:
            if p == cnt%m+1:
                result += s
                if t==len(result):
                    break
            cnt+=1
        i+=1
    return result
