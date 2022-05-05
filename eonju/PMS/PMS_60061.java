import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Solution {

    private static final int COL = 0;
    private static final int WID = 1;
    private static final int DELETE = 0;
    private static final int ADD = 1;

    private static List<FRAME> cols = new ArrayList<>();
    private static List<FRAME> wids = new ArrayList<>();

    public static int[][] solution(int n, int[][] build_frame) {
        for (int i = 0; i < build_frame.length; i++) {
            int[] frame = build_frame[i];
            int width = frame[0];
            int height = frame[1];
            int frameType = frame[2];
            int command = frame[3];

            if (frameType == COL) {
                if (command == ADD) {
                    if (addColIsOK(height, width)) {
                        cols.add(new FRAME(height, width, frameType));
                    }
                } else {
                    removeColIsOK(height, width);
                }
            } else {
                if (command == ADD) {
                    if (addWidIsOk(height, width)) {
                        wids.add(new FRAME(height, width, frameType));
                    }
                } else {
                    removeWidIsOK(height, width);
                }
            }
        }

        cols.addAll(wids);
        cols.sort(Comparator.comparing(FRAME::getWidth).thenComparing(FRAME::getHeight).thenComparing(FRAME::getType));

        int[][] answer = new int[cols.size()][3];

        for (int i = 0; i < cols.size(); i++) {
            answer[i] = new int[]{cols.get(i).width, cols.get(i).height, cols.get(i).type};
        }
        return answer;
    }

    public static boolean addColIsOK(int height, int width) {
        if (height == 0) {
            return true;
        }
        if (cols.contains(new FRAME(height - 1, width, COL))) {
            return true;
        }
        if (wids.contains(new FRAME(height, width - 1, WID))) {
            return true;
        }

        return false;
    }

    public static boolean addWidIsOk(int height, int width) {
        if (cols.contains(new FRAME(height - 1, width, COL)) || cols.contains(new FRAME(height - 1, width + 1, COL))) {
            return true;
        }

        if (wids.contains(new FRAME(height, width - 1, WID)) && wids.contains(new FRAME(height, width + 1, WID))) {
            return true;
        }

        return false;
    }

    public static void removeColIsOK(int height, int width) {
        cols.remove(new FRAME(height, width, COL));
        if (!isOK()) {
            cols.add(new FRAME(height, width, COL));
        }
    }

    public static void removeWidIsOK(int height, int width) {
        wids.remove(new FRAME(height, width, WID));
        if (!isOK()) {
            wids.add(new FRAME(height, width, WID));
        }
    }

    public static boolean isOK() {
        for (FRAME col : cols) {
            if (!addColIsOK(col.height, col.width)) {
                return false;
            }
        }

        for (FRAME wid : wids) {
            if (!addWidIsOk(wid.height, wid.width)) {
                return false;
            }
        }

        return true;
    }

    static class FRAME {

        private int height;
        private int width;
        private int type;

        public FRAME(int height, int width, int type) {
            this.height = height;
            this.width = width;
            this.type = type;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public int getType() {
            return type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            FRAME frame = (FRAME) o;
            return height == frame.height && width == frame.width && type == frame.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(height, width, type);
        }

    }
}
