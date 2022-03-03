import java.util.*;
 
class Prgrms_보석쇼핑 {
    
    Set<String> set = new HashSet<>();
    Map<String, Integer> map = new HashMap<>();
    
    public int[] solution(String[] gems) {
        for(int i = 0; i < gems.length; i++) {
            set.add(gems[i]);
        }
        
        int start = 0;
        int tempStart = 0;
        int len = gems.length;
        Queue<String> q = new LinkedList<>();
        for(int i = 0; i < gems.length; i++) {
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
            
            q.add(gems[i]);
            while(true) {
                String gem = q.peek();
                if(map.get(gem) > 1) {
                    map.put(gem, map.get(gem) - 1);
                    q.poll();
                    tempStart++;
                } else break;
            }
            
            if(map.size() == set.size()) {
                if(len > q.size()) {
                    len = q.size();
                    start = tempStart;
                }
            }
        }
        return new int[] {start + 1, start + len};
    }
}
