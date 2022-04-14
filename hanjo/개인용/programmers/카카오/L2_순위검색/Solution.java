package 카카오.L2_순위검색;

import java.util.*;

class Solution {

    public String[] category;
    public int iScore;
    public Map<String, List<Integer>> comb;
    
    public int[] solution(String[] info, String[] query) {
        // 조합 구하기
        comb = new HashMap<>();
        for(String row : info){
            String[] splited = row.split(" ");
            category = Arrays.copyOfRange(splited, 0, 4);
            iScore = Integer.parseInt(splited[4]);
            dfs(0, "");
        }
        // 내림차순 정렬
        for(String key : comb.keySet()){
            Collections.sort(comb.get(key));
        }
        // 쿼리문에 해당하는 숫자 찾기
        List<Integer> answer = new ArrayList<>();
        for(String row : query){
            String[] splited = row.replace(" and ", " ").split(" ");
            String key = String.join("", Arrays.copyOfRange(splited, 0, 4));
            int qScore = Integer.parseInt(splited[4]);
            answer.add(countValid(key, qScore));
        }
        return answer.stream().mapToInt(v->v).toArray();
    }

    public void dfs(int depth, String str){
        if(depth==4){
            if(!comb.containsKey(str)){
                comb.put(str, new ArrayList<>());
            }
            comb.get(str).add(iScore);
            return;
        }
        
        dfs(depth+1, str+"-");
        dfs(depth+1, str+category[depth]);
    }

    public int countValid(String key, int qScore){
        if(!comb.containsKey(key)){
            return 0;
        }
        List<Integer> scoreList = comb.get(key);
        int size = scoreList.size();

        // upper bound
        int left = 0;
        int right = size;
        while(left < right){
            int mid = (left+right)/2;
            if(scoreList.get(mid) < qScore){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        return size - left;
    }

    public static void main(String[] args){
        // [1,1,1,1,2,4]
        System.out.println(Arrays.toString(new Solution().solution(
            new String[]{
                "java backend junior pizza 150",
                "python frontend senior chicken 210",
                "python frontend senior chicken 150",
                "cpp backend senior pizza 260",
                "java backend junior chicken 80",
                "python backend senior chicken 50"
            },
            new String[]{
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150"
            }
        )));
    }
}


/**
 * ================================================================================
 * 링크 : https://programmers.co.kr/learn/courses/30/lessons/72412
 * 날짜 : 220331
 * 성공여부 : 실패 (구글링)
 * 풀이시간 : 2h
 * 
 * 시간복잡도 : 
 * 효율성 테케 4 : (1022.94ms, 197MB)
 * ================================================================================
 * 
 * 구현 + dfs(조합) + 이분탐색 문제
 * 
 * 우선 문제를 읽어보면 바로 완전탐색하면 되겠는데? 라는 생각부터 든다
 * 근데 효율성이 걸려있음 -> 완탐 시간복잡도를 계산해봄 -> O(50,000*100,000)이므로 절대 안됨
 * 고로 탐색시간 O(n)을 최소 O(logn)으로 줄여야겠다고 생각하면 이분탐색이 떠올라야함
 * 
 * 그래서 일단 완전탐색(Try1)으로 구현해놓고 어디서 이분탐색할지를 살펴봤는데...
 * 전혀 감이오지 않아서 답을 봤다
 * 
 * - info 배열에 있는 한명의 조건이 어떤 쿼리에 걸릴수있는지 생각해봐야함
 *      -> 해당 인원이 검색될 수 있는 모든 쿼리 경우의 수(조합)를 구한다
 *      -> 특정 쿼리 케이스의 검색 결과(인원의 점수)를 리스트로 저장함
 * - 위 자료구조가 만들어지면 아래와 같은 흐름이 진행된다
 *      1. 쿼리를 순회하여 쿼리 결과 점수리스트 뽑음
 *      2. 점수리스트를 가지고 쿼리문 점수 조건 이상 count
 *          -> 여기서 이분탐색을 사용해야함
 * - 이분탐색시 쿼리문의 ~~점 이상인 점수의 수를 구하는 것이므로 총 사이즈에서 빼기위해 upperbound
 * 
 */