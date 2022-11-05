package BOJ.Graph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_13023_ABCDE {

    public static final int NOT_EXIST = 0;
    public static final int EXIST = 1;
    public static final int ABCDE = 5;

    public static int N, M;
    public static boolean hasRelation;
    public static List<List<Integer>> friends;
    public static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        friends = new ArrayList<>();
        isVisited = new boolean[N];
        for (int i = 0; i < N; i++) {
            friends.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends.get(a).add(b);
            friends.get(b).add(a);
        }
        for (int i = 0; i < N; i++) {
            searchRelation(1, i);
            if (hasRelation) {
                break;
            }
        }
        bw.write(Integer.toString(hasRelation ? EXIST : NOT_EXIST));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void searchRelation(int count, int person) {
        if (count == ABCDE) {
            hasRelation = true;
            return;
        }
        isVisited[person] = true;
        for (int friend : friends.get(person)) {
            if (isVisited[friend]) {
                continue;
            }
            searchRelation(count + 1, friend);
        }
        isVisited[person] = false;
    }
}
