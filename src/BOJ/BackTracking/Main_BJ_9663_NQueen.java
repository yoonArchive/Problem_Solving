package BOJ.BackTracking;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_BJ_9663_NQueen {
	static int N, count;
	static int cols[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		cols = new int[N + 1];
		setQueen(1);
		bw.write(Integer.toString(count));
		br.close();
		bw.flush();
		bw.close();
	}

	private static void setQueen(int row) {
		if (row == N + 1) {
			count++;
			return;
		}

		for (int i = 1; i <= N; i++) {
			cols[row] = i;
			if (isAvailable(row)) // 가지치기
				setQueen(row + 1);
		}
	}

	private static boolean isAvailable(int row) {
		for (int i = 1; i < row; i++) {
			if (cols[row] == cols[i] || Math.abs(cols[row] - cols[i]) == (row - i))
				return false;
		}
		return true;
	}

}
