package 구현.L2_위장;

import java.util.*;
import static java.util.stream.Collectors.*;

class Solution {
	static public int solution(String[][] clothes) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for(String[] c : clothes)
		{
			String kind = c[1];
			if(!hm.containsKey(kind)) 
				hm.put(kind, 1);
			else
				hm.replace(kind,hm.get(kind)+1);
		}
		int answer = 1;
		for(Integer v : hm.values()) 
			answer = answer*(v+1);
		answer--;
		return answer;
	 }
	
    static public int solution2(String[][] clothes) {
		return Arrays.stream(clothes)
                .collect(
                		groupingBy(
                				e -> e[1],
                				mapping(e -> e[0], counting())
                				)
                		)
                .values()
                .stream()
                .collect(
                    reducing(1L, (x, y) -> x * (y + 1)))
                .intValue() - 1;
	 }

	static public void main(String[] args) {
		String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
		System.out.println(solution(clothes));
		String[][] clothes2 = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};
		System.out.println(solution(clothes2));
	}
}
