def solution(msg):
    dic_list = [chr(i+65) for i in range(26)]
    index_list = []

    i =0
    # 문자열 한글자씩 순회
    while i!=len(msg):
        # 초기 문자 설정
        w = msg[i]
        # 사전에 일치하는 값 없을때까지 순회
        while 1:
            # 사전에 있다면? 그 문자의 인덱스 저장
            if w in dic_list:
                pre = dic_list.index(w)+1
            # 없다면? 새로운 문자열 사전에 저장 + 그 전 인덱스 추가
            else:
                index_list.append(pre)
                dic_list.append(w)
                break
            i += 1
            # 마지막 문자일때 -> 그 전 인덱스 추가하고 끝
            if i == len(msg):
                index_list.append(pre)
                break
            # 문자를 하나씩 붙이면서 반복
            w += msg[i]

    return index_list

msg = 'KAKAO'
print(solution(msg))
msg2 = 'TOBEORNOTTOBEORTOBEORNOT'
print(solution(msg2))