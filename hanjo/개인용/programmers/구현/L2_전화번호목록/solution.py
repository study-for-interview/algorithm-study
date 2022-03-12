def solution2(phone_book):
    answer = False
    for i in phone_book:
        length = len(i)
        for j in phone_book:
            if i==j or len(j) <= length:
                continue
            sliced = j[0:length]
            if sliced == i:
                return answer
    answer = True
    return answer
# 효율성 1.50ms, 1.37ms


def solution(phone_book):
    for p1 in phone_book:
        for p2 in phone_book:
            if p1!=p2 and p1==p2[:len(p1)]:
                return False
    return True
# 효율성 1.26ms, 1.46ms


phone_book = ['119', '97674223', '1195524421']
print(solution(phone_book))
phone_book = ['123','456','789']
print(solution(phone_book))
phone_book = ['12','123','1235','567','88']
print(solution(phone_book))