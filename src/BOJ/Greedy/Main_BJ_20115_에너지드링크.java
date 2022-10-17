package BOJ.Greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Main_BJ_20115_에너지드링크 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] energyDrinks = new int[N];
        int energyDrink;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            energyDrink = Integer.parseInt(st.nextToken());
            energyDrinks[i] = energyDrink;
            max = Math.max(max, energyDrink);
        }
        double total = max;
        for (int drink : energyDrinks) {
            if (drink == max) {
                continue;
            }
            total += (double) drink / 2;
        }
        bw.write(Double.toString(total));
        br.close();
        bw.flush();
        bw.close();
    }
}
