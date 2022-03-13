def solution(dartResult):
    dartResult = dartResult.replace("10", "x")
    square = {"S":1, "D":2, "T":3}
    score, score_list = 0, []
    
    for w in dartResult:
        # 숫자일때
        if w not in "SDT*#":
            score_list.append(score)
            if w == "x":
                w = "10"
            score = int(w)
        else:
            # 제곱일때
            if w in square:
                score = score ** square[w]
            # 옵션일때
            else :
                if w == "*":
                    score *= 2
                    score_list[-1] = score_list[-1]*2
                else:
                    score *= -1
    score_list.append(score)
    return sum(score_list)




dartResult = '1S2D*3T'
print(solution(dartResult))
dartResult = '1D2S#10S'
print(solution(dartResult))
dartResult = '1D2S0T'
print(solution(dartResult))
dartResult = '1S*2T*3S'
print(solution(dartResult))
dartResult = '1D#2S*3S'
print(solution(dartResult))
dartResult = '1T2D3D#'
print(solution(dartResult))
dartResult = '1D2S3T*'
print(solution(dartResult))
