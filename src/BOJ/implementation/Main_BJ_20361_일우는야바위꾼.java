package BOJ.implementation;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_20361_일우는야바위꾼 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        st.nextToken(); // 컵의 개수 -> 활용하지 않으므로 그냥 날린다.
        int X = Integer.parseInt(st.nextToken()); // 공의 위치
        int K = Integer.parseInt(st.nextToken()); // 수행 횟수

        for (int i = 0; i < K; i++) { // 바꾸는 횟수만큼 반복
            st = new StringTokenizer(br.readLine(), " ");
            int loc1 = Integer.parseInt(st.nextToken()); // 바꾸는 컵 위치
            int loc2 = Integer.parseInt(st.nextToken()); // 바꾸는 컵 위치
            if (loc1 == X) {
                X = loc2;
            } else if (loc2 == X) {
                X = loc1;
            }
        }
        bw.write(Integer.toString(X));
        br.close();
        bw.flush();
        bw.close();
    }

}
