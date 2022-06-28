package BOJ.수학;

import java.io.*;
import java.util.*;

public class Main_BJ_2775_부녀회장이될테야 {

    public static class Address {
        int k, n;

        public Address(int k, int n) {
            this.k = k;
            this.n = n;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int maxK = 0, maxN = 0;
        ArrayList<Address> list = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            list.add(new Address(k, n));
            maxK = Math.max(maxK, k);
            maxN = Math.max(maxN, n);
        }
        int apt[][] = new int[maxK + 1][maxN + 1];
        int lengthK = apt.length;
        int lengthN = apt[0].length;

        for (int i = 0; i < lengthK; i++)
            apt[i][0] = 1;

        for (int i = 1; i < lengthN; i++)
            apt[lengthK - 1][i] = i + 1;

        for (int i = maxK - 1; i >= 0; i--) {
            for (int j = 1; j < lengthN; j++) {
                apt[i][j] = apt[i + 1][j] + apt[i][j - 1];
            }
        }

        for (int i = 0, length = list.size(); i < length; i++) {
            Address address = list.get(i);
            int k = address.k;
            int n = address.n;
            sb.append(apt[maxK - k][n - 1]).append("\n");
        }
        System.out.println(sb.toString());
    }

}
