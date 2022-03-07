import datetime

def processing(time, timeline):
    out = 0
    start = time
    end = time + datetime.timedelta(seconds=1.0)
    
    for t1, t2 in timeline:
        if not(t1 >= end or t2 < start):
            out += 1
    return out
        
def solution(lines):
    answer = 0
    timeline = []
    for line in lines:
        date, time_str, T = line.split()
        S = date + ' ' + time_str
        T = float(T[:-1])
        
        end = datetime.datetime.strptime(S, '%Y-%m-%d %H:%M:%S.%f')
        start = end - datetime.timedelta(microseconds=(T - 0.001) * 1000000)
        timeline.append([start, end])
    
    for times in timeline:
        for time in times:
            throughput = processing(time, timeline)
            if throughput > answer:
                answer = throughput

    return answer


print(solution(
     ["2016-09-15 01:00:04.001 2.0s",
    "2016-09-15 01:00:07.000 2s"]
))