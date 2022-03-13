package 구현.L2_전화번호목록;

import java.util.*;

class Solution {
    public static boolean solution(String[] phone_book) {
		Set<String> hs = new HashSet<>();
		
		for(String phoneNumber : phone_book) {
			hs.add(phoneNumber);
		}
		
		for(String phoneNumber : phone_book) {
			for(int i=0; i<phoneNumber.length(); i++) {
				String startNum = phoneNumber.substring(0, i);
				if(hs.contains(startNum)) {
					return false;
				}
			}
		}
		
		return true;
    }
    
	public static void main(String[] args) {
		String[] phone_book = {"119", "97674223", "1195524421"};
		System.out.println(solution(phone_book));
	}
}

