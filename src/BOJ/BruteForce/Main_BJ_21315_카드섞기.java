package BOJ.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_21315_카드섞기 {
    static ArrayList<Integer> cards;
    static int N, last;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        int[] dummy = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++)
            dummy[i] = Integer.parseInt(st.nextToken());
        int firstK, secondK = 0;
        cards = new ArrayList<>();
        outer:
        for (firstK = 1; (firstK << 1) < N; firstK++) {
            for (secondK = 1; (secondK << 1) < N; secondK++) {
                initialize(cards);
                mix(firstK << 1);
                mix(secondK << 1);
                // System.out.println(firstK + " " + secondK + " " + cards);
                if (isEqual(cards, dummy))
                    break outer;
                cards.clear();
            }
        }
        sb.append(firstK).append(" ").append(secondK);
        System.out.println(sb.toString());
        br.close();
    }

    private static void mix(int cnt) {
        last = N;
        ArrayList<Integer> upCards = new ArrayList<>();
        while (true) {
            for (int idx = last - 1; idx >= last - cnt; idx--) {
                upCards.add(0, cards.get(idx));
                cards.remove(idx);
            }
            cards.addAll(0, upCards);
            if (cnt == 1)
                break;
            upCards.clear();
            last = cnt;
            cnt /= 2;
        }
    }

    private static boolean isEqual(ArrayList<Integer> cards, int[] dummy) {
        for (int i = 0; i < N; i++)
            if (cards.get(i) != dummy[i])
                return false;
        return true;
    }

    private static void initialize(ArrayList<Integer> cards) {
        for (int i = 1; i <= N; i++)
            cards.add(i);
    }

}
