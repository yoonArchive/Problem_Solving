package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10250_ACM호텔 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int H = Integer.parseInt(st.nextToken());
			st.nextToken();
			int N = Integer.parseInt(st.nextToken());

			int remainder = N % H;
			int floor = remainder == 0 ? H : remainder;
			sb.append(floor);

			int divide = N / H;
			StringBuilder numSb = new StringBuilder();
			if(remainder==0)
				if((divide)<10) numSb.append(0).append(divide);
				else numSb.append(divide);
			else
				if((divide+1)<10) numSb.append(0).append(divide+1);
				else numSb.append(divide+1);
			sb.append(numSb).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
