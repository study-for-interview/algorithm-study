import re

def solution(new_id):
    # 1. 대문자 -> 소문자
    new_id = new_id.lower()

    # 2. 알파벳, 숫자, [-_.] 빼고 제거
    new_id = re.sub('[^a-z0-9-_.]', '', new_id)

    # 3. 마침표 2번이상 반복부분 마침표 하나로 치환
    new_id = re.sub('[.]{2,}', '.', new_id)

    # 4. 앞뒤 마침표 제거
    new_id = new_id.strip('.')

    # 5. 빈문자열일 경우 a 추가 
    if not new_id :
        new_id += 'a'

    # 6. 16자 이상일 경우 길이를 15까지 컷 / 만약 마지막이 마침표면 제거
    if len(new_id) >= 15:
        new_id = new_id[:15].rstrip('.')

    # 7. 2자 이하일 경우 마지막 문자를 길이 3까지 반복
    last = new_id[-1]
    while len(new_id) < 3:
        new_id += last

    return new_id



new_id = "...!@BaT#*..y.abcdefghijklm"
print(solution(new_id))

new_id = "z-+.^."	
print(solution(new_id))

new_id = "=.="
print(solution(new_id))

new_id = "123_.def"
print(solution(new_id))

new_id = "abcdefghijklmn.p"
print(solution(new_id))