def to_piece(str_):
    # 문자열 대문자 -> 소문자
    str_ = str_.lower()
    piece = []
    for i in range(len(str_)-1):
        # 문자열을 두개씩 자른다.
        new_p = str_[i:i+2]
        # 특수문자가 포함된건 거른다.
        if new_p.isalpha() :
            piece.append(new_p)
    return piece

def solution(str1, str2):
    piece1 = to_piece(str1)
    piece2 = to_piece(str2)
    union = 0
    i_s = 0
    # 1과 2의 중복을 제거한 합집합 원소를 순회
    # 1과 2에서의 각 원소당 등장횟수를 구한다.
    # 합집합에는 등장횟수의 max값을, 교집합에는 min값을 더한다.
    for s in set(piece1) | set(piece2):
        union += max(piece1.count(s), piece2.count(s))
        i_s += min(piece1.count(s), piece2.count(s))
    # 자카드 유사도 계산
    # 교집합과 합집합이 둘다 공집합인 경우엔 유사도 = 1
    if i_s == 0 and union == 0:
        J = 1
    else :
        J = i_s / union
    answer = int(J * 65536)
    return answer

a = solution('FRANCE','french')
b = solution('handshake','shake hands')
c = solution('aa1+aa2','AAAA12')
d = solution('E=M*C^2','e=m*c^2')

print(a, 16384)
print(b, 65536)
print(c, 43690)
print(d, 65536)


# 시행착오 -----------------------------------
from collections import Counter
def to_Dic_cnt(str_):
    # 1. 문자열의 대문자를 소문자로 변경하기
    str_ = str_.lower()
    # 2. 문자열을 두개씩 끊어서 저장
    piece = []
    for i in range(len(str_)-1):
        new_p = str_[i:i+2]
        if new_p.isalpha() :
            piece.append(new_p)
    # 3. 각 조각 당 등장횟수를 담은 딕셔너리로 반환
    return Counter(piece)

def solution2(str1, str2):
    dic_cnt1 = to_Dic_cnt(str1)
    dic_cnt2 = to_Dic_cnt(str2)
    # 문자열 1, 2번의 모든 키
    key = set(dic_cnt1.keys()) | set(dic_cnt2.keys())
    union = 0
    i_s = 0
    for k in key:
        # 1번과 2번에 같이 존재할때 
        if k in dic_cnt1.keys() and dic_cnt2.keys():
            union += max(dic_cnt1[k], dic_cnt2[k])
            i_s += min(dic_cnt1[k], dic_cnt2[k])
        elif k in dic_cnt1.keys():
            union += dic_cnt1[k]
        elif k in dic_cnt2.keys():
            union += dic_cnt2[k]
    if union == i_s:
        J = 1
    else :
        J = i_s / union
    answer = int(J * 65536)
    return answer