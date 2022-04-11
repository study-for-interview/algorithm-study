import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_21277 {
    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);

    static Puzzle puzzles[];

    public static class Puzzle {
        int n;
        int m;
        int puzzleBoard[][];
    }

    // 퍼즐을 만드는 메서드
    public static Puzzle makePuzzle() throws Exception{
        String nums = br.readLine();
        StringTokenizer stk = new StringTokenizer(nums);

        Puzzle puzzle = new Puzzle();

        puzzle.n = Integer.parseInt(stk.nextToken());
        puzzle.m = Integer.parseInt(stk.nextToken());
        puzzle.puzzleBoard = new int [puzzle.n+1][puzzle.m+1];

        for (int i = 1; i <= puzzle.n; i++) {
            String puzzleNums = br.readLine();

            for (int j = 1; j <= puzzleNums.length(); j++)
                puzzle.puzzleBoard[i][j] = Character.getNumericValue(puzzleNums.charAt(j-1));

        }

        return puzzle;
    }

    // 회전하는 메서드 퍼즐객체를 받아 puzzleBoard를 돌려 새로운 배열을 만들고 바꿔줍니다.
    public static void rotate(Puzzle puzzle) {
        int n = puzzle.m;
        int m = puzzle.n;

        int tmpPuzzleBoard[][] = new int[n + 1][m + 1];

        for (int i = 1; i <= puzzle.n; i++) {
            for (int j = 1; j <= puzzle.m; j++)
                tmpPuzzleBoard[puzzle.m + 1 - j][i] = puzzle.puzzleBoard[i][j];
        }

        puzzle.puzzleBoard = tmpPuzzleBoard;
        puzzle.n = n;
        puzzle.m = m;
    }

    // 액자를 구하는 메서드
    public static void makeFrame() {
        Puzzle basePuzzle;
        Puzzle cmpPuzzle;

        // 더 큰 퍼즐을 고정(base)로 합니다.
        if(puzzles[0].n + puzzles[0].m > puzzles[1].n + puzzles[1].m) {
            basePuzzle = puzzles[0];
            cmpPuzzle = puzzles[1];
        }
        else {
            basePuzzle = puzzles[1];
            cmpPuzzle = puzzles[0];
        }

        int size = 3000;

        // 회전할 수 있는 만큼 반복
        for (int i = 0; i < 4; i++) {
            rotate(cmpPuzzle);

            // (비교할 퍼즐 + 고정 퍼즐 + 비교할 퍼즐)로 두 퍼즐이 전부 들어갈만한 배열의 길이를 구합니다.
            int lengthN = basePuzzle.n + cmpPuzzle.n * 2;
            int lengthM = basePuzzle.m + cmpPuzzle.m * 2;

            Integer baseFrame[][] = new Integer[lengthN + 1][lengthM + 1];

            // 고정된 퍼즐로 N의 최대최소, M의 최대최소를 저장할 변수
            int baseMinN = 1000;
            int baseMaxN = 0;
            int baseMinM = 1000;
            int baseMaxM = 0;

            // 고정된 퍼즐을 액자에 위치시켜 주면서 퍼즐의 N의 최대최소, M의 최대최소를 구합니다.
            for (int j = 1; j <= basePuzzle.n; j++)
                for (int k = 1; k <= basePuzzle.m; k++) {
                    baseFrame[j+cmpPuzzle.n][k+cmpPuzzle.m] = basePuzzle.puzzleBoard[j][k];

                    baseMinN = Math.min(baseMinN, j+cmpPuzzle.n);
                    baseMaxN = Math.max(baseMaxN, j+cmpPuzzle.n);
                    baseMinM = Math.min(baseMinM, k+cmpPuzzle.m);
                    baseMaxM = Math.max(baseMaxM, k+cmpPuzzle.m);
                }

            // 비교 액자를 순회하기 위한 변수(비교 퍼즐이 한칸 씩 이동할 때 사용)
            int addN = 0;
            int addM = 0;

            while(true) {

                if(addM + cmpPuzzle.m > lengthM) {
                    addM = 0;
                    ++addN;
                }

                if(addN + cmpPuzzle.n > lengthN)
                    break;

                Integer cmpFrame[][] = new Integer[lengthN + 1][lengthM + 1];

                // 액자의 넓이를 구하기 위한 N의 최대최소, M의 최대최소 (고정 퍼즐로 초기화)
                int minN = baseMinN;
                int maxN = baseMaxN;
                int minM = baseMinM;
                int maxM = baseMaxM;

                // 비교액자에 비교퍼즐을 만들면서 N의 최대최소, M의 최대최소를 갱신합니다.
                for (int j = 1; j <= cmpPuzzle.n; j++)
                    for (int k = 1; k <= cmpPuzzle.m; k++) {
                        cmpFrame[j+addN][k + addM] = cmpPuzzle.puzzleBoard[j][k];

                        minN = Math.min(minN, j+addN);
                        maxN = Math.max(maxN, j+addN);
                        minM = Math.min(minM, k+addM);
                        maxM = Math.max(maxM, k+addM);
                    }

                // 겹치는지 체크
                boolean checkOverlap = false;

                for (int j = 1 + cmpPuzzle.n; j <= cmpPuzzle.n + basePuzzle.n; j++) {
                    for (int k = 1 + cmpPuzzle.m; k <= cmpPuzzle.m + basePuzzle.m; k++) {

                        // Integer로 액자를 초기화 했기 때문에 null과 비교, 액자끼리 비교해서 같은 인덱스의 값이 1로 같으면 겹치는 것
                        if(cmpFrame[j][k] != null && baseFrame[j][k] == 1 && cmpFrame[j][k] == 1){
                            checkOverlap = true;
                            break;
                        }
                    }
                    if(checkOverlap)
                        break;
                }
                ++addM;

                if(checkOverlap) {
                    continue;
                }

                int finalN = maxN - minN + 1;
                int finalM = maxM - minM + 1;

                // N과 M을 구해 액자의 최소넓이를 갱신
                size = Math.min(size, finalN * finalM);
            }
        }
        System.out.println(size);
    }

    public static void main(String[] args) {

        try {
            puzzles = new Puzzle[2];

            for (int i = 0; i < 2; i++)
                puzzles[i] = makePuzzle();

            makeFrame();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}