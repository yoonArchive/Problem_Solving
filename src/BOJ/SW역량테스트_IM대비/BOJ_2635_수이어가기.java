package BOJ.SW역량테스트_IM대비;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2635_수이어가기 {

	public static void getNext(ArrayList<Integer> numbers, int n, int idx) {
		if (n < 0)
			return;
		numbers.add(n);
		getNext(numbers, numbers.get(idx - 1) - numbers.get(idx), idx + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> numbers = new ArrayList<>();

		int maxCnt = 0;
		for (int i = N / 2; i <= N; i++) {
			numbers.clear();
			numbers.add(N);
			getNext(numbers, i, 1);
			int nowSize = numbers.size();
			if (nowSize > maxCnt)
				maxCnt = nowSize;

			if (nowSize < maxCnt) {
				numbers.clear();
				numbers.add(N);
				getNext(numbers, i - 1, 1);
				break;
			}
		}
		sb.append(numbers.size() + "\n");
		numbers.forEach(n -> sb.append(n + " "));
		System.out.println(sb.toString());
		br.close();
	}

}
