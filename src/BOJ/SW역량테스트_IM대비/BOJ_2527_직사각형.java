package BOJ.SW역량테스트_IM대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2527_직사각형 {
	private static char RECTANGLE = 'a';
	private static char LINE = 'b';
	private static char POINT = 'c';
	private static char NO_OVERLAPPED = 'd';

	public static class Rectangle {
		int x, y, p, q;

		public Rectangle(int x, int y, int p, int q) {
			super();
			this.x = x;
			this.y = y;
			this.p = p;
			this.q = q;
		}

	}

	public static char overlappedCheck(Rectangle r1, Rectangle r2) {
		int maxLeft = Math.max(r1.x, r2.x);
		int minRight = Math.min(r1.p, r2.p);
		int maxBottom = Math.max(r1.y, r2.y);
		int minTop = Math.min(r1.q, r2.q);

		int width = minRight - maxLeft;
		int height = minTop - maxBottom;

		if (width > 0 && height > 0)
			return RECTANGLE;
		else if ((width == 0 && height > 0) || (width > 0 && height == 0))
			return LINE;
		else if (width == 0 && height == 0)
			return POINT;
		else
			return NO_OVERLAPPED;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < 4; k++) {
			Rectangle rec1 = null, rec2 = null;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 2; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				int q = Integer.parseInt(st.nextToken());
				if (i == 0)
					rec1 = new Rectangle(x, y, p, q);
				else
					rec2 = new Rectangle(x, y, p, q);
			}
			sb.append(overlappedCheck(rec1, rec2) + "\n");
		}
		System.out.println(sb.toString());
		br.close();

	}

}
