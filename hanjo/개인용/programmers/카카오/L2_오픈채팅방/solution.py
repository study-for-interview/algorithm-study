def solution(record):
    answer = []
    uid_nickname = {}

    for i in range(len(record)):
        splited = record[i].split(" ")
        command = splited[0]
        if command != "Leave":
            uid = splited[1]
            nickname = splited[2]
            uid_nickname[uid] = nickname

    for i in range(len(record)):
        splited = record[i].split(" ")
        command = splited[0]
        uid = splited[1]
        if command == "Enter":
            answer.append(F"{uid_nickname[uid]}님이 들어왔습니다.")
        elif command == "Leave":
            answer.append(F"{uid_nickname[uid]}님이 나갔습니다.")
    return answer

record = ["Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"]
print(solution(record))