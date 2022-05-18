# Memo
> 코딩하다 새롭게 알게되는 지식 메모

<br>

## Arrays.sort vs Collections.sort

### Arrays.sort

- 정렬방식 : DualPivotQuicksort
- 시간복잡도 : 평균 - `O(n*log(n))` / 최악 - `O(n^2)`

### Collections.sort

- 정렬방식 : TimeSort (삽입 + 합병)
- 시간복잡도 : `O(n*log(n))`

<br>

## 비트연산

- `&` : 대응되는 비트가 모두 1이면 1을 반환함. (비트 AND 연산)
- `|` : 대응되는 비트 중에서 하나라도 1이면 1을 반환함. (비트 OR 연산)
- `^` : 대응되는 비트가 서로 다르면 1을 반환함. (비트 XOR 연산)
- `~` : 비트를 1이면 0으로, 0이면 1로 반전시킴. (비트 NOT 연산)

<br>