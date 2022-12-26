package BOJ.UnionFind;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BJ_1043_거짓말 {

    public static final int KNOW_TRUTH = -1;

    public static int N, M;
    public static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        int truths = Integer.parseInt(st.nextToken());
        // 진실을 아는 사람들이 같은 값을 가리키도록 한다.
        for (int i = 0; i < truths; i++) {
            parents[Integer.parseInt(st.nextToken())] = KNOW_TRUTH;
        }
        // 파티마다 오는 사람들의 정보
        Set<Integer>[] partyInfo = new Set[M];
        for (int i = 0; i < M; i++) {
            partyInfo[i] = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                partyInfo[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        // 파티 정보를 탐색하며 parent 갱신
        boolean tellTruth, changeFlag;
        while (true) {
            changeFlag = false;
            for (int i = 0; i < M; i++) {
                tellTruth = false;
                for (int number : partyInfo[i]) {
                    if (parents[number] == KNOW_TRUTH) {
                        tellTruth = true;
                        break;
                    }
                }
                if (tellTruth) {
                    for (int number : partyInfo[i]) {
                        if (parents[number] != KNOW_TRUTH) {
                            parents[number] = KNOW_TRUTH;
                            changeFlag = true;
                        }
                    }
                }
            }
            if (!changeFlag) {
                break;
            }
        }
        // 진실을 말하지 않아도 되는 파티 갯수 구하기
        int count = 0;
        for (int i = 0; i < M; i++) {
            for (int number : partyInfo[i]) {
                if (parents[number] != KNOW_TRUTH) {
                    count++;
                    break;
                }
            }
        }
        bw.write(Integer.toString(count));
        br.close();
        bw.flush();
        bw.close();
    }
}
