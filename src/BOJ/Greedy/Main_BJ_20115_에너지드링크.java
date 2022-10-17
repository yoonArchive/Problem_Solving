package BOJ.Greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BJ_20115_에너지드링크 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Double> energyDrinks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            energyDrinks.add(Double.parseDouble(st.nextToken()));
        }
        Collections.sort(energyDrinks);
        while (--N > 0) {
            double seleceted = energyDrinks.get(0) / 2;
            energyDrinks.set(N, energyDrinks.get(N) + seleceted);
            energyDrinks.remove(0);
        }
        bw.write(Double.toString(energyDrinks.get(0)));
        br.close();
        bw.flush();
        bw.close();
    }
}
