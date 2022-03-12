def isConnect(start, end, edge):
    edge_copy = edge.copy()
    for e in edge:
        if start in e:
            edge_copy.remove(e)
            # end를 찾게되면 연결 되어있는것.
            if end in e:
                return True
            # 재귀로 돌려서 True 등장시 리턴
            elif isConnect(e[1], end, edge_copy):
                return True
            elif isConnect(e[0], end, edge_copy):
                return True
    # 끝까지 안나오면 False
    return False

def solution(n, costs):
    # 비용을 기준으로 정렬
    costs.sort(key = lambda x : x[2])
    vertex = set([])    # 방문한 정점리스트
    edge=[]             # 연결된 간선리스트
    cost = 0
    for a,b,c in costs:
        # 둘다 이미 방문한 경우
        if a in vertex and b in vertex:
            # DFS로 a정점에서 b정점까지 연결되어있는지 확인한다.
            if isConnect(a, b, edge):
                continue
            # 분리되어있다면 해당 정점간선을 추가
            else:
                edge.append((a,b))
                cost += c
        # 둘 중 하나라도 방문하지 않은 경우
        else :
            vertex.add(a)
            vertex.add(b)
            edge.append((a,b))
            cost += c
    return cost

n = 4
costs = [[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]]
print(solution(n, costs))   # 4
n = 5
costs = [[0,1,5],[1,2,3],[2,3,3],[3,1,2],[3,0,4],[2,4,6],[4,0,7]]
print(solution(n, costs))   # 15
n = 5
costs = [[0,1,1],[3,4,1],[1,2,2],[2,3,4]]
print(solution(n, costs))   # 8
n = 5
costs = [[0,1,1],[0,2,2],[1,2,5],[1,3,3],[2,3,8],[3,4,1]]
print(solution(n, costs))   # 7
n=6
costs = [[0, 1, 5], [0, 3, 2], [0, 4, 3], [1, 4, 1], [3, 4, 10], [1, 2, 2], [2, 5, 3], [4, 5, 4]]
print(solution(n, costs))   # 11
n = 5
costs = [[0, 1, 1], [3, 1, 1], [0, 2, 2], [0, 3, 2], [0, 4, 100]]
print(solution(n, costs))   # 104
