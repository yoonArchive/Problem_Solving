package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_17136_색종이붙이기 {
	static final int SIZE = 10;
	static int remain, minCnt = 26, colorPaperCnt[], paper[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		paper = new int[SIZE][SIZE];
		colorPaperCnt = new int[6];
		for (int i = 1; i <= 5; i++)
			colorPaperCnt[i] = 5;
		for (int i = 0; i < SIZE; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < SIZE; j++) {
				int num = Integer.parseInt(st.nextToken());
				paper[i][j] = num;
				if (num == 1)
					remain++;
			}
		}
		attach(0, 0, 0);
		if (minCnt == 26)
			minCnt = -1;
		bw.write(Integer.toString(minCnt));
		br.close();
		bw.flush();
		bw.close();
	}

	private static void attach(int index, int attachedCnt, int paperCnt) {
		if (attachedCnt == remain) {
			minCnt = Math.min(minCnt, paperCnt);
			return;
		}

		int r = index / SIZE;
		int c = index % SIZE;

		if (paper[r][c] == 0)
			attach(index + 1, attachedCnt, paperCnt);
		else {
			for (int paperSize = 5; paperSize >= 1; paperSize--) {
				if (!canAttach(r, c, paperSize))
					continue;
				for (int i = r; i < r + paperSize; i++)
					for (int j = c; j < c + paperSize; j++)
						paper[i][j] = 0;
				colorPaperCnt[paperSize]--;
				attach(index + 1, attachedCnt + paperSize * paperSize, paperCnt + 1);
				for (int i = r; i < r + paperSize; i++)
					for (int j = c; j < c + paperSize; j++)
						paper[i][j] = 1;
				colorPaperCnt[paperSize]++;
			}
		}

	}

	private static boolean canAttach(int r, int c, int paperSize) {
		// 경계 밖으로 나가면 false
		if (r + paperSize > SIZE || c + paperSize > SIZE)
			return false;
		// 붙일 수 있는 색종이가 없다면 false
		if (colorPaperCnt[paperSize] <= 0)
			return false;
		// 0이 적힌 칸이 있다면 false
		for (int i = r; i < r + paperSize; i++) {
			for (int j = c; j < c + paperSize; j++) {
				if (paper[i][j] == 0)
					return false;
			}
		}
		return true;
	}

}
