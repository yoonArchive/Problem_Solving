package BOJ.Graph;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_1938_통나무옮기기 {

    public static final char TREE = 'B';
    public static final char MOVED_TREE = 'E';
    public static final char UNCUT_TREE = '1';
    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = 0;

    public static int N;
    public static char[][] area;
    public static Tree movedTree;

    public static class Tree {
        int centerR;
        int centerC;
        int placedStatus;
        int count;

        public Tree(int centerR, int centerC, int placedStatus, int count) {
            this.centerR = centerR;
            this.centerC = centerC;
            this.placedStatus = placedStatus;
            this.count = count;
        }

        public boolean equals(Object o) {
            Tree tree = (Tree) o;
            return centerR == tree.centerR && centerC == tree.centerC && placedStatus == tree.placedStatus;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        area = new char[N][N];
        for (int i = 0; i < N; i++) {
            area[i] = br.readLine().toCharArray();
        }
        Queue<Tree> queue = new LinkedList<>();
        boolean[][][] isPlaced = new boolean[N][N][2]; // 0 : 가로, 1 : 세로
        boolean findMovedTree = false, findTree = false;
        outer:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!findTree && area[i][j] == TREE) {
                    if (isIn(i + 1, j) && area[i + 1][j] == TREE) {
                        queue.offer(new Tree(i + 1, j, VERTICAL, 0));
                        isPlaced[i + 1][j][VERTICAL] = true;
                    } else if (isIn(i, j + 1) && area[i][j + 1] == TREE) {
                        queue.offer(new Tree(i, j + 1, HORIZONTAL, 0));
                        isPlaced[i][j + 1][HORIZONTAL] = true;
                    }
                    findTree = true;
                } else if (!findMovedTree && area[i][j] == MOVED_TREE) {
                    if (isIn(i + 1, j) && area[i + 1][j] == MOVED_TREE) {
                        movedTree = new Tree(i + 1, j, VERTICAL, 0);
                    } else if (isIn(i, j + 1) && area[i][j + 1] == MOVED_TREE) {
                        movedTree = new Tree(i, j + 1, HORIZONTAL, 0);
                    }
                    findMovedTree = true;
                }
                if (findTree && findMovedTree) {
                    break outer;
                }
            }
        }
        bw.write(Integer.toString(moveTree(queue, isPlaced)));
        br.close();
        bw.flush();
        bw.close();
    }

    private static int moveTree(Queue<Tree> queue, boolean[][][] isPlaced) {
        while (!queue.isEmpty()) {
            Tree tree = queue.poll();
            int centerR = tree.centerR;
            int centerC = tree.centerC;
            int placedStatus = tree.placedStatus;
            int count = tree.count;
            if (tree.equals(movedTree)) {
                return count;
            }
            for (int type = 0; type < 5; type++) {
                move(queue, isPlaced, type, centerR, centerC, placedStatus, count);
            }
        }
        return 0;
    }

    private static void move(Queue<Tree> queue, boolean[][][] isPlaced, int type, int centerR, int centerC, int placedStatus, int count) {
        int nCenterR = centerR, nCenterC = centerC, nPlacedStatus = placedStatus;
        if (type == 0) { // 위로 이동
            nCenterR--;
        } else if (type == 1) { // 오른쪽으로 이동
            nCenterC++;
        } else if (type == 2) { // 아래로 이동
            nCenterR++;
        } else if (type == 3) { // 왼쪽으로 이동
            nCenterC--;
        } else { // 90도 회전
            nPlacedStatus = placedStatus == VERTICAL ? HORIZONTAL : VERTICAL;
        }
        if (canMove(type, nPlacedStatus, nCenterR, nCenterC) && !isPlaced[nCenterR][nCenterC][nPlacedStatus]) {
            queue.offer(new Tree(nCenterR, nCenterC, nPlacedStatus, count + 1));
            isPlaced[nCenterR][nCenterC][nPlacedStatus] = true;
        }
    }

    private static boolean canMove(int type, int placedStatus, int r, int c) {
        if (type == 0) {
            if (placedStatus == 0) {
                for (int i = c - 1; i <= c + 1; i++) {
                    if (!isIn(r, i) || area[r][i] == UNCUT_TREE) {
                        return false;
                    }
                }
            } else {
                if (!isIn(r - 1, c) || area[r - 1][c] == UNCUT_TREE) {
                    return false;
                }
            }
        } else if (type == 1) {
            if (placedStatus == 0) {
                if (!isIn(r, c + 1) || area[r][c + 1] == UNCUT_TREE) {
                    return false;
                }
            } else {
                for (int i = r - 1; i <= r + 1; i++) {
                    if (!isIn(i, c) || area[i][c] == UNCUT_TREE) {
                        return false;
                    }
                }
            }
        } else if (type == 2) {
            if (placedStatus == 0) {
                for (int i = c - 1; i <= c + 1; i++) {
                    if (!isIn(r, i) || area[r][i] == UNCUT_TREE) {
                        return false;
                    }
                }
            } else {
                if (!isIn(r + 1, c) || area[r + 1][c] == UNCUT_TREE) {
                    return false;
                }
            }
        } else if (type == 3) {
            if (placedStatus == 0) {
                if (!isIn(r, c - 1) || area[r][c - 1] == UNCUT_TREE) {
                    return false;
                }
            } else {
                for (int i = r - 1; i <= r + 1; i++) {
                    if (!isIn(i, c) || area[i][c] == UNCUT_TREE) {
                        return false;
                    }
                }
            }
        } else {
            for (int i = r - 1; i <= r + 1; i++) {
                for (int j = c - 1; j <= c + 1; j++) {
                    if (!isIn(i, j) || area[i][j] == UNCUT_TREE) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
