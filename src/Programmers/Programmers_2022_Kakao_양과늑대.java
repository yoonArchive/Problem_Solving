package Programmers;

public class Programmers_2022_Kakao_양과늑대 {
    static final int SHEEP = 0;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) {
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        System.out.println(solution(info, edges));
    }

    public static int solution(int[] info, int[][] edges) {
        visited = new boolean[info.length];
        visited[0] = true;
        move(1, 0, edges, info);
        return answer;
    }

    public static void move(int sheep, int wolf, int[][] edges, int[] info) {
        if (sheep <= wolf) {
            return;
        }
        answer = Math.max(sheep, answer);
        for (int[] edge : edges) {
            int prev = edge[0];
            int current = edge[1];
            if (visited[prev] && !visited[current]) {
                visited[current] = true;
                if (info[current] == SHEEP) {
                    move(sheep + 1, wolf, edges, info);
                } else {
                    move(sheep, wolf + 1, edges, info);
                }
                visited[current] = false;
            }
        }
    }
}
