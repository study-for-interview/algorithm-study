def solution2(participant, completion):
    participant.sort()
    completion.sort()
    
    for i in range(len(participant)):
        if i == len(participant)-1:
            answer = participant[i]
            return answer
        elif participant[i] != completion[i]:
            answer = participant[i]
            return answer

def solution(participant, completion):
    for p, c in zip(sorted(participant), sorted(completion)):
        if p != c :
            return p
   

participant = ['marina', 'josipa', 'nikola', 'vinko', 'filipa']
completion = ['josipa', 'filipa', 'marina', 'nikola']
print(solution(participant, completion))
