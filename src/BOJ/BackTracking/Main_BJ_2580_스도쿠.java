package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_2580_스도쿠 {

    static int[][] sudoku;
    static ArrayList<Pair> list;
    static int zeroCnt;
    static StringBuilder sb = new StringBuilder();

    static class Pair {
        int r, c;

        public Pair(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        sudoku = new int[9][9];
        list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int num = Integer.parseInt(st.nextToken());
                sudoku[i][j] = num;
                if (num == 0) {
                    zeroCnt++;
                    list.add(new Pair(i, j));
                }
            }
        }
        setNum(0);
    }

    private static void setNum(int count) {
        if (count == zeroCnt) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(sudoku[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
            System.exit(0);
        }

        int r = list.get(count).r;
        int c = list.get(count).c;
        for (int i = 1; i <= 9; i++) {
            sudoku[r][c] = i;
            if (isAvailable(r, c))
                setNum(count + 1);
        }
        sudoku[r][c] = 0;
    }

    private static boolean isAvailable(int r, int c) {
        int number = sudoku[r][c];
        for (int i = 0; i < 9; i++) {
            if ((i != r && sudoku[i][c] == number) || (i != c && sudoku[r][i] == number))
                return false;
        }
        int sr = r - (r % 3);
        int sc = c - (c % 3);
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (i == r && j == c)
                    continue;
                if (sudoku[i][j] == number)
                    return false;
            }
        }
        return true;
    }

}
