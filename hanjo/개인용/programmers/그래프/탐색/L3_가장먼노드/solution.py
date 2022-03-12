from collections import deque
def solution(n, edge):
    # 정점의 인접 정점 리스트
    # a_vertex = [[3, 2], [3, 1, 4, 5], [6, 4, 2, 1], [3, 2], [2], [3]]
    a_vertex =[[] for _ in range(n)]
    for v1, v2 in edge:
        a_vertex[v1-1].append(v2)
        a_vertex[v2-1].append(v1)
    # 정점 1로부터의 거리와 방문여부를 저장하는 딕셔너리
    dic_dis = {1:0}
    # 방문할 순서를 저장하는 큐
    queue = deque([1])
    # 그래프 순회(BFS)
    # 1. 큐에서 정점을 꺼내고 그 정점의 인접 정점을 방문
    # 2. 첫 방문시 그 정점을 딕셔너리에 저장 + 큐에다가 추가
    # 3. 만약 딕셔너리 key값이 이미 존재하면 재방문임.
    while queue:
        q = queue.popleft()
        for v in a_vertex[q-1]:
            if v not in dic_dis.keys():
                dic_dis[v]=dic_dis[q]+1
                queue.append(v)
    # 이렇게 저장된 딕셔너리의 값(거리)중 가장 큰값 찾기
    dv = list(dic_dis.values())
    answer = dv.count(max(dv))
    return answer

n = 6
edge = [[3, 6], [4, 3], [3, 2], 
[1, 3], [1, 2], [2, 4], [5, 2]]
a = solution(n, edge)
print(a)



# DFS/효율성 초과 ----------------------------------------
end_dic = {1:0}

def DFS(start, depth, edge):
    global end_dic
    temp_edge = edge.copy()
    for e in edge:
        if e[0] == start:
            temp_edge.remove(e)
            if e[1] not in end_dic:
                end_dic[e[1]] = depth
            elif end_dic[e[1]] > depth:
                end_dic[e[1]] = depth
            DFS(e[1], depth+1, temp_edge)
        elif e[1] == start:
            temp_edge.remove(e)
            if e[0] not in end_dic:
                end_dic[e[0]] = depth
            elif end_dic[e[0]] > depth:
                end_dic[e[0]] = depth
            DFS(e[0], depth+1, temp_edge)

def solution1(n, edge):
    DFS(1, 1, edge)
    global end_dic
    max_depth = max(end_dic.values())
    answer = 0
    for v in end_dic.values():
        if v == max_depth :
            answer+=1
    return answer

n = 6
edge = [[3, 6], [4, 3], [3, 2], 
[1, 3], [1, 2], [2, 4], [5, 2]]
print(solution(n, edge))


# 시행착오 ----------------------------------------

from collections import deque
def solution2(n, edge):
    dic_v = {}
    for i in range(1, n+1):
        dic_v[i] = []
        for e in edge:
            if i in e:
                dic_v[i].append(e)
    dic_dis = {1:0}
    queue = deque([1])

    while queue:
        q = queue.popleft()
        dis = dic_dis[q]
        vertex = dic_v[q]
        for v in vertex:
            #print(q, v, dic_dis)
            next_v = None
            if v[0] == q:
                next_v = v[1]
            elif v[1] == q:
                next_v = v[0]
            if next_v != None and (next_v not in dic_dis.keys()):
                dic_v[next_v].remove(v)
                dic_dis[next_v]=dis+1
                queue.append(next_v)

    dv = list(dic_dis.values())
    answer = dv.count(max(dv))
    return answer