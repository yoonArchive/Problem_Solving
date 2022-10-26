package BOJ.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_BJ_2251_물통 {

    public static final int A = 0;
    public static final int B = 1;
    public static final int C = 2;
    public static final int BUCKETS = 3;

    public static int[] volumes;
    public static boolean[][] isPoured;
    public static Set<Integer> ListOfC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        volumes = new int[BUCKETS];
        ListOfC = new TreeSet<>();
        for (int i = 0; i < BUCKETS; i++) {
            volumes[i] = Integer.parseInt(st.nextToken());
        }
        isPoured = new boolean[volumes[A] + 1][volumes[B] + 1];
        pour(0, 0, volumes[2]);
        for (int amount : ListOfC) {
            sb.append(amount).append(" ");
        }
        System.out.println(sb);
        br.close();
    }

    private static void pour(int amountOfA, int amountOfB, int amountOfC) {
        if (isPoured[amountOfA][amountOfB]) {
            return;
        }
        if (amountOfA == 0) {
            ListOfC.add(amountOfC);
        }
        isPoured[amountOfA][amountOfB] = true;

        // From A to B
        if (amountOfA + amountOfB > volumes[B]) {
            pour((amountOfA + amountOfB) - volumes[B], volumes[B], amountOfC);
        } else {
            pour(0, amountOfA + amountOfB, amountOfC);
        }

        // From A to C
        if (amountOfA + amountOfC > volumes[C]) {
            pour((amountOfA + amountOfC) - volumes[C], amountOfB, volumes[C]);
        } else {
            pour(0, amountOfB, amountOfA + amountOfC);
        }

        // From B to A
        if (amountOfB + amountOfA > volumes[A]) {
            pour(volumes[A], (amountOfB + amountOfA) - volumes[A], amountOfC);
        } else {
            pour(amountOfB + amountOfA, 0, amountOfC);
        }

        // From B to C
        if (amountOfB + amountOfC > volumes[C]) {
            pour(amountOfA, (amountOfB + amountOfC) - volumes[C], volumes[C]);
        } else {
            pour(amountOfA, 0, amountOfB + amountOfC);
        }

        // From C to A
        if (amountOfC + amountOfA > volumes[A]) {
            pour(volumes[A], amountOfB, (amountOfC + amountOfA) - volumes[A]);
        } else {
            pour(amountOfC + amountOfA, amountOfB, 0);
        }

        // From C to B
        if (amountOfC + amountOfB > volumes[B]) {
            pour(amountOfA, volumes[B], (amountOfB + amountOfC) - volumes[B]);
        } else {
            pour(amountOfA, amountOfB + amountOfC, 0);
        }
    }
}
