package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10158_개미_시간초과 { //시간초과
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken()); 
		int q = Integer.parseInt(st.nextToken()); 
		int t = Integer.parseInt(br.readLine()); 
		boolean pFlag = true;
		boolean qFlag = true;

		while (t > 0) {
			t--;
			if (p == 0) pFlag = true;
			
			if (p == w) pFlag = false;
			
			if (q == 0) qFlag = true;
			
			if (q == h) qFlag = false;
			
			if (pFlag) p++;
			else p--;
			
			if (qFlag) q++;
			else q--;
		}
		
		sb.append(p).append(" ").append(q);
		System.out.println(sb.toString());
		br.close();

	}

}
