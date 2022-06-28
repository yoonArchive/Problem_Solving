package BOJ.수학;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_4998_저금 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = "";
        try {
            while ((input = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(input, " ");
                double N = Double.parseDouble(st.nextToken());
                double B = Double.parseDouble(st.nextToken());
                double M = Double.parseDouble(st.nextToken());
                int year = 0;
                while (true) {
                    year++;
                    N = N * (1 + B / 100);
                    if (N > M) {
                        System.out.println(year);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            br.close();
        }

    }

}
