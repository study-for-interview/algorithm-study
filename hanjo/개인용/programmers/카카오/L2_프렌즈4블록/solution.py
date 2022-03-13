import copy
def delete_row_col(m, n, b_copy):
    # 가로로 2개이상 연결안된 애들 삭제
    for i in range(m):
        sign = False
        for j in range(n):
            if b_copy[i][j] == "_":
                continue
            if j==n-1 or b_copy[i][j] != b_copy[i][j+1]:
                if sign==True:
                    sign = False
                else:
                    b_copy[i][j]="_"                
            else:
                sign = True
    # 세로로 2개이상 연결안된 애들 삭제
    for j in range(n):
        sign = False
        for i in range(m):
            if b_copy[i][j] == "_":
                continue
            if i==m-1 or b_copy[i][j] != b_copy[i+1][j]:
                if sign==True:
                    sign = False
                else:
                    b_copy[i][j]="_"                
            else:
                sign = True
    return b_copy

def modify(m, n, board):
    b_copy = copy.deepcopy(board)  
    b_copy = delete_row_col(m, n, b_copy)
    b_copy = delete_row_col(m, n, b_copy)
    # b_copy가 비어있다? --> 더이상 삭제할것이 없을 때 리턴
    if sum(b_copy, []).count("_") == m*n:
        return sum(board, []).count("_")
    # 삭제할것이 남아있다면 --> '_'의 좌표값을 모두 저장한다.
    index = [[] for _ in range(n)]
    for i in range(m):
        for j in range(n):
            if b_copy[i][j] == "_":
                index[j].append((i,j))
            else:
                b_copy[i][j] = "_"
    # 저장된 좌표값에 해당하는 board의 값을 순차적으로 아래서부터 쌓는다.
    for j in range(n):
        index[j].sort(reverse=True)
        for i in range(m-1, -1, -1):
            if not index[j]:
                break
            else:
                row = index[j][0][0]
                col = index[j][0][1]
                index[j].remove(index[j][0])
                b_copy[i][j] = board[row][col]
    return modify(m, n, b_copy)
    
def solution(m, n, board):
    # 리스트로 변환하기
    for i, b in enumerate(board):
        board[i] = list(b)
    answer = modify(m, n, board)
    return answer

m = 4
n = 5
board = ['CCBDE', 'AAADE', 'AAABF', 'CCBBF']
m2 = 6	
n2 = 6	
board2 = [
    'TTTANT', 
    'RRFACC', 
    'RRRFCC', 
    'TRRRAA', 
    'TTMMMF', 
    'TMMTTJ']

print(solution(m2, n2, board2))