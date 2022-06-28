package BOJ.DataStructure.Stack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_BJ_17298_오큰수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int A[][] = new int[2][N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[0][i] = Integer.parseInt(st.nextToken()); //원소 값
			A[1][i] = -1; //오큰수
		}

		Stack<Integer> stack = new Stack<>(); //원소 값이 아닌 인덱스를 저장하는 스택!->인덱스를 통해 배열원소 값과 오큰수에 접근
		stack.push(0); 
		for (int i = 1; i < N; i++) {
			if(A[0][i]<=A[0][stack.peek()]) { //원소값이 스택의 top인덱스가 가리키는 원소값보다 작으면
				stack.push(i); //인덱스를 push
				continue;
			}
			while(!stack.isEmpty()) {
				if(A[0][i]<=A[0][stack.peek()]) break; //지금 push하려는 값보다 작은 값만 pop -> 자기보다 작은 수는 밑에 있을 수 없다.
				int idx=stack.pop();
				A[1][idx]=A[0][i];
			}
			stack.push(i);
		}
		for(int i=0;i<N;i++) {
			bw.write(A[1][i]+" ");
		}

		br.close();
		bw.flush();
		bw.close();
	}

}
