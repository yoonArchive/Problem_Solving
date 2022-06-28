package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2669_직사각형네개의합집합의면적구하기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		boolean[][] plane = new boolean[100][100];
		int count = 0;
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int bottomX = Integer.parseInt(st.nextToken());
			int bottomY = Integer.parseInt(st.nextToken());
			int topX = Integer.parseInt(st.nextToken());
			int topY = Integer.parseInt(st.nextToken());
			for (int j = bottomX; j < topX; j++) {
				for (int k = bottomY; k < topY; k++) {
					if (!plane[j][k]) {
						plane[j][k]=true;
						count++;
					}
						
				}
			}
		}
		bw.write(Integer.toString(count));
		br.close();
		bw.flush();
		bw.close();
	}

}
