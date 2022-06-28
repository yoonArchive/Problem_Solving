package BOJ.Greedy;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_13305_주유소 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); //도시의 개수
        long city[][] = new long[2][N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            city[0][i] = Long.parseLong(st.nextToken()); //0행: 도로 길이
        }
        st = new StringTokenizer(br.readLine());
        long minFee = Long.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            city[1][i] = Long.parseLong(st.nextToken()); //1행: 주유 가격
            if (i != N - 1 && city[1][i] < minFee) minFee = city[1][i]; //최소 주유 가격을 minFee에 저장
        }

        long preDiff = Integer.MAX_VALUE;
        long sum = 0;
        long gasFee = 0;
        for (int i = 0; i < N - 1; i++) {
            long diff = city[1][i] - minFee;
            if (diff == 0) { //현재 주유 가격이 최소 주유 가격이면 이 주유소에서 full 충전
                gasFee = city[1][i];
                while (i < N) {
                    sum += gasFee * city[0][i];
                    i++;
                }
                break;
            }
            if (diff < preDiff) { //이전 주유 가격보다 가격이 줄었다면 충전
                preDiff = diff;
                gasFee = city[1][i];
                sum += gasFee * city[0][i];
            } else sum += gasFee * city[0][i];
        }
        bw.write(Long.toString(sum));
        br.close();
        bw.flush();
        bw.close();
    }


}
