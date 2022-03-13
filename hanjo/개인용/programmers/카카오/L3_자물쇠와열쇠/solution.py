import copy   
def rotate(key, m):
    new = [[] for _ in range(m)]
    for j in range(m):
        for i in range(m-1, -1, -1):
            new[j].append(key[i][j])
    return new
    
'''    
def rotate(key):
    r_key = []
    for k in list(zip(*key)):
        r_key.append(list(reversed(k)))
    return r_key
'''

def solution(key, lock):
    m = len(key)
    n = len(lock)
    x = m+2*n
    # 확장된 행렬 main을 만들어준다.
    main = [[] for _ in range(x)]
    for ma in main:
        for _ in range(x):
                ma.append(0)
    for i in range(n):
        for j in range(n):
            main[n+i][n+j] = lock[i][j]
            
    for _ in range(4):
        row = 0
        col = 0
        while(1):
            m_copy = copy.deepcopy(main)
            # 1. 메인에 키를 겹쳐본다.
            for i in range(m):
                for j in range(m):
                    m_copy[row+i][col+j] += key[i][j]
            # 2. 맞춰본 후 중앙 lock 검사
            cnt=0
            for i in range(n):
                for j in range(n):
                    if m_copy[n+i][n+j] == 1:
                        cnt+=1
            # 3. 중앙 lock이 모두 1이라면? 키 존재.
            if cnt == n*n:
                return True
            # 4. 아니면 시작 포인트 이동.
            if col+m < x :
                col+=1
            else:
                col=0
                if row+m < x:
                    row+=1
                else:
                    break
        # 5. while문 끝나면 90도 돌려준다.
        key = rotate(key, m)
    return False

key = [[0, 0, 0], [1, 0, 0], [0, 1, 1]]	
lock = [[1, 1, 1], [1, 1, 0], [1, 0, 1]]
print(solution(key,lock))
