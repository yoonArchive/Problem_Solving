package BOJ.BackTracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main_BJ_7682_틱택토 {
	static final char O_MARKER = 'O';
	static final char X_MARKER = 'X';
	static char[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		board = new char[3][3];
		while (true) {
			String s = br.readLine();
			if (s.equals("end"))
				break;
			int xCnt = 0, oCnt = 0;
			for (int i = 0; i < 9; i++) {
				char c = s.charAt(i);
				board[i / 3][i % 3] = c;
				if (c == X_MARKER)
					xCnt++;
				else if (c == O_MARKER)
					oCnt++;
			}
			if (xCnt + oCnt == 9) {
				if (xCnt != oCnt + 1 || isFinalState(O_MARKER))
					sb.append("invalid").append("\n");
				else
					sb.append("valid").append("\n");
			} else {
				if (xCnt == oCnt) { // O 말로 3칸이 이어져야 valid
					if (isFinalState(O_MARKER) && !isFinalState(X_MARKER))
						sb.append("valid").append("\n");
					else
						sb.append("invalid").append("\n");
				} else if (xCnt == oCnt + 1) { // X 말로 3칸이 이어져야 valid
					if (isFinalState(X_MARKER) && !isFinalState(O_MARKER))
						sb.append("valid").append("\n");
					else
						sb.append("invalid").append("\n");
				} else
					sb.append("invalid").append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();

	}

	static boolean isFinalState(char marker) {
		// 가로
		for (int r = 0; r < 3; r++) {
			if (board[r][0] == marker && board[r][1] == marker && board[r][2] == marker)
				return true;
		}
		// 세로
		for (int c = 0; c < 3; c++) {
			if (board[0][c] == marker && board[1][c] == marker && board[2][c] == marker)
				return true;
		}
		// 대각선
		return (board[0][0] == marker && board[1][1] == marker && board[2][2] == marker)
				|| (board[0][2] == marker && board[1][1] == marker && board[2][0] == marker);
	}
}