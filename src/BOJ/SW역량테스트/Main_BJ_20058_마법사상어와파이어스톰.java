package BOJ.SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_20058_마법사상어와파이어스톰 {
    static int[][] map;
    static int totalIce;
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int size = 1 << N; // 격자의 크기
        map = new int[size][size];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < size; j++) {
                int cur = Integer.parseInt(st.nextToken());
                map[i][j] = cur;
                totalIce += cur; // 초기 얼음의 총합
            }
        }

        Queue<int[]> q = new LinkedList<>();

        st = new StringTokenizer(br.readLine(), " ");
        while (Q-- > 0) {

            int L = Integer.parseInt(st.nextToken()); // 상어가 시전한 단계

            // step 1. 부분 격자를 시계 방향 회전
            int subSize = 1 << L; // 부분 격자의 크기
            for (int i = 0; i < size; i += subSize) {
                for (int j = 0; j < size; j += subSize) {
                    int tmpSize = subSize;
                    int diff = 0;
                    while (tmpSize >= 2) {
                        rotate(i + diff, j + diff, tmpSize);
                        tmpSize = tmpSize - 2;
                        diff++;
                    }
                }
            }

            // step 2. 얼음이 있는 칸 3개(또는 그 이상)와 인접해 있지 않은 칸 체크 후 얼음 양 줄이기
            check(size, q);

        }

        // step 3. 남아있는 얼음의 합 출력
        sb.append(totalIce).append("\n");

        // step 4. 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수 출력
        int maxCnt = 0;
        boolean isVisited[][] = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] > 0 && !isVisited[i][j])
                    maxCnt = Math.max(maxCnt, getBiggestArea(i, j, size, isVisited));
            }
        }
        sb.append(maxCnt).append("\n");
        System.out.println(sb.toString());
        br.close();
    }

    /**
     * 현재 영역을 시계 방향 90도 회전
     *
     * @param sr   맨 왼쪽 상단 행 번호
     * @param sc   맨 왼쪽 상단 열 번호
     * @param size 현재 영역의 크기
     */
    private static void rotate(int sr, int sc, int size) {
        // 1. 상단 행 저장 (가장자리 제외)
        int subSize = size - 2; // 양 끝 2개를 제외한 행/열의 크기
        int top[] = new int[subSize];
        int idx = 0;
        for (int i = sc + 1, length = sc + subSize; i <= length; i++)
            top[idx++] = map[sr][i];

        // 2. 왼쪽 -> 위
        int tmp = sc + size - 2;
        for (int r = sr + 1, length = sr + subSize; r <= length; r++)
            map[sr][tmp--] = map[r][sc];

        // 3. 아래 -> 왼쪽
        tmp = sr + 1;
        for (int c = sc + 1, length = sc + subSize; c <= length; c++)
            map[tmp++][sc] = map[sr + size - 1][c];

        // 4. 오른쪽 -> 아래
        tmp = sc + size - 2;
        for (int r = sr + 1, length = sr + subSize; r <= length; r++)
            map[sr + size - 1][tmp--] = map[r][sc + size - 1];

        // 5. 위 -> 오른쪽
        tmp = sr + 1;
        for (int i = 0; i < subSize; i++)
            map[tmp++][sc + size - 1] = top[i];

        // 가장자리 4개
        tmp = map[sr][sc]; // 맨 왼쪽 상단 끝
        map[sr][sc] = map[sr + size - 1][sc];
        map[sr + size - 1][sc] = map[sr + size - 1][sc + size - 1];
        map[sr + size - 1][sc + size - 1] = map[sr][sc + size - 1];
        map[sr][sc + size - 1] = tmp;

    }

    /**
     * 4방 탐색 하며 인접 칸에 얼음이 있는 칸이 3개보다 적다면 큐에 저장
     *
     * @param size 격자의 크기
     */
    private static void check(int size, Queue<int[]> q) {
        int count = 0;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                count = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr >= 0 && nc >= 0 && nr < size && nc < size && map[nr][nc] > 0)
                        count++;
                }
                if (count < 3)
                    q.offer(new int[]{r, c});
            }
        }
        // 해당하는 칸의 얼음 양 -1
        updateIce(q);
    }

    private static void updateIce(Queue<int[]> q) {
        while (!q.isEmpty()) {
            int[] pair = q.poll();
            int r = pair[0];
            int c = pair[1];
            if (map[r][c] <= 0)
                continue;
            map[r][c]--;
            totalIce--; // 총 얼음의 합도 같이 줄이기
        }

    }

    // BFS 탐색 통해 가장 큰 덩어리가 차지하는 칸의 개수 구하기
    private static int getBiggestArea(int sr, int sc, int size, boolean[][] isVisited) {
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{sr, sc});
        isVisited[sr][sc] = true;

        int maxCnt = 1;
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int r = pair[0];
            int c = pair[1];
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr >= 0 && nc >= 0 && nr < size && nc < size && map[nr][nc] > 0 && !isVisited[nr][nc]) {
                    queue.offer(new int[]{nr, nc});
                    isVisited[nr][nc] = true;
                    maxCnt++;
                }
            }
        }
        return maxCnt;
    }


//	private static void print(int size) {
//		System.out.println();
//		for(int i=0;i<size;i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
//	}

}
