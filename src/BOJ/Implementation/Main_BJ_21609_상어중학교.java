package BOJ.Implementation;

import java.io.*;
import java.util.*;

public class Main_BJ_21609_상어중학교 {

    public static final int BLACK = -1;
    public static final int RAINBOW = 0;
    public static final int EMPTY = -2;

    public static BlockGroup biggestBlockGroup;
    public static int N, M, score;
    public static int[][] board, rotated;
    public static int[][] deltas = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public static class Block {
        int r;
        int c;

        public Block(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Block block = (Block) o;
            return r == block.r && c == block.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    public static class BlockGroup {
        Block starndardBlock;
        Set<Block> blockSet = new HashSet<>();
        int rainbowCount;

        public BlockGroup(Block block, int rainbowCount) {
            this.starndardBlock = block;
            this.blockSet.add(block);
            this.rainbowCount = rainbowCount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        rotated = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (true) {
            biggestBlockGroup = new BlockGroup(new Block(0, 0), 0);
            findBiggestBlockGroup();
            if (biggestBlockGroup.blockSet.size() == 1) {
                break;
            }
            removeBiggestBlockGroup();
            workGravity();
            rotate();
            workGravity();
        }
        bw.write(Integer.toString(score));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void findBiggestBlockGroup() {
        boolean[][] isVisited = new boolean[N][N];
        Queue<Block> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isVisited[i][j] || board[i][j] == RAINBOW || board[i][j] == BLACK || board[i][j] == EMPTY) {
                    continue;
                }
                BlockGroup newBlockGroup = getGroup(i, j, queue, isVisited);
                if (biggestBlockGroup.blockSet.size() == newBlockGroup.blockSet.size()) {
                    if (biggestBlockGroup.rainbowCount == newBlockGroup.rainbowCount) {
                        if (biggestBlockGroup.starndardBlock.r == newBlockGroup.starndardBlock.r) {
                            if (biggestBlockGroup.starndardBlock.c < newBlockGroup.starndardBlock.c) {
                                biggestBlockGroup = newBlockGroup;
                            }
                        } else if (biggestBlockGroup.starndardBlock.r < newBlockGroup.starndardBlock.r) {
                            biggestBlockGroup = newBlockGroup;
                        }
                    } else if (biggestBlockGroup.rainbowCount < newBlockGroup.rainbowCount) {
                        biggestBlockGroup = newBlockGroup;
                    }
                } else if (biggestBlockGroup.blockSet.size() < newBlockGroup.blockSet.size()) {
                    biggestBlockGroup = newBlockGroup;
                }
            }
        }
    }

    private static BlockGroup getGroup(int startR, int startC, Queue<Block> queue, boolean[][] isVisited) {
        Block startBlock = new Block(startR, startC);
        BlockGroup blockGroup = new BlockGroup(startBlock, 0);
        queue.offer(startBlock);
        isVisited[startR][startC] = true;
        int number = board[startR][startC];
        while (!queue.isEmpty()) {
            Block block = queue.poll();
            int r = block.r;
            int c = block.c;
            for (int d = 0; d < 4; d++) {
                int nr = r + deltas[d][0];
                int nc = c + deltas[d][1];
                if (isIn(nr, nc) && !isVisited[nr][nc] && board[nr][nc] != BLACK && board[nr][nc] != EMPTY) {
                    if (board[nr][nc] == RAINBOW) {
                        if (!blockGroup.blockSet.contains(new Block(nr, nc))) {
                            queue.offer(new Block(nr, nc));
                            blockGroup.blockSet.add(new Block(nr, nc));
                            blockGroup.rainbowCount++;
                        }
                    } else if (board[nr][nc] == number) {
                        queue.offer(new Block(nr, nc));
                        isVisited[nr][nc] = true;
                        blockGroup.blockSet.add(new Block(nr, nc));
                    }
                }
            }
        }
        return blockGroup;
    }

    private static void removeBiggestBlockGroup() {
        int size = biggestBlockGroup.blockSet.size();
        score += (size * size);
        for (Block block : biggestBlockGroup.blockSet) {
            board[block.r][block.c] = EMPTY;
        }
    }

    private static void workGravity() {
        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == BLACK || board[i][j] == EMPTY || board[i + 1][j] != EMPTY) {
                    continue;
                }
                fall(i, j);
            }
        }
    }

    private static void fall(int r, int c) {
        int nr = r;
        while (true) {
            nr += 1;
            if (!isIn(nr, c) || board[nr][c] != EMPTY) {
                nr -= 1;
                break;
            }
        }
        board[nr][c] = board[r][c];
        board[r][c] = EMPTY;
    }

    private static void rotate() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotated[i][j] = board[j][N - i - 1];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = rotated[i][j];
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
