package BOJ.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BJ_1969_DNA {
    static int N, M;
    static char[] nucleotide = {'A', 'C', 'G', 'T'};
    static char[][] DNA;
    static int[] count;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        DNA = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++)
                DNA[i][j] = line.charAt(j);
        }
        count = new int[4];
        makeDNA(0, 0);
    }

    private static void makeDNA(int index, int hammingDist) {
        if (index == M) {
            sb.append("\n").append(hammingDist);
            System.out.println(sb.toString());
            System.exit(0);
        }

        for (int i = 0; i < N; i++)
            count[getIndex(DNA[i][index])]++;

        int maxCnt = 0;
        int idx = -1;
        for (int i = 0; i < 4; i++) {
            if (count[i] > maxCnt) {
                maxCnt = count[i];
                idx = i;
            }
        }
        sb.append(nucleotide[idx]);
        Arrays.fill(count, 0);
        makeDNA(index + 1, hammingDist + (N - maxCnt));
    }

    private static int getIndex(char c) {
        int idx = -1;
        switch (c) {
            case 'A':
                idx = 0;
                break;
            case 'C':
                idx = 1;
                break;
            case 'G':
                idx = 2;
                break;
            case 'T':
                idx = 3;
                break;
        }
        return idx;
    }

}
