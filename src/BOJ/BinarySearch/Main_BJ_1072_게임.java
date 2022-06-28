package BOJ.BinarySearch;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_1072_게임 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		long X = Integer.parseInt(st.nextToken());
		long Y = Integer.parseInt(st.nextToken());
		long rate = Y * 100 / X;
		long low = 1;
		long high = X;
		boolean isChanged = false;
		long ans = 0;
		while (low <= high) {
			long mid = (low + high) / 2; // 게임 횟수
			long tmpRate = ((Y + mid) * 100) / (X + mid); // mid번 게임 했을 때의 승률
			if (tmpRate - rate > 0) { // 승률이 올랐다면
				isChanged = true;
				high = mid - 1;
			} else {
				ans = mid;
				low = mid + 1;
			}
		}
		if (!isChanged) bw.write("-1");
		else bw.write(Long.toString(ans + 1));
		br.close();
		bw.flush();
		bw.close();
	}

}
