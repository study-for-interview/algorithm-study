from collections import defaultdict
from operator import itemgetter
def solution(genres, plays):
    # value를 기본으로 []를 갖는 딕셔너리 생성
    dic_g = defaultdict(lambda : [])
    # 장르 : [노래이름, 재생횟수] 형태로 딕셔너리에 저장
    # dic_g = {'classic': [[0, 500], [2, 150], [3, 800]], 'pop': [[1, 600], [4, 2500]]}
    for i, z in enumerate(zip(genres, plays)):
        dic_g[z[0]].append([i, z[1]])

    dic_n = {}
    for v in dic_g.values():
        # itemgetter : 2차원 리스트를 정렬할때 사용 --> (lambda x: x[_]) 와 동일
        # sorted의 키값에 itemgetter(_)를 넣으면 원소 리스트들의 _열을 기준으로 정렬된다
        # 재생횟수를 내림차순으로 정렬
        v = sorted(v, key=itemgetter(1), reverse=True)
        # dic_n = {1450: [3, 0], 3100: [4, 1]}
        key = sum([ i[1] for i in v])   # 총 재생횟수
        value = [i[0] for i in v][0:2]  # 정렬된 이름을 2개로 자르기
        dic_n[key] = value

    # dic_n을 item리스트로 변환 > 총 재생횟수 기준 내림차순으로 정렬
    item = sorted(dic_n.items(), key=itemgetter(0), reverse=True)
    # 노래이름만 추출 후 2차원리스트 병합
    answer = sum([i[1] for i in item], [])
    return answer

genres = ['classic', 'pop', 'classic', 'classic', 'pop']	
plays = [500, 600, 150, 800, 2500]
a = solution(genres, plays)
print(a)