package 구현.L3_베스트앨범;

import java.util.*;

public class Solution {
	
	public static int[] solution(String[] genres, int[] plays) {
		/* 구상한 구조
		 genre_dict = {
		 	classic : {
		 		idx : plays,
		 		...
		 	}
		 }
		 */
		
		// 모두 담을 해시맵 만들기
		Map<String, Map<Integer, Integer>> genreMap = new HashMap<>();
		for(int i=0; i<genres.length; i++) {
			// 이미 장르가 존재한다면
			if(genreMap.containsKey(genres[i])) {
				// 해당 장르 해시맵에 id - 재생횟수 저장
				genreMap.get(genres[i]).put(i, plays[i]);
			}
			// 장르가 없다면?
			else {
				// 장르와 해시맵을 새로 추가해줌
				Map<Integer, Integer> newMap = new HashMap<>();
				newMap.put(i, plays[i]);
				genreMap.put(genres[i], newMap);
			}
		}
		
		// 장르별 플레이 수 합계 구하기
		Map<String, Integer> genre_sum = new HashMap<>();
		for(String genre : genreMap.keySet()) {
			int sum = 0;
			for(int play : genreMap.get(genre).values()) {
				sum += play;
			}
			genre_sum.put(genre, sum);
		}
		
		// 플레이 수 기준 내림차순 정렬하기
		List<String> genreRank = new ArrayList<>(genre_sum.keySet());
		Collections.sort(genreRank, 
				(o1, o2) -> genre_sum.get(o2).compareTo(genre_sum.get(o1))
				);
		
		// id를 담을 리스트
		List<Integer> answerList = new ArrayList<>();
		
		// 플레이수가 많은 장르부터 담기
		for(String genre : genreRank) {
			// 장르별 해시맵 엔트리셋을 리스트에 담는다
			List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(genreMap.get(genre).entrySet());
			// 리스트를 id 기준으로 오름차순 정렬 (getKey) => 플레이 횟수가 같을 경우 작은 id가 먼저 와야됨
			Collections.sort(entries, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));
			// id 정렬 후 play 횟수 기준 내림차순 정렬 (getValue)
			Collections.sort(entries, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
			// 정렬된 리스트에서 앞 2개만 추출 후 정답 리스트에 담기
			int max = 2;
			if(entries.size() < 2) {
				max = entries.size();
			}
			for(int i=0; i<max; i++) {
				answerList.add(entries.get(i).getKey());
			}
		}
		
		// ArrayList를 배열로 전환
		int[] answer = new int[answerList.size()];
		for(int i=0; i<answerList.size(); i++) {
			answer[i] = answerList.get(i);
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		System.out.println(Arrays.toString(solution(genres, plays)));
		
	}

}
