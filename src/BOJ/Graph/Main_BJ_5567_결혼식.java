package BOJ.Graph;

import java.io.*;
import java.util.*;

public class Main_BJ_5567_결혼식 {

    public static final int SANGGEUN = 1;

    public static int N, M, inviteCount;
    public static List<List<Integer>> friendsList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        friendsList = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] isInvited = new boolean[N + 1];
        isInvited[SANGGEUN] = true;
        for (int i = 0; i <= N; i++) {
            friendsList.add(new ArrayList<>());
        }
        StringTokenizer st = null;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friendsList.get(a).add(b);
            friendsList.get(b).add(a);
            if (a == SANGGEUN) {
                queue.offer(b);
                isInvited[b] = true;
                inviteCount++;
            } else if (b == SANGGEUN) {
                queue.offer(a);
                isInvited[a] = true;
                inviteCount++;
            }
        }
        invite(queue, isInvited);
        bw.write(Integer.toString(inviteCount));
        br.close();
        bw.flush();
        bw.close();
    }

    private static void invite(Queue<Integer> queue, boolean[] isInvited) {
        int size = queue.size();
        while (size-- > 0) {
            int friend = queue.poll();
            for (int friendOfFriend : friendsList.get(friend)) {
                if (isInvited[friendOfFriend]) {
                    continue;
                }
                queue.offer(friendOfFriend);
                isInvited[friendOfFriend] = true;
                inviteCount++;
            }
        }
    }
}
