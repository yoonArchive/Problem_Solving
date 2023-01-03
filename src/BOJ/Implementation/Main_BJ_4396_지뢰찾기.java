package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BJ_4396_지뢰찾기 {

    public static final char EMPTY = '.';
    public static final char MINE = '*';
    public static final char OPENED = 'x';

    public static int size;
    public static char[][] map, result;
    public static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    public static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        size = Integer.parseInt(br.readLine());
        map = new char[size][size];
        result = new char[size][size];
        for (int i = 0; i < size; i++) {
            map[i] = br.readLine().toCharArray();
        }
        char current;
        boolean loseStatus = false;
        for (int i = 0; i < size; i++) {
            String line = br.readLine();
            for (int j = 0; j < size; j++) {
                current = line.charAt(j);
                if (current == OPENED) {
                    if (map[i][j] == MINE) {
                        loseStatus = true;
                    } else {
                        result[i][j] = countMines(i, j);
                    }
                } else if (current == EMPTY) {
                    result[i][j] = EMPTY;
                }
            }
        }
        if (loseStatus) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (map[i][j] == MINE) {
                        result[i][j] = MINE;
                    }
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(result[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static char countMines(int r, int c) {
        int nr, nc, count = 0;
        for (int d = 0; d < 8; d++) {
            nr = r + dr[d];
            nc = c + dc[d];
            if (!isIn(nr, nc) || map[nr][nc] != MINE) {
                continue;
            }
            count++;
        }
        return (char) (count + '0');
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < size && nc < size;
    }
}
