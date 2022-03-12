def solution(routes):
    routes = sorted(routes, key= lambda x : x[0])
    # 가장 첫번째(왼이 가장 작은) 경로의 오른쪽값 저장
    ex_right = routes[0][1]
    # 시작 카메라는 한대.
    camera = 1
    for L, R in routes:
        # 1. 완전 겹쳐진 경우
        if R <= ex_right:
            ex_right = R
        # 2. 한쪽이 겹칠 경우
        if L <= ex_right < R:
            pass
        # 3. 아예 안겹칠 경우
        if ex_right < L :
            ex_right = R
            camera+=1
    return camera



print(solution([[-2,-1], [1,2],[-3,0]])) #2
print(solution([[0,0],])) #1
print(solution([[0,1], [0,1], [1,2]])) #1
print(solution([[0,1], [2,3], [4,5], [6,7]])) #4
print(solution([[-20,-15], [-14,-5], [-18,-13], [-5,-3]])) #2
print(solution([[-20,15], [-14,-5], [-18,-13], [-5,-3]])) #2
print(solution([[-20,15], [-20,-15], [-14,-5], [-18,-13], [-5,-3]])) #2

