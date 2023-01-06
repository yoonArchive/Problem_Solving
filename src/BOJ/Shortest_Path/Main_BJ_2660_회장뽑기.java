package BOJ.Shortest_Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_2660_회장뽑기 {

    public static int N;
    public static List<List<Integer>> friendList;
    public static Queue<Friendship> queue;
    public static boolean[][] isVisited;
    public static int[] depths;

    public static class Friendship {
        int from;
        int to;
        int depth;

        public Friendship(int from, int to, int depth) {
            this.from = from;
            this.to = to;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        friendList = new ArrayList<>();
        queue = new LinkedList<>();
        isVisited = new boolean[N + 1][N + 1];
        depths = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            friendList.add(new ArrayList<>());
        }
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1) {
                break;
            }
            friendList.get(a).add(b);
            friendList.get(b).add(a);
            queue.offer(new Friendship(a, b, 1));
            queue.offer(new Friendship(b, a, 1));
            isVisited[a][b] = true;
            isVisited[b][a] = true;
        }
        getLeader();
        int minScore = Integer.MAX_VALUE;
        Map<Integer, Set<Integer>> scoreMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            int score = depths[i];
            minScore = Math.min(minScore, score);
            if (!scoreMap.containsKey(score)) {
                scoreMap.put(score, new TreeSet());
            }
            scoreMap.get(score).add(i);
        }
        sb.append(minScore).append(" ").append(scoreMap.get(minScore).size()).append("\n");
        for (int i : scoreMap.get(minScore)) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
        br.close();
    }

    private static void getLeader() {
        while (!queue.isEmpty()) {
            Friendship friendship = queue.poll();
            int from = friendship.from;
            int to = friendship.to;
            int depth = friendship.depth;
            depths[from] = Math.max(depths[from], depth);
            for (int friend : friendList.get(to)) {
                if (isVisited[from][friend] || friend == from) {
                    continue;
                }
                queue.offer(new Friendship(from, friend, depth + 1));
                isVisited[from][friend] = true;
            }
        }
    }
}
