package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_9328_열쇠 {

    public static final char EMPTY = '.';
    public static final char WALL = '*';
    public static final char VISITED = '@';
    public static final char DOCUMENT = '$';
    public static final int SIZE = 26;

    public static int h, w, keys;
    public static char[][] map;
    public static int[][] deltas = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static class Space {
        int r;
        int c;

        public Space(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h + 2][w + 2];
            for (int i = 0; i < h + 2; i++) {
                if (i == 0 || i == h + 1) {
                    Arrays.fill(map[i], EMPTY);
                    continue;
                }
                String line = br.readLine();
                for (int j = 0; j < w + 2; j++) {
                    if (j == 0 || j == w + 1) {
                        map[i][j] = EMPTY;
                    } else {
                        map[i][j] = line.charAt(j - 1);
                    }
                }
            }
            keys = 0;
            String line = br.readLine();
            if (!line.equals("0")) {
                for (int i = 0; i < line.length(); i++) {
                    keys |= (1 << line.charAt(i) - 'a');
                }
            }
            sb.append(findDocuments()).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static int findDocuments() {
        Queue<Space> queue = new LinkedList<>();
        List<List<Space>> unOpenedDoors = new LinkedList<>();
        for (int i = 0; i < SIZE; i++) {
            unOpenedDoors.add(new LinkedList<>());
        }
        queue.offer(new Space(0, 0));
        map[0][0] = VISITED;
        int documents = 0;
        while (!queue.isEmpty()) {
            Space space = queue.poll();
            int r = space.r;
            int c = space.c;
            for (int d = 0; d < 4; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];
                if (!isIn(nr, nc) || map[nr][nc] == WALL || map[nr][nc] == VISITED) {
                    continue;
                }
                char current = map[nr][nc];
                if (current >= 'A' && current <= 'Z') { // 문 발견
                    if ((keys & 1 << (current - 'A')) == 0) { // 열쇠 없는 경우
                        unOpenedDoors.get(current - 'A').add(new Space(nr, nc)); // 저장 후 열쇠 발견 시 방문
                        continue;
                    }
                } else if (current >= 'a' && current <= 'z') { // 열쇠 발견
                    int key = current - 'a';
                    keys |= (1 << key);
                    for (Space unOpenedDoor : unOpenedDoors.get(key)) {
                        int tmpR = unOpenedDoor.r;
                        int tmpC = unOpenedDoor.c;
                        if (map[tmpR][tmpC] == key + 'A') {
                            map[tmpR][tmpC] = VISITED;
                            queue.offer(unOpenedDoor);
                        }
                    }
                } else if (current == DOCUMENT) { // 문서 발견
                    documents++;
                }
                map[nr][nc] = VISITED;
                queue.offer(new Space(nr, nc));
            }
        }
        return documents;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < h + 2 && c < w + 2;
    }
}
