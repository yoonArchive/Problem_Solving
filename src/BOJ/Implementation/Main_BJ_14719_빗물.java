package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BJ_14719_빗물 {
	static int H, W, end;
	static boolean isBlock[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		isBlock = new boolean[H][W];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < W; i++) {
			int height = Integer.parseInt(st.nextToken());
			for (int j = 0; j < height; j++)
				isBlock[H - 1 - j][i] = true;
		}
		end = H * W;
		rain(0, 0);
	}

	private static void rain(int index, int sum) {
		if (index == end) {
			System.out.println(sum);
			System.exit(0);
		}
		int r = index / W;
		int c = index % W;
		if (isBlock[r][c] || isBorder(c) || !isExistBlock(r, c, true) || !isExistBlock(r, c, false))
			rain(index + 1, sum);
		else
			rain(index + 1, sum + 1);
	}

	private static boolean isExistBlock(int r, int c, boolean isLeft) {
		int diff = isLeft ? -1 : 1;
		int end = isLeft ? 0 : W - 1;
		while (c != end) {
			c += diff;
			if (isBlock[r][c])
				return true;
		}
		return false;
	}

	private static boolean isBorder(int c) {
		return c == 0 || c == W - 1;
	}

}
