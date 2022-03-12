import math
def solution(brown, yellow):
    grid_num = brown + yellow
    for x in range(math.ceil(math.sqrt(grid_num)), grid_num-1, 1):
        y, remain = divmod(grid_num, x)
        if remain==0 and (x+(y-2))*2 == brown:
            return [x,y]
            

brown = 10
yellow = 2
print(solution(brown, yellow))

brown = 8
yellow = 1
print(solution(brown, yellow))

brown = 24
yellow = 24
print(solution(brown, yellow))