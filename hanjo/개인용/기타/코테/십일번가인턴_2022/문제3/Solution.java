package 코테.십일번가인턴_2022.문제3;

import java.util.*;

class Solution {

    public static class File implements Comparable<File> {
        public int idx;
        public String ext;
        public String city;
        public String time;

        public File(int idx, String ext, String city, String time) {
            this.idx = idx;
            this.ext = ext;
            this.city = city;
            this.time = time;
        }

        @Override
        public int compareTo(File o) {
            return this.time.compareTo(o.time);
        }
    }

    public String solution(String S) {
        String[] fileNames = S.split("\n");
        int n = fileNames.length;

        // 초기화
        List<File> files = new ArrayList<>();
        Map<String, List<File>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            // 파싱 -> 객체 생성
            String[] splited = fileNames[i].split(", ");
            String ext = splited[0].split("\\.")[1];
            String city = splited[1];
            String time = splited[2];
            File file = new File(i, ext, city, time);
            // 리스트 추가
            files.add(file);
            // 맵 추가
            if (!map.containsKey(city)) {
                map.put(city, new ArrayList<>());
            }
            map.get(city).add(file);
        }

        // map value 정렬
        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        // 리스트 순회하며 파일명 변경
        StringBuilder sb = new StringBuilder();
        for (File file : files) {
            List<File> list = map.get(file.city);
            int size = list.size();

            StringBuilder num = new StringBuilder();
            for (int i = 0; i < size; i++) {
                File temp = list.get(i);
                if (file.idx == temp.idx) {
                    num.append(i + 1);
                }
            }

            int len = String.valueOf(size).length();
            while (num.length() != len) {
                num.insert(0, "0");
            }
            sb.append(file.city).append(num).append(".").append(file.ext).append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        //
        System.out.println(new Solution().solution(
            "photo.jpg, Warsaw, 2013-09-05 14:08:15\njohn.png, London, 2015-06-20 15:13:22\nmyFriends.png, Warsaw, 2013-09-05 14:07:13\nEiffel.jpg, Paris, 2015-07-23 08:03:02\npisatower.jpg, Paris, 2015-07-22 23:59:59\nBOB.jpg, London, 2015-08-05 00:02:03\nnotredame.png, Paris, 2015-09-01 12:00:00\nme.jpg, Warsaw, 2013-09-06 15:40:22\na.png, Warsaw, 2016-02-13 13:33:50\nb.jpg, Warsaw, 2016-01-02 15:12:22\nc.jpg, Warsaw, 2016-01-02 14:34:30\nd.jpg, Warsaw, 2016-01-02 15:15:01\ne.png, Warsaw, 2016-01-02 09:49:09\nf.png, Warsaw, 2016-01-02 10:55:32\ng.jpg, Warsaw, 2016-02-29 22:13:11"
        ));
    }
}
