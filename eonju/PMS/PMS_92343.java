import java.util.ArrayList;
import java.util.List;

class Solution {
    public static int maxSheepCount;
    public static ArrayList<Integer>[] childs;

    public int solution(int[] info, int[][] edges) {
        childs = new ArrayList[info.length];

        for (int[] link : edges) {
            int parent = link[0];
            int child = link[1];

            if (childs[parent] == null) {
                childs[parent] = new ArrayList<>();
            }

            childs[parent].add(child);
        }

        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0);
        getAnimal(0, 0, 0, nextNodes, info);

        return maxSheepCount;
    }

    public void getAnimal(int sheepCount, int wolfCount, int node, List nextNodes, int[] info) {
        sheepCount += info[node] ^ 1;
        wolfCount += info[node];
        maxSheepCount = Math.max(maxSheepCount, sheepCount);

        if (sheepCount <= wolfCount) {
            return;
        }

        List<Integer> copied = new ArrayList<>();
        copied.addAll(nextNodes);
        // 현재 방문한 노드에서 갈 수 있는 자식 노드가 있다면 추가해준다.
        if (childs[node] != null) {
            copied.addAll(childs[node]);
        }
        // 현재 방문한 노드를 다음에 방문할 목록에서 삭제한다.
        copied.remove(Integer.valueOf(node));

        for (int nextNode : copied) { // 다음에 방문할 노드
            getAnimal(sheepCount, wolfCount, nextNode, copied, info);
        }
    }
}
