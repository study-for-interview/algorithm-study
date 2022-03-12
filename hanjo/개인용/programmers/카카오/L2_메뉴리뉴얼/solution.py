
# def solution(orders, course):
#     order_dict = {order : 0 for order in orders}
#     for i in orders:
#         for j in orders:
#             flag = 1
#             for menu in i:
#                 if menu not in j:
#                     flag = 0
#                     break
#             if flag:
#                 order_dict[i] += 1
#     print(order_dict)
#     answer = []
#     for num in course:
#         num_item = []
#         max = 0
#         for k, v in order_dict.items():
#             if len(k) == num and len(k) != 1:
#                 if max < v:
#                     max = v
#                 num_item.append([k,v])
#         for k, v in num_item:
#             if v == max:
#                 answer.append(k)
#     return sorted(answer)


# from collections import Counter
# from itertools import combinations
# def solution(orders, course):

#     combi_list = []
#     for i in course:
#         for combi in combinations(sorted(list(set(''.join(orders)))), i):
#             combi_list.append(combi)
#         # combi_list.append([i, combinations(sorted(list(set(''.join(orders)))), i)])

#     combi_count = {}
#     for combi in combi_list:
#         #
#         temp_dict = {}  # { word : order_list }
#         for word in combi:
#             temp_dict[word] = []
#             for order in orders:
#                 if word in order:
#                     temp_dict[word].append(order)
#         # 해당 단어가 등장했던 주문의 수를 센다.
#         order_count = [[order, 0] for order in orders]  # {order : count}
#         for v_list in temp_dict.values():
#             for v in v_list:
#                 for i in range(len(order_count)):
#                     if v == order_count[i][0]:
#                         order_count[i][1] += 1
#         print(combi, order_count)
#         #
#         count = 0
#         for i in range(len(order_count)):
#             if order_count[i][1] == len(combi):
#                 count += 1
#         if count >= 2:
#             combi_count[''.join(combi)] = count

#     # for i in course:
#     #     if i == len(combi_count.keys()):
            
#     print(combi_count)
#     return


from collections import defaultdict
from itertools import combinations
def solution(orders, course):
    combi_dict = defaultdict(int)
    for order in orders:
        for num in course:
            for combi in combinations(sorted(order), num):
                combi_dict[''.join(combi)] += 1
    num_dict = defaultdict(list)
    answer = []
    for num in course:
        max_count = 0
        for combi, count in combi_dict.items():
            if num == len(combi) and count != 1: 
                if max_count < count:
                    max_count = count
                num_dict[num].append((combi,count)) 
        for combi, count in num_dict[num]:
            if count == max_count:
                answer.append(combi)
    return sorted(answer)
    


orders = ["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"]
course = [2,3,4]
print(solution(orders, course))

orders = ["ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"]
course = [2,3,5]
print(solution(orders, course))

print(solution(["XYZ", "XWY", "WXA"], [2,3,4]))