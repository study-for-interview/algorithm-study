package 코테.라인_2022.문제3;

import java.util.*;

class Solution {

    public String[] remote_tasks;
    public String[] office_tasks;

    public static class Employee {

        public int id;
        public boolean isRemote;

        public Employee(int id, boolean isRemote) {
            this.id = id;
            this.isRemote = isRemote;
        }
    }

    public int[] solution(int num_teams, String[] remote_tasks, String[] office_tasks, String[] employees) {
        this.remote_tasks = remote_tasks;
        this.office_tasks = office_tasks;

        HashMap<Integer, ArrayList<Employee>> teams = new HashMap<>();
        for (int i = 1; i <= num_teams; i++) {
            teams.put(i, new ArrayList<>());
        }

        for (int i = 0; i < employees.length; i++) {
            String line = employees[i];
            // 파싱
            int teamNum = line.charAt(0) - '0';
            String[] tasks = line.substring(2).split(" ");
            boolean isRemote = isRemote(tasks);
            // 팀에 사원 추가
            teams.get(teamNum).add(new Employee(i + 1, isRemote));
        }

        ArrayList<Integer> result = new ArrayList<>();

        // 팀 단위로 순회
        for (int i = 1; i <= num_teams; i++) {
            ArrayList<Employee> members = teams.get(i);
            ArrayList<Integer> remote = new ArrayList<>();
            for (Employee member : members) {
                if (member.isRemote) {
                    remote.add(member.id);
                }
            }
            // 만약 모두 재택이면 사원번호 낮은 순서가 출근
            if (members.size() == remote.size()) {
                remote.remove(0);
            }
            result.addAll(remote);
        }

        return result.stream().mapToInt(v -> v).toArray();
    }

    public boolean isRemote(String[] tasks) {
        for (String task : tasks) {
            for (String office : office_tasks) {
                if (task.equals(office)) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        // [1,4,5,7]
        System.out.println(Arrays.toString(new Solution().solution(
            3,
            new String[]{"development", "marketing", "hometask"},
            new String[]{"recruitment", "education", "officetask"},
            new String[]{
                "1 development hometask",
                "1 recruitment marketing",
                "2 hometask",
                "2 development marketing hometask",
                "3 marketing",
                "3 officetask",
                "3 development"
            }
        )));
        // [3,4]
        System.out.println(Arrays.toString(new Solution().solution(
            2,
            new String[]{"design"},
            new String[]{"building", "supervise"},
            new String[]{
                "2 design",
                "1 supervise building design",
                "1 design", "2 design"
            }
        )));
    }
}