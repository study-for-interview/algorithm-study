route_list = []
def DFS(vertex, tickets, route):
    # 경로를 복사 후 방문 정점을 저장.
    r_copy = route.copy()
    r_copy.append(vertex)
    # 빈 티켓이 온다면 더이상 갈곳이 없는것.
    # 전역 리스트에 지금까지의 경로를 저장한다.
    if not tickets:
        global route_list
        route_list.append(r_copy)
        return
    # 티켓을 순회해서 다음 정점을 정한다.
    for t in tickets:
        if t[0] == vertex:
            # 현재의 티켓 상태 복사 후 다음 티켓(간선)을 삭제.
            t_copy = tickets.copy()
            t_copy.remove(t)
            # 재귀호출. 깊이당 티켓이 점점 줄어듬.
            DFS(t[1], t_copy, r_copy)
            
def solution(tickets):
    # 깊이우선탐색 -> 현재정점/티켓(인접리스트와 비슷)/경로
    DFS("ICN", tickets, [])
    global route_list
    # 모든 경로들을 알파벳 순서로 정렬 후 가장 높은 리스트를 추출
    route_list.sort()
    answer = route_list[0]
    return answer

tickets = [
    ["ICN", "SFO"], ["ICN", "ATL"], 
    ["SFO", "ATL"], ["ATL", "ICN"], 
    ["ATL","SFO"]]

print(solution(tickets))