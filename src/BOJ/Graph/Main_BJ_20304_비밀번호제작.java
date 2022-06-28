package BOJ.Graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_20304_비밀번호제작 {

	/*
	 * 입력값들의 범위를 보았을 때 완전 탐색 시 시간초과가 나기 때문에 다른 접근 방식을 택했습니다.
	 * 
	 * 예시로 준 N=10인 상황에서, 로그인에 시도된 비밀번호인 3과 안전거리가 1인 숫자를 구해보면 -> 1,2,7 이다. 또 로그인에 시도된
	 * 비밀번호인 3과 안전거리가 2인 숫자를 구해보면 -> 0,5,6,9,10 이다. 이 때 0, 5, 6, 9, 10은 3과 안전거리가 1인
	 * 숫자인 1,2,7과는 안전거리가 1이라는 규칙이 있었다.)
	 * 
	 * 접근 방식: 1. 로그인에 시도된 숫자들을 큐에 넣는다. 2. 큐에서 하나씩 poll하면서 poll한 숫자와 안전거리가 1이면서 N을
	 * 넘어가지 않는 숫자들을 큐에 offer한다. (changePw 메소드) 3. 2의 과정을 반복. 언제까지? 큐가 빌 때 까지?
	 * 
	 * 선입선출 방식 사용을 위해 큐를 사용하고, 또 이미 안전거리가 구해진 숫자들을 저장하기 위한 isVisited 배열 필요
	 *
	 */

	static int N, M;
	static Queue<pwInfo> q;
	static boolean isVisited[];

	public static class pwInfo { // 비밀번호 숫자와 안전거리를 멤버 변수로 갖는 pwInfo 클래스
		int num;
		int safety;

		public pwInfo(int num, int safety) {
			super();
			this.num = num;
			this.safety = safety;
		}
	}

	public static int getStrongPw() {
		int ans = 0; // while문 돌면서 안전거리가 최대이면 ans에 저장
		while (!q.isEmpty()) {
			pwInfo nowPw = q.poll();
			// System.out.println(nowPw.num + " " + nowPw.safety);
			ans = Math.max(ans, nowPw.safety);

			// nowPw.num과 안전거리가 1인 숫자 구하기 - 이걸 어떻게 하는지 모르겠습니다..
			for (int i = 0; i < 20; i++) { // N의 최대값이 백만이므로 Math.pow(2,i)가 백만을 넘어가지 않도록! //<=20이면 73%에서 틀리고 <20이면 pass
				int compare = (int) Math.pow(2, i); // compare에 1,2,4,8 ... 2^20 넣어주고(2진수로 1은 0001, 2는 0010, 4는 0100 ..)
				int diffOne = nowPw.num ^ compare; // nowPw와 compare와 XOR 연산하면 안전거리가 1인 수가 나오고, 이를 curNum에 저장한다.

				if (diffOne <= N && !isVisited[diffOne]) { // curNum이 N범위를 넘어가지 않고, 이미 방문된 숫자가 아니라면 방문여부를 true로 바꾸고 큐에
															// 넣는다
					isVisited[diffOne] = true;
					q.offer(new pwInfo(diffOne, nowPw.safety + 1));
				}

			}
		}
		return ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine()); // 관리자 계정 비밀번호의 최댓값
		M = Integer.parseInt(br.readLine()); // 로그인 시도에 사용된 비밀번호의 개수

		q = new LinkedList<>();
		isVisited = new boolean[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int tried = Integer.parseInt(st.nextToken());
			q.offer(new pwInfo(tried, 0)); // 해커가 로그인에 시도한 암호는 안전거리가 0이다.
			isVisited[tried] = true;
		}

		bw.write(Integer.toString(getStrongPw()));

		br.close();
		bw.flush();
		bw.close();
	}

}
