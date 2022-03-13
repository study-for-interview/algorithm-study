from collections import Counter
def solution(s):
    # {, } 싹다 제거
    s = s.replace('{', '')
    s = s.replace('}', '')
    # , 로 나눠서 리스트에 저장
    s_list = s.split(',')
    # int로 형 변환하기
    s_list = [int (i) for i in s_list]

    # 그 리스트에서 숫자별 등장횟수 딕셔너리를 만든다 > counter 함수 사용
    cnt = Counter(s_list)
    
    # 딕셔너리를 등장횟수 기준으로 내림차순 정렬한다.
    # sorted(list, key, reverse) 
    # cnt.items() = [(2, 4), (1, 3), (3, 2), (4, 1)] 이렇게 되므로
    # lambda x: x[1] --> x = (2,4) / (1,3) .... 이 된다.
    # 따라서 x[1]은 value값이 되고 value를 기준으로 정렬한다는 뜻.
    s_cnt = sorted(cnt.items(), key=(lambda x: x[1]), reverse=True)
    answer = []
    for i in s_cnt:
        answer.append(i[0])
    return answer

s = "{{2},{2,1},{2,1,3},{2,1,3,4}}"
print(solution(s), [2, 1, 3, 4])

s1 = "{{20,111},{111}}"
print(solution(s1), [111, 20])