package 카카오.L3_불량사용자;

import java.util.*;

public class Solution {

    public static ArrayList<ArrayList<String>> candidates;
    public static  HashSet<HashSet<String>> combinations;

    public static int solution(String[] user_id, String[] banned_id) {

        // 후보 찾기
        candidates = new ArrayList<>();
        for(int i=0; i<banned_id.length; i++){

            String bannedId = banned_id[i];
            candidates.add(new ArrayList<>());

            for(String userId : user_id){
                if(isMatch(userId, bannedId)){
                    candidates.get(i).add(userId);
                }
            }
        }

        // 후보군으로 모든 조합 찾기
        combinations = new HashSet<>();
        reculsive(0, new HashSet<String>());

        return combinations.size();
    }

    public static boolean isMatch(String userId, String bannedId){
        if(userId.length() != bannedId.length()){
            return false;
        }
        for(int j=0; j<userId.length(); j++){
            if(bannedId.charAt(j) != userId.charAt(j) && bannedId.charAt(j) != '*'){
                return false;
            }
        }
        return true;
    }

    public static void reculsive(int depth, HashSet<String> bucket){
        if(depth == candidates.size()){
            if(bucket.size() == candidates.size()){
                combinations.add(bucket);
            }
            return;
        }

        for(String candidate : candidates.get(depth)){
            HashSet<String> newBucket = new HashSet<>();
            newBucket.addAll(bucket);
            newBucket.add(candidate);
            reculsive(depth+1, newBucket);
        }
    }

    public static void main(String[] args){
        // 2
        System.out.println(solution(
            new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
            new String[]{"fr*d*", "abc1**"}
        ));
        //2
        System.out.println(solution(
            new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
            new String[]{"*rodo", "*rodo", "******"}
        ));
        // 3
        System.out.println(solution(
            new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
            new String[]{"fr*d*", "*rodo", "******", "******"}
        ));
    }
}

/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/64064
 * 성공여부 : 실패
 * 풀이시간 : 2H
 * 
 * 시간복잡도 : ?
 * 테케5 : 3915.56ms, 383MB
 * ================================================================================
 * 
 * 문자열 파싱 + 완전탐색(dfs) 문제
 * HashSet은 내부 컬렉션 원소까지 모두 비교해서 중복을 제거한다는 사실을 알게됨
 * 
 */